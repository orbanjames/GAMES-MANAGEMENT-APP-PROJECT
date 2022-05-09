package com.jamesorban.mygamestore.modelFx;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.jamesorban.mygamestore.controllers.MinesController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class MinesFx {

    private final int height;
    private final int width;
    private boolean showAll;

    private final Point[][] gameBoard;

    public MinesFx(int height, int width, int numMines) {
        this.height = height;
        this.width = width;
        this.showAll = false;
        gameBoard = new Point[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameBoard[i][j] = new Point(i, j);
            }
        }
        int count = 0;
        while (count < numMines) {
            Random rnd = new Random();
            int x = rnd.nextInt(height);
            int y = rnd.nextInt(width);
            if (!gameBoard[x][y].isMine) {
                gameBoard[x][y].isMine = true;
                count++;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameBoard[i][j].setNeighbours();
            }
        }

    }

    public void addMine(int i, int j) {
        if (gameBoard[i][j].isMine) {
            return;
        }
        gameBoard[i][j].isMine = true;
    }

    public boolean open(int i, int j) {

        // if its not mine return true.
        if (gameBoard[i][j].isMine) {
            return false;
        }
        gameBoard[i][j].isOpen = true;

        if (gameBoard[i][j].getCountOfMinesArround() == 0) {
            Set<Point> neighbours = gameBoard[i][j].neighbours;
            for (Point p : neighbours) {
                if(!p.isOpen) {
                    if(!open(p.x, p.y)) {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public void toggleFlag(int x, int y) {
        gameBoard[x][y].isFlag = !gameBoard[x][y].isFlag;
    }

    public boolean isDone() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!gameBoard[i][j].isMine && !gameBoard[i][j].isOpen) {
                    return false;
                }
            }
        }
        return true;
    }

    public String get(int i, int j) {
        return gameBoard[i][j].toString();
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public String toString() {
        StringBuilder mine = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String str = gameBoard[i][j].toString();
                if (!str.equals("")) {
                    mine.append(str);
                    flag = true;
                }
            }
            if (flag) {
                mine.append("\n");
            }
            flag = false;
        }
        return mine.toString();

    }

    // ************** inner class point **************//

    public class Point {

        private boolean isOpen;
        private boolean isMine;
        private boolean isFlag;
        private int x, y;
        private Set<Point> neighbours;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            isOpen = false;
            isMine = false;
            isFlag = false;
            neighbours = new HashSet<>();
        }

        public boolean isOpen() {
            return isOpen;
        }
        public boolean isMine() {
            return isMine;
        }
        public boolean isFlag() {
            return isFlag;
        }

        public int getCountOfMinesArround() {
            Iterator<Point> it = neighbours.iterator();
            int count = 0;
            while (it.hasNext()) {
                if (it.next().isMine) {
                    count++;
                }
            }
            return count;
        }

        public void setNeighbours() {
            // add all neighbours to set.
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i == x && j == y) {
                        continue;
                    }
                    if (isInside(i, j)) {
                        neighbours.add(gameBoard[i][j]);
                    }
                }
            }
        }

        public boolean isInside(int i, int j) {
            // check if the point is inside the board.
            if (i < 0 || i >= height || j < 0 || j >= width) {
                return false;
            }
            return true;
        }

        public String toString() {
            if (isOpen || showAll) {
                if (isMine) {
                    return "X";
                } else {
                    if (getCountOfMinesArround() == 0) {
                        return " ";
                    }
                    return "" + getCountOfMinesArround();
                }
            } else {
                if (isFlag) {
                    return "F";
                } else {
                    return ".";
                }
            }
        }
    }

    public Point[][] getBoard(){
        return gameBoard;
    }

    public static void main(String[] args) {
        MinesFx m = new MinesFx (3, 4, 0);
        m.addMine(0,1);
        m.addMine(2, 3);
        System.out.println(m.open(2, 0));
        System.out.println(m);
        m.setShowAll(true);

//		m.toggleFlag(0, 1);
        System.out.println(m);

    }


    public static class Main extends Application {

        private TextField txtWidth;
        private TextField txtHeight;
        private TextField txtMines;

        private GridPane boardGrid;

        private MinesFx mine;
        private Button[][] buttonsBoard;

        private int width;
        private int height;

        private boolean yes_no_FLAG;

        private final int maxWidth = 600;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) throws Exception {
            stage.initStyle(StageStyle.UNDECORATED);

            StackPane root1;
            Scene scene;
            MinesController menuController;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("BoardView.fxml"));
                root1 = loader.load();
                menuController = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            yes_no_FLAG = false;
            // set pointers to all of my controller data.
            Button btnReset = menuController.getBtnReset();

            txtWidth = menuController.getTxtWidth();
            txtHeight = menuController.getTxtHeight();
            txtMines = menuController.getTxtMines();

            GridPane gloabalGrid = menuController.getGlobalGrid();
            boardGrid = menuController.getBoardGrid();
            Pane topPane = menuController.getDataPane();

            // Default start //
            txtWidth.setText("11");
            txtHeight.setText("11");
            txtMines.setText("11");

            //   /*************** set exit button *****************/
            Button btnExit = new Button();
            btnExit.setPrefSize(32, 32);
            btnExit.relocate(10, 10);

            // click to exit form.
            btnExit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                }
            });

            btnReset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (txtWidth.getText().equals("") || txtHeight.getText().equals("") || txtMines.getText().equals("")) {
                        // bad input , set pop up window to the user.
                        createPopUpMessage("Sorry , first fill all text fields..", "wrong");
                    } else {
                        // all good set the mine.
                        createNewGame();
                        setOnClickBoard();
                    }

                }
            });

            scene = new Scene(root1);
            scene.getStylesheets().add("style.css");
            stage.setScene(scene);
            stage.show();
        }

        public void toggleFlag(int i, int j) {
            mine.toggleFlag(i, j);
            buttonsBoard[i][j].setText(mine.getBoard()[i][j].toString());
        }

        public void setButtonsOpen() {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Point currentPoint = mine.getBoard()[i][j];
                    if (currentPoint.isOpen()) {
                        buttonsBoard[i][j].setText(currentPoint.toString());
                        switch (currentPoint.getCountOfMinesArround()) {
                            case 0: {
                                buttonsBoard[i][j].setStyle(""
                                        + "-fx-background-color:black");
                            }
                            break;
                            case 1: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#8ACAFE");
                            }
                            break;
                            case 2: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#00ff00");
                            }
                            break;
                            case 3: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#2e8b57");
                            }
                            break;
                            case 4: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#bd3b3b");
                            }
                            break;
                            case 5: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#377fc3");
                            }
                            break;
                            case 6: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#55ff55");
                            }
                            break;
                            case 7: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#ffbf23");
                            }
                            break;
                            case 8: {
                                buttonsBoard[i][j].setStyle("" + "-fx-color:#ccccff");
                            }
                            break;
                        }
                        buttonsBoard[i][j].setDisable(true);
                    }
                }
            }
        }

        private void showAll(boolean b) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Point currentPoint = mine.getBoard()[i][j];
                    if(currentPoint.toString().equals(" ")) {
                        buttonsBoard[i][j].setText("");
                    }else {
                        buttonsBoard[i][j].setText(currentPoint.toString());
                    }
                }
            }
        }

        private void createNewGame() {
            boardGrid.getChildren().clear(); // clear all children before set new game.
            boardGrid.setStyle(""
                    + "-fx-padding:5px");
    //		boardGrid.setStyle("" + "-fx-background-color:red;");
            width = Integer.parseInt(txtWidth.getText());
            height = Integer.parseInt(txtHeight.getText());
            boardGrid.setPrefWidth(850);
            boardGrid.setPrefHeight(650);
            int minesNumber = Integer.parseInt(txtMines.getText());
            mine = new MinesFx(width, height, minesNumber);
            mine.setShowAll(true);
            mine.setShowAll(false);
            buttonsBoard = new Button[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    buttonsBoard[i][j] = new Button();
                    double sizeWidth = boardGrid.getPrefWidth() / width;// > 30 ? 30 : (int)boardGrid.getWidth() / width;
                    double sizeHeight = boardGrid.getPrefHeight() / height;
                    buttonsBoard[i][j].setPrefSize(sizeWidth, sizeHeight);
                    buttonsBoard[i][j].setId(i + "," + j);
                    boardGrid.add(buttonsBoard[i][j], i + 1, j + 1);
                }

            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    buttonsBoard[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            MouseButton btnClick = event.getButton();
                            Button currentButton = (Button) event.getSource();
                            int i = Integer.parseInt(currentButton.getId().split(",")[0] + "");
                            int j = Integer.parseInt(currentButton.getId().split(",")[1] + "");

                            if (btnClick == MouseButton.PRIMARY) {
                                // LEFT CLICK - regular click
                                if (mine.open(i, j)) {
                                    setButtonsOpen();
                                    if (mine.isDone()) {
                                        gameOver("win");
                                    }
                                } else {
                                    openAllMines();
                                    gameOver("lose");
                                }
                            }
                            if (btnClick == MouseButton.SECONDARY) {
                                // RIGHT CLICK - to toggle flags.
                                toggleFlag(i, j);
                            }
                        }
                    });
                }
            }

        }

        private void setOnClickBoard() {

        }

        private void gameOver(String result) {
            switch (result) {
                case "win": {
                    createPopUpMessage("Your are the winner !!!", "win");
                }
                break;
                case "lose": {
                    createPopUpMessage("You are a looser !!! Reset or cancel..", "lose");
                }
                break;
            }
            if (yes_no_FLAG) {
                createNewGame();
            }

        }

        private void createPopUpMessage(String messege, String type) {
            // return true to create new game , and false to back.
            yes_no_FLAG = false;

            final Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UNDECORATED);

            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setAlignment(Pos.CENTER);
            dialogVbox.setId("dialog");

            dialogVbox.getChildren().add(new Text(messege));

            Button btnYes = new Button("Ok");
            btnYes.setPrefSize(70, 40);
            btnYes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    yes_no_FLAG = true;
                    dialog.close();
                }
            });

            dialogVbox.getChildren().add(btnYes);

            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();

        }

        private void openAllMines() {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Point currentPoint = mine.getBoard()[i][j];
                    if (currentPoint.isMine()) {
                        buttonsBoard[i][j].setText("X");
                        buttonsBoard[i][j].setStyle("" + "-fx-background-color:red");
                    }
                }
            }
        }

    }
}
