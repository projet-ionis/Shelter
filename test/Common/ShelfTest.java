/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author terje
 */
public class ShelfTest {
    Shelf shelf;
    public ShelfTest() {
    }
    
    @Before
    public void setUp() {
        shelf = new Shelf(1);
        
    }
    
    @After
    public void tearDown() {
        shelf = null;
    }

    /**
     * Test of addGood method, of class Shelf.
     */
    @Test
    public void testAddGood() {
        Good good = new Good("pencil", 1);
        shelf.addGood(good, 3);
        assertTrue(shelf.shelfContainsGood(1));
    }

    /**
     * Test of removeGood method, of class Shelf.
     */
    @Test
    public void testRemoveGood() throws StorageException {
        Good good = new Good("Pen", 2);
        shelf.addGood(good, 9);
        shelf.removeGood(good);
        assertFalse(shelf.shelfContainsGood(2));
    }

    /**
     * Test of getGoodFromArticleNr method, of class Shelf.
     */
    @Test
    public void testGetGoodFromArticleNr() throws Exception {
        Good good = new Good("book", 3);
        shelf.addGood(good, 4);
        assertTrue(shelf.getGoodFromArticleNr(3) == good);
    }

    /**
     * Test of getContent method, of class Shelf.
     */
    @Test
    public void testGetContent() {
        Good good = new Good("notepad", 4);
        shelf.addGood(good, 2);
        HashMap map = shelf.getContent();
        assertTrue(map.containsKey(good));
    }

    /**
     * Test of getShelfNr method, of class Shelf.
     */
    @Test
    public void testGetShelfNr() {
        assertTrue(shelf.getShelfNr() == 1);
    }

    /**
     * Test of shelfContainsGood method, of class Shelf.
     */
    @Test
    public void testShelfContainsGood_String() {
        Good good = new Good("stapler", 5);
        shelf.addGood(good, 34);
        assertTrue(shelf.shelfContainsGood("stapler"));
    }

    /**
     * Test of shelfContainsGood method, of class Shelf.
     */
    @Test
    public void testShelfContainsGood_Good() {
        Good good = new Good("sharpener", 6);
        shelf.addGood(good, 2);
        assertTrue(shelf.shelfContainsGood(6));
    }

    /**
     * Test of updateStock method, of class Shelf.
     */
    @Test
    public void testUpdateStock() {
        Good good = new Good("eraser", 7);
        shelf.addGood(good, 8);
        shelf.updateStock(good, 98);
        assertTrue(shelf.getInstock(good) == 98);
    }

    /**
     * Test of addStock method, of class Shelf.
     */
    @Test
    public void testAddStock() {
        Good good = new Good("pencilcase", 8);
        shelf.addGood(good, 3);
        shelf.addStock(good, 27);
        assertTrue(shelf.getInstock(good) == 30);
    }

    /**
     * Test of removeStock method, of class Shelf.
     */
    @Test
    public void testRemoveStock() throws StorageException {
        Good good = new Good("lead", 9);
        shelf.addGood(good, 98);
        shelf.removeStock(good, 90);
        assertTrue(shelf.getInstock(good) == 8);;
    }
    
    /**
     * Test of Constructor, of class Shelf.
     * Tests the constructor by using a negative argument. Expected result is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_NegativeShelfNr(){
        shelf = null;
        shelf = new Shelf(-3);
    }
    
    /**
     * Test of addGood methos, of class Shelf.
     * Tests the addGood method by using a null value for the Good argument. Expected result is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testaddGood_NullGood(){
        Good good = new Good(null, 4); //The error is caught in the Good class, so it's not neccesary to catch it in the addBook method.
    }
    
    /**
     * Test of addGood method, of class Shelf.
     * Tests the addGood method by using a negative value for the inStock argument. Expected result is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testaddGood_NegativeStock(){
        Good good = new Good("laptop", 10);
        shelf.addGood(good, -21);
    }
    
    /**
     * Test of the removeGood method, of class Shelf.
     * Tests the removeGood method by using a null value for the Good argument. Expected result is a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveGood_NullGood() throws StorageException{
        Good good = new Good("charger", 11);
        shelf.addGood(good, 23); //Adding a good to the map so that it won't be empty during the test.
        shelf.removeGood(null);
    }
    
    /**
     * Test of the removeGood method, of class Shelf.
     * Tests the removeGood method by trying to remove a good not registered in the shelf. 
     */
    @Test(expected = StorageException.class)
    public void testRemoveGood_NotInMap() throws StorageException{
        Good good = new Good("compact disc", 12);
        shelf.addGood(good, 87); //Adding a good to the map so that it won't be empty during the test.
        Good altgood = new Good("snøscooter", 1);
        shelf.removeGood(altgood);
    }
    
    /**
     * Test of the getGoodFromArticleNr method, of class Shelf.
     * Tests the getGoodFromArticleNr by entering a negative article number. Expected result is an IllegalArgumentException.
     * @throws StorageException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetGoodFromArticleNr_NegativeArticleNr() throws StorageException{
        Good good = new Good("DVD", 13);
        shelf.addGood(good, 42);
        shelf.getGoodFromArticleNr(-9000);
    }
    
    /**
     * Test of getGoodFromArticleNr method, of class Shelf.
     * Tests the getGoodFromArticleNr by entering the article number of a good that is not in the shelf. Expected result is a StorageException.
     * @throws StorageException 
     */
    @Test(expected = StorageException.class)
    public void testGetGoodFromArticleNr_WrongArticleNr() throws StorageException{
        Good good = new Good("schoolbag", 14);
        shelf.addGood(good, 65);
        shelf.getGoodFromArticleNr(4);
    }
    
    /**
     * Test of shelfContainsGood_String method, of class Shelf.
     * Tests the shelfContainsGood_String method by checking for goods with an empty string for name. Expected result is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testShelfContainsGood_String_EmptyString(){
        Good good = new Good("water bottle", 15);
        shelf.addGood(good, 32);
        shelf.shelfContainsGood("");
    }
    
    /**
     * Test of shelfContainsGood_String method, of class Shelf.
     * Tests the shelfcontainsGood_String method by entering a null value in place of the name. Expected result is a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testShelfContainsGood_String_nullString(){
        Good good = new Good("lunch", 16);
        shelf.addGood(good, 16);
        String s = null;
        shelf.shelfContainsGood(s);
    }
    
    /**
     * Test of updateStock method, of class Shelf.
     * Tests the updateStock method by entering a negative value for the new stock. Expected result is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateStock_negativeStock(){
        Good good = new Good("stun gun", 18);
        shelf.addGood(good, 2764);
        shelf.updateStock(good, -2);
    }
    
    /**
     * Test of updateStock method, of class Shelf.
     * Tests the updateStock method by entering a null value in place of the good argument. Expected result is a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testUpdateStock_nullGood(){
        Good god = new Good("baseball bat", 19);
        shelf.addGood(god, 23);
        shelf.updateStock(null, 23);
    }
    
    /**
     * Test of addStock method, of class Shelf.
     * Tests the addStock method by trying to add a negative integer(it is not possible to add a negative value using this method. Use the
     * removeStock method instead.). Expected result is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddStock_negativeAddition(){
        Good good = new Good("Dalai Lama", 20);
        shelf.addGood(good, 1);
        shelf.addStock(good, -2);
    }
    
    /**
     * Test of addStock method, of class Shelf.
     * Tests the addStock method by entering a null value in place of the good argument. Expected result is a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testAddStock_nullGood(){
        Good good = new Good("lacking imagination", 21);
        shelf.addGood(good, 0);
        Good altgood = null;
        shelf.addStock(altgood, 3);
    }
    
    /**
     * Test of removeStock method, of class Shelf.
     * Tests the removeStock method by entering a null value in place of the good argument. Expected result is a NullPointerException.
     * @throws StorageException 
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveStock_nullGood() throws StorageException{
        Good good = new Good("bleh", 22);
        shelf.addGood(good, 3);
        Good altgood = null;
        shelf.removeStock(altgood, 3);
    }
    
    /**
     * Test of removeStock method, of class Shelf.
     * Tests the removeStock method by entering a negative value for the subtractant(the number to subtract). Expected result is an IllegalArgumentException.
     * @throws StorageException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveStock_negativeArgument() throws StorageException{
        Good good = new Good("pants", 23);
        shelf.addGood(good, 2);
        shelf.removeStock(good, -3);
    }
    
    /**
     * Test of removeStock method, of class Shelf.
     * Tests the removeStock method by entering a value which insures a negative stock value. Expected result is a StorageException.
     * @throws StorageException 
     */
    @Test(expected = StorageException.class)
    public void testRemoveStock_negativeResult() throws StorageException{
        Good good = new Good("shirt", 24);
        shelf.addGood(good, 3);
        shelf.removeStock(good, 4);
    }
}
