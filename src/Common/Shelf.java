/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author terje
 */
public class Shelf implements Serializable {
    
    private int shelfNr;
    private HashMap<Good, Integer> content;

    public Shelf(int shelfNr) {
        if(shelfNr < 0){
            throw new IllegalArgumentException();
        }
        else{
        this.shelfNr = shelfNr;
        content = new HashMap<>();
        }
    }
    
    public void addGood(Good good, int inStock){
        if(inStock >= 0){
            content.put(good, new Integer(inStock));
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public void removeGood(Good good) throws StorageException{
        if(good == null){
            throw new NullPointerException();
        }
        else if(!content.containsKey(good)){
            throw new StorageException();
        }
        else{
            content.remove(good);
        }
    }
    
    public Good getGoodFromArticleNr(int articleNr) throws StorageException{
        if(articleNr >= 0){
        Good retval = null;
        for(Good g : content.keySet()){
            if(g.getArticleNr() == articleNr){
                retval = g;
                break;
            }
        }
        if(retval != null){
            return retval;
        }
        else{
            throw new StorageException();
        }
    }
        else{
            throw new IllegalArgumentException();
        }
    }
    
    public HashMap getContent(){
        return content;
    }
    
    public int getShelfNr(){
        return shelfNr;
    }
    
    public boolean shelfContainsGood(String name){
        if(name.equalsIgnoreCase("")){
            throw new IllegalArgumentException();
        }
        else{
            boolean retval = false;
        for(Good g : content.keySet()){
            if(g.getName().equalsIgnoreCase(name)){
                retval = true;
                break;
            }
        }
        return retval;
        }
    }
    
    public boolean shelfContainsGood(int i){
        if(i < 0){
            throw new IllegalArgumentException();
        }
        else{
        boolean retval = false;
        for(Good g : content.keySet()){
            if(g.getArticleNr() == i){
                retval = true;
                break;
            }
        }
        return retval;
        }
    }
    
    public void updateStock(Good good, int newStock){
        if(newStock < 0){
            throw new IllegalArgumentException();
        }
        else if(good == null){
            throw new NullPointerException();
        }
        else{
        content.remove(good);
        content.put(good, newStock);
        }
    }
    
    public void addStock(Good good, int addition){
        if(addition < 0){
            throw new IllegalArgumentException();
        }
        else if(good == null){
            throw new NullPointerException();
        }
        else{
            int oldStock = content.get(good);
            content.remove(good);
            content.put(good, oldStock + addition);
        }
    }
    
    public void removeStock(Good good, int sub) throws StorageException{
        if(good == null){                           //Testing whether the good is initialized.
            throw new NullPointerException();
        }
        else if(sub < 0){                           //Testing for negative input value.
            throw new IllegalArgumentException();
        }
        else{
            int oldStock = content.get(good);
            if(oldStock - sub < 0){                 //Testing whether the result would become negative.
                throw new StorageException();
            }
            else{
                content.remove(good);               //Removing the old entry. Variables are stored locally.
                content.put(good, oldStock - sub);  //Adding a new entry with the old good, and the updated stock.
            }
        }
    }
    
    public int getInstock(Good good){
        return content.get(good);
    }
    
}
