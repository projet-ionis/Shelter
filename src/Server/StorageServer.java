/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import Common.Good;
import Common.Order;
import Common.Person;
import Common.SalesOrder;
import Common.StorageException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author terje
 */
public interface StorageServer extends Remote {
    /**
     * Adds a new article to the system.
     * @param name
     * @param shelf
     * @param inStock
     * @throws StorageException
     * @throws RemoteException 
     */
    void newGood(String name, int shelf, int inStock) throws StorageException, RemoteException;
    
    /**
     * Removes a specified article from the system.
     * @param good
     * @throws RemoteException 
     */
    void removeGood(Good good) throws RemoteException;
    
    /**
     * Adds an amount to a specified article.
     * @param good
     * @param addition
     * @throws RemoteException 
     */
    void addStock(Good good, int addition) throws RemoteException;
    
    /**
     * Removes an amount from a specified article.
     * @param good
     * @param sub
     * @throws RemoteException 
     */
    void removeStock(Good good, int sub) throws RemoteException;
    
    /**
     * Checks the id and password of a user.
     * @param id
     * @param password
     * @return
     * @throws RemoteException 
     */
    boolean Authenticate(int id, String password) throws RemoteException;
    
    /**
     * Returns a list of all articles in the system.
     * @return
     * @throws RemoteException 
     */
    HashMap getGoodsList() throws RemoteException;
    
    /**
     * Returns the shelfnumber of a specified article.
     * @param good
     * @return
     * @throws RemoteException 
     */
    int getGoodLocation(Good good) throws RemoteException;
    
    /**
     * Starts a new order.
     * @param userId
     * @return
     * @throws RemoteException 
     */
    int newOrder(int userId) throws RemoteException;
    
    /**
     * Adds an article to a specified order and reserves it.
     * @param articleNr
     * @param amount
     * @param SO
     * @throws RemoteException 
     */
    void addGoodTooOrder(int articleNr, int amount, int SO) throws RemoteException;
    
    /**
     * Removes an article from an order. This method removes the article completely regardless of the amount.
     * @param articleNr
     * @param order
     * @throws RemoteException
     * @throws StorageException 
     */
    void removeGoodFromOrder(int articleNr, int order) throws RemoteException, StorageException;
    
    /**
     * This method closes the order, moves it into an archive and updates the stock.
     * @param order
     * @return
     * @throws RemoteException 
     */
    SalesOrder confirmOrder(int order) throws RemoteException;
    
    /**
     * This method deletes an order, and removes all reservations held by that order.
     * @param order
     * @throws RemoteException 
     */
    void cancelOrder(int order) throws RemoteException;
    
    /**
     * This method adds a new user to the system.
     * @param fName
     * @param lName
     * @param role
     * @param password
     * @return
     * @throws RemoteException 
     */
    int newUser(String fName, String lName, Person.Role role, String password) throws RemoteException;
    
    /**
     * This method removes a user from the system based on his/her user id.
     * @param id
     * @throws RemoteException 
     */
    void deleteUser(int id) throws RemoteException;
    
    /**
     * This method returns a list of all users in the system.
     * @return
     * @throws RemoteException 
     */
    HashMap getUsers() throws RemoteException;
    
    /**
     * This method returns a specified order.
     * @param order
     * @return
     * @throws RemoteException 
     */
    SalesOrder getOrder(int order) throws RemoteException;
    
}
