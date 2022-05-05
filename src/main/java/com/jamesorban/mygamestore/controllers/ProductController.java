package com.jamesorban.mygamestore.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.jamesorban.mygamestore.modelFx.GameFx;
import com.jamesorban.mygamestore.modelFx.ProductModel;
import com.jamesorban.mygamestore.utils.DialogUtils;
import com.jamesorban.mygamestore.utils.exceptions.ApplicationException;

public class ProductController {

    @FXML
    private ComboBox<GameFx> gameComboBox;
    @FXML
    private TextArea opinionTextArea;
    @FXML
    private Slider myRatingSlider;
    @FXML
    private Button addProductButton;

    private ProductModel productModel;

    @FXML
    public void initialize() {
        this.productModel = new ProductModel();

        this.gameComboBox.setItems(this.productModel.getGameFxObservableList());

        initBindings();
    }

    private void initBindings() {
        this.productModel.getProductFxObjectProperty().gameFxProperty().bind(this.gameComboBox.valueProperty());
        this.productModel.getProductFxObjectProperty().myOpinionProperty().bind(this.opinionTextArea.textProperty());
        this.productModel.getProductFxObjectProperty().myRatingProperty().bind(this.myRatingSlider.valueProperty());

        this.addProductButton.disableProperty().bind(this.productModel.getProductFxObjectProperty().gameFxProperty().isNull()
                .or(this.productModel.getProductFxObjectProperty().myOpinionProperty().isEmpty()));
    }


    @FXML
    public void backOnClick() {
        closeWindow();
    }

    @FXML
    public void addProductOnClick() {
        try {
            this.productModel.saveProductInDataBase();
            closeWindow();
        } catch (ApplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void closeWindow(){
        Stage stage = (Stage) addProductButton.getScene().getWindow();
        stage.close();
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}
