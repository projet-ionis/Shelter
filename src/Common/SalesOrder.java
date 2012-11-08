/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author terje
 */
public class SalesOrder implements Serializable {
    private HashMap<Good, Integer> goods;
    private int userId;
    private Date createdTimeStamp;
    private boolean open;
    private Date finishedTimeStamp;
    private int SO;
    
    public SalesOrder(int userId, int SO){
        this.userId = userId;
        createdTimeStamp = new Date();
        this.SO = SO;
        open = true;
        goods = new HashMap();
    }
    
    public void confirmOrder(){
        open = false;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public HashMap<Good, Integer> getGoods() {
        return goods;
    }

    public int getUserId() {
        return userId;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public Date getFinishedTimeStamp() {
        return finishedTimeStamp;
    }

    public int getSO() {
        return SO;
    }
    
    public void addGood(Good g, int amount){
        goods.put(g, amount);
    }
    
    public void removeGood(Good g){
        goods.remove(g);
    }
    
    public int getAmount(Good g){
        return goods.get(g);
    }
}
