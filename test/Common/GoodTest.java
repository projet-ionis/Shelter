/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author terje
 */
public class GoodTest {
    Good good1;
    
    public GoodTest() {
    }
    
    @Before
    public void setUp() {
        good1 = new Good("blyant", 19);
    }
    
    @After
    public void tearDown() {
        good1 = null;
    }

    /**
     * Test of getName method, of class Good.
     */
    @Test
    public void testGetName() {
        assertTrue(good1.getName().equalsIgnoreCase("blyant"));
    }

    /**
     * Test of setName method, of class Good.
     */
    @Test
    public void testSetName() {
        good1.setName("viskelær");
        assertTrue(good1.getName().equalsIgnoreCase("viskelær"));
    }

    /**
     * Test of getArticleNr method, of class Good.
     */
    @Test
    public void testGetArticleNr() {
        assertTrue(good1.getArticleNr() == 19);
    }

    /**
     * Test of setArticleNr method, of class Good.
     */
    @Test
    public void testSetArticleNr() {
        good1.setArticleNr(3);
        assertTrue(good1.getArticleNr() == 3);
    }
    
    /**
     * Test of the constructor, of class Good.
     * Test which creates a new Good object with an empty string for the name argument.
     * The program should catch this and throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptystring(){
        good1 = null;
        good1 = new Good("", 4); //The good name should not be able to be an empty string. Expected IllegalArgumentException.
    }
    
    /**
     * Test of the constructor, of class Good.
     * Test which creates a new Good object with null as the name argument.
     * The program should catch this and throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructornullString(){
        good1 = null;
        good1 = new Good(null, 4); //The good name should not be able to be null. Expected IllegalArgumentException.
    }
    
    /**
     * Test of the Constructor, of class Good.
     * Test which creates a new Good object using a negative integer value as the articleNumber argument.
     * The program should catch this and throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeArticleNumber(){
        good1 = null;
        good1 = new Good("penn", -3); //The article should not be able to be negative. Expected IllegalArgumentException.
    }
    
    /**
     * Test of the setName method, of class Good.
     * Test which sets a new name of a Good object using the setName method. The new name entered is an empty string.
     * The program should catch this and throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNameEmptyString(){
        good1.setName(""); //The name should not be able to be an empty string. Expected IllegalArgumentException.
    }
    
    /**
     * Test of the setName method, of class Good.
     * Test which sets a new name of a Good object using the setName method. The new name entered is null.
     * The program should catch this and throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNameNullString(){
        good1.setName(null);
    }
    
    /**
     * Test of the setArticleNr method, of class Good.
     * Test which sets a new article number of a Good objectusing the setArticleNr method. The new article number is a negative integer value.
     * The program should catch this and throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetArticleNrNegativeValue(){
        good1.setArticleNr(-4);
    }
}
