package com.jamesorban.gamesmanagementapp.database.models;

import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Pane pane = new Pane();

    @Before
    public void setUp() throws Exception {
        game = new Game(1, "minesweeper");
    }

    @Test
    void setInvalidGameId() {
        try{
            game.setId(9);
            fail("string value in id should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught string value in setId():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setId with string argument");
        }
    }
    @org.junit.Test
    public void testSetGameId(){
        game.setId(12);
        int expResult = 12;
        assertEquals(expResult, game.getId());
    }



    @Test
    void setInvalidTitle() {
        try{
            game.setTitle("5067mines");
            fail("integer value in title should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught integer value in setTitle():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setTitle with integer argument");
        }
    }
    @org.junit.Test
    public void testSetTitle(){
        game.setTitle("minesweeper");
        String expResult = "minesweeper";
        assertEquals(expResult, game.getId());
    }

}