/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Common.Good;
import Common.Person;
import Common.SalesOrder;
import Common.StorageException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import shelterserver.StorageServer;

/**
 *
 * @author terje
 */
public class Client {
    static String uri = "rmi://127.0.0.1/" + StorageServer.class.getName();
    static StorageServer server;
    public static void main(String[] args) throws Exception {
    }
    
    public static void connect() throws Exception{
        server = (StorageServer)Naming.lookup(uri);
    }
    
    public static void disconnect() throws Exception{
        server = null;
    }
    
    public static void setIP(String IP){
        uri = "rmi://" + IP + "/" + StorageServer.class.getName();
    }
    
    public static HashMap getStock() throws RemoteException{
        return server.getGoodsList();
    }
    
    public static String getIP(){
        return uri;
    }
    
    public static int addUser(String fName, String lName, Person.Role role, String password) throws RemoteException{
        return server.newUser(fName, lName, role, password);
    }
    
    public static void removeUser(int id) throws RemoteException{
        server.deleteUser(id);
    }
    
    public static HashMap getUsers() throws RemoteException{
        return server.getUsers();
    }
    
    public static void newGood(String name, int shelfNr, int amount) throws StorageException, RemoteException{
        server.newGood(name, shelfNr, amount);
    }
    
    public static void deleteArticle(Good g) throws RemoteException{
        server.removeGood(g);
    }
    
    public static int newOrder(int user) throws RemoteException{
        return server.newOrder(user);
    }
    
    public static void addArticleToOrder(int articleNr, int amount, int order) throws RemoteException{
        server.addGoodTooOrder(articleNr, amount, order);
    }
    
    public static void removeArticleFromOrder(int articleNr, int order) throws RemoteException, StorageException{
        server.removeGoodFromOrder(articleNr, order);
    }
    
    public static SalesOrder confirmOrder(int order) throws RemoteException{
        return server.confirmOrder(order);
    }
    
    public static void cancelOrder(int order) throws RemoteException{
        server.cancelOrder(order);
    }
    
    public static SalesOrder getOrder(int order) throws RemoteException{
        return server.getOrder(order);
    }
    
    public static boolean authentificate(int userID, String password) throws RemoteException{
        return server.Authenticate(userID, password);
    }
}
