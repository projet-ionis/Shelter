/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Common.Good;
import Common.Person.Role;
import Common.SalesOrder;
import Common.StorageException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import shelterserver.StorageServer;
import shelterserver.UserArchive;
import shelterserver.Warehouse;

/**
 *
 * @author terje
 */
public class StorageServerImpl implements StorageServer {
    private Warehouse warehouse;
    private UserArchive userArchive;
    private ArrayList<SalesOrder> orderArchive;

    public StorageServerImpl() throws RemoteException, StorageException {
        warehouse = new Warehouse("Aalesund");
        userArchive = new UserArchive();
        orderArchive = new ArrayList<>();
    }
    
    @Override
    public synchronized void newGood(String name, int shelfNr, int inStock) throws StorageException, RemoteException {
        warehouse.newGood(name, shelfNr, inStock);
    }

    @Override
    public synchronized void removeGood(Good good)throws RemoteException {
        try {
            warehouse.removeGood(good.getArticleNr());
        } catch (StorageException ex) {
            Logger.getLogger(StorageServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void addStock(Good good, int addition)throws RemoteException {
        warehouse.addStock(good, addition);
    }

    @Override
    public synchronized void removeStock(Good good, int sub)throws RemoteException {
        try {
            warehouse.subStock(good, sub);
        } catch (StorageException ex) {
            Logger.getLogger(StorageServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized boolean Authenticate(int id, String password)throws RemoteException {
        return userArchive.authenticate(id, password);
    }

    @Override
    public synchronized HashMap getGoodsList()throws RemoteException {
        for(Object o : warehouse.getPrintout().keySet()){
            System.out.println(o);
        }
        return warehouse.getPrintout();
    }

    @Override
    public synchronized int getGoodLocation(Good good)throws RemoteException {
        return warehouse.warehouseContainsGood_shelf(good.getName()).getShelfNr();
    }

    @Override
    public synchronized int newOrder(int userId)throws RemoteException {
        return warehouse.newSalesOrder(userId);
        
    }

    @Override
    public synchronized void addGoodTooOrder(int articleNr, int amount, int SO)throws RemoteException {
        try {
            warehouse.addGoodToSO(articleNr, amount, SO);
        } catch (StorageException ex) {
            Logger.getLogger(StorageServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void removeGoodFromOrder(int articleNr, int order)throws RemoteException, StorageException {
        warehouse.removeGoodFromSO(articleNr, order);
    }

    @Override
    public synchronized SalesOrder confirmOrder(int order)throws RemoteException {
        try {
            SalesOrder s = warehouse.confirmOrder(order);
            orderArchive.add(s);
            return s;
        } catch (StorageException ex) {
            Logger.getLogger(StorageServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public synchronized void cancelOrder(int order)throws RemoteException {
        warehouse.calcelOrder(order);
    }

    @Override
    public synchronized int newUser(String fName, String lName, Role role, String password)throws RemoteException {
        return userArchive.addUser(fName, lName, role, password);
    }

    @Override
    public synchronized void deleteUser(int id)throws RemoteException {
        userArchive.removeUser(id);
    }

    @Override
    public synchronized HashMap getUsers() throws RemoteException {
        return userArchive.getUsers();
    }
    
    @Override
    public synchronized SalesOrder getOrder(int order){
        return warehouse.getOrder(order);
    }
}
