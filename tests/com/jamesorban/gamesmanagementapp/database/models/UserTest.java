package com.jamesorban.gamesmanagementapp.database.models;

import javafx.scene.layout.Pane;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
private User user;
private Pane pane = new Pane();

    @Before
    public void setUp() throws Exception {
        user = new User("orbanjames","orbanjames5@gmail.com","james123");
    }

    @org.junit.jupiter.api.Test
    void setUserNameInvalid() {
        try{
            user.setUserName("1234567");
            fail("integer value in userName should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught integer value in setUserName():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setUserName with integer argument");
        }
    }
    @org.junit.Test
    public void testSetUserName(){
        user.setUserName("jamesorban");
        String expResult = "jamesorban";
        assertEquals(expResult, user.getUserName());
    }

    @org.junit.jupiter.api.Test
    void setEmailInvalid() {
        try{
            user.setEmail("1234567");
            fail("integer value in email should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught integer value in email():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for email with integer argument");
        }
    }
    @org.junit.Test
    public void testSetEmail(){
        user.setEmail("james@gmail.com");
        String expResult = "james@gmail.com";
        assertEquals(expResult, user.getEmail());
    }


    @org.junit.jupiter.api.Test
    void setPassword() {
        try{
            user.setUserName("1234567");
            fail("boolean value in password should trigger an exception");
        }
        catch (IllegalArgumentException e){
            System.out.println("Properly caught integer value in setBoolean():" + e.getMessage());
        }
        catch (Exception e){
            fail("wrong exception thrown for setPassword with boolean argument");
        }
    }
    @org.junit.Test
    public void testSetPassword(){
        user.setPassword("james123");
        String expResult = "james123";
        assertEquals(expResult, user.getPassword());
    }
}