/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import Common.SalesOrder;
import Common.Good;
import Common.Shelf;
import Common.StorageException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author terje
 */
public final class Warehouse {
    private ArrayList<Shelf> shelves;
    private String warehouseName;
    private int lastArtNr;
    private HashMap<Integer, SalesOrder> salesOrderArchive;
    private int lastSO;
    

    public Warehouse(String warehouseName) throws StorageException {
        this.warehouseName = warehouseName;
        lastArtNr = 0;
        shelves = new ArrayList<>();
        salesOrderArchive = new HashMap();
        lastSO = 0;
        shelves.add(newShelf(1));
        shelves.add(newShelf(2));
        shelves.add(newShelf(3));
        shelves.add(newShelf(4));
        newGood("Hot dogs 400g", 1, 43);
        newGood("beans 2 Oz", 2, 47);
        newGood("Stew 0,5L", 3, 20);
        newGood("WhiteBread 250g", 4, 18);
        newGood("Butter 500g", 5, 23);
        newGood("Milk 0,5L", 6, 10);
        newGood("Yoghurt 0,3L", 7, 87);
        newGood("Ham slices 100g", 8, 40);
        
    }
    
    public void newGood(String name, int shelf, int inStock) throws StorageException{
        if(name != "" | shelf >= 0 | inStock >= 0){
            lastArtNr = lastArtNr+1;
            if(!warehouseContainsGood_check(name)){
                Good good = new Good(name, lastArtNr);
                getShelf(shelf).addGood(good, inStock);
            }
            else{
                throw new StorageException();
            }
    }
        else{
            throw new IllegalArgumentException();
        }
    }

    public String getWarehouseName() {
        return warehouseName;
    }
    
    public boolean warehouseContainsGood_check(String name){
        boolean retval = false;
        for(Shelf s : shelves){
            if(s.shelfContainsGood(name)){
                retval = true;
                break;
            }
        }
        return retval;
    }
    
    public Shelf warehouseContainsGood_shelf(String name){
        Shelf retval = null;
        for(Shelf s : shelves){
            if(s.shelfContainsGood(name)){
                retval = s;
                break;
            }
        }
        return retval;
    }
    
    public Good getGood(int articleNr) throws StorageException{
        for(Shelf s : shelves){
            if(s.shelfContainsGood(articleNr)){
                return s.getGoodFromArticleNr(articleNr);
            }
        }
        return null;
    }
    
    public void removeGood(int articleNr) throws StorageException{
        boolean goodRemoved = false;
        for(Shelf s : shelves){
            if(s.shelfContainsGood(articleNr)){
                s.removeGood(s.getGoodFromArticleNr(articleNr));
                goodRemoved = true;
            }
        }
        if(!goodRemoved){
            throw new IllegalArgumentException();
        }
    }
    
    public void updateStock(Good good, int newStock){
        if(warehouseContainsGood_check(good.getName())){
            for(Shelf s : shelves){
                if(s.shelfContainsGood(good.getArticleNr())){
                    s.updateStock(good, newStock);
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public void addStock(Good good, int addition){
        if(warehouseContainsGood_check(good.getName())){
            for(Shelf s : shelves){
                if(s.shelfContainsGood(good.getArticleNr())){
                    s.addStock(good, addition);
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public void subStock(Good good, int sub) throws StorageException{
        if(warehouseContainsGood_check(good.getName())){
            for(Shelf s : shelves){
                if(s.shelfContainsGood(good.getArticleNr())){
                    s.removeStock(good, sub);
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public Shelf newShelf(int number){
        Shelf shelf = new Shelf(number);
        return shelf;
    }
    
    public Shelf getShelf(int shelfNr){
        Shelf retval = null;
        for(Shelf shelf : shelves){
            if(shelf.getShelfNr() == shelfNr){
                retval = shelf;
            }
        }
        return retval;
    }
    
    public HashMap getPrintout(){
        HashMap retval = new HashMap();
        for(Shelf s : shelves){
            retval.putAll(s.getContent());
        }
        return retval;
    }
    
    public boolean reserveGood(int articleNr, int amount, int SO) throws StorageException{
        for(Shelf s : shelves){
            if(s.shelfContainsGood(articleNr)){
                Good g = s.getGoodFromArticleNr(articleNr);
                Object o = s.getContent().get(g);
                int i = (Integer)o;
                if((i - g.getAmountReserved()) >= amount){
                   g.reserveGood(amount, SO);
                   return true;
                }
            }
        }
        return false;
    }
    
    public void removeReservation(int articleNr, int SO) throws StorageException{
        for(Shelf s : shelves){
            if(s.shelfContainsGood(articleNr)){
                Good g = s.getGoodFromArticleNr(articleNr);
                g.removeReservation(SO);
            }
        }
    }
    
    public void cleanUpWarehouse(){
        for(Shelf s : shelves){
            for(Object o : s.getContent().keySet()){
                Good g = (Good)o;
                g.checkReservations();
            }
        }
    }
    
    public int newSalesOrder(int userId){
        lastSO++;
        SalesOrder SO = new SalesOrder(userId, lastSO);
        salesOrderArchive.put(lastSO, SO);
        return lastSO;
    }
    
    public void addGoodToSO(int articleNr, int amount, int SO) throws StorageException{
        SalesOrder order = salesOrderArchive.get(SO);
        if(order.isOpen() && reserveGood(articleNr, amount, SO)){
            order.addGood(getGood(articleNr), amount);
        }
    }
    
    public void removeGoodFromSO(int articleNr, int SO) throws StorageException{
        SalesOrder order = salesOrderArchive.get(SO);
        if(order.isOpen()){
            order.removeGood(getGood(articleNr));
            removeReservation(articleNr, SO);
        }
    }
    
    public SalesOrder confirmOrder(int SO) throws StorageException{
        SalesOrder so = salesOrderArchive.get(SO);
        HashMap<Good, Integer> hm = so.getGoods();
        for(Good g : hm.keySet()){
            subStock(g, so.getAmount(g));
            removeReservation(g.getArticleNr(), so.getSO());
        }
        so.confirmOrder();
        return so;
    }
    
    public void calcelOrder(int SO){
        salesOrderArchive.remove(SO);
    }
    
    public SalesOrder getOrder(int SO){
        return salesOrderArchive.get(SO);
    }
}
