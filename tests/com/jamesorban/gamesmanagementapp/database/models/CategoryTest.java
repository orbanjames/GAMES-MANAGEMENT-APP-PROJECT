package com.jamesorban.gamesmanagementapp.database.models;

import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;
    private Pane pane = new Pane();

    @Before
    public void setUp() {
        category = new Category(1, "strategy");
    }

    @Test
    void setIdInvalid() {
        try{
            category.setId(5);
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
        category.setId(10);
        int expResult = 12;
        assertEquals(expResult, category.getId());
    }
    @Test
    void setNameInvalid() {
        try{
            category.setName("mfpm");
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
        category.setName("mfpm");
        String expResult = "mfpm";
        assertEquals(expResult, category.getName());
    }
}