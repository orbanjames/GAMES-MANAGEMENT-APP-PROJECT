package com.jamesorban.gamesmanagementapp.database.models;

import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private Pane pane = new Pane();

    @Before
    public void setUp() throws Exception {
        product = new Product(10, "the best game i have ever played");
    }

    @Test
    void setInvalidMyRating() {
        try{
            product.setMyRating(-5);
            fail("negative integer value in myRating should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught negative integer value in setMyRating():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setMyRating with string argument");
        }
    }
    @org.junit.Test
    public void testSetMyRating(){
        product.setMyRating(8);
        int expResult = 8;
        assertEquals(expResult, product.getMyRating());
    }

    @Test
    void setInvalidMyOpinion() {
        try{
            product.setMyOpinion("5067mines");
            fail("integer value in myOpinion should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught integer value in setMyOpinion():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setMyOpinion with integer argument");
        }
    }
    @org.junit.Test
    public void testSetMyOpinion(){
        product.setMyOpinion("the best game of the century");
        String expResult = "the best game of the century";
        assertEquals(expResult, product.getMyOpinion());
    }

}