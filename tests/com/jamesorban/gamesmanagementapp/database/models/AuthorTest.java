package com.jamesorban.gamesmanagementapp.database.models;

import javafx.scene.layout.Pane;
import org.junit.Before;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AuthorTest {
    private Author author;
    private Pane pane = new Pane();

    @Before
    public void setUp() {
        author = new Author(1, "orban", "minesweeper");
    }

    @Test
    void setIdInvalid() {
        try{
            author.setId(12);
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
    public void testSetId(){
        author.setId(10);
        int expResult = 12;
        assertEquals(expResult, author.getId());
    }
    @Test
    void setNameInvalid() {
        try{
            author.setName("james123");
            fail("integer value in name should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught integer value in setName():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setName with integer argument");
        }
    }
    @org.junit.Test
    public void testSetName(){
        author.setName("james");
        String expResult = "james";
        assertEquals(expResult, author.getName());
    }
}