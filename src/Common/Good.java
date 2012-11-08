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
public class Good implements Serializable {
    private String name;
    private int articleNr;
    private HashMap<Date, Integer> reserved;
    private HashMap<Date, Integer> SOMap;

    public Good(String name, int articleNr) {
        if(name == null | name == "" | articleNr < 0){
            throw new IllegalArgumentException();
        }
        else{
            this.name = name;
            this.articleNr = articleNr;
        }
        reserved = new HashMap();
        SOMap = new HashMap();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null | name == ""){
            throw new IllegalArgumentException();
        }
        else{
            this.name = name;
        }
    }

    public int getArticleNr() {
        return articleNr;
    }

    public void setArticleNr(int articleNr) {
        if(articleNr < 0){
            throw new IllegalArgumentException();
        }
        else{
            this.articleNr = articleNr;
        }
    }
    
    public void reserveGood(int amount, int SO){
        Date timeStamp = new Date();
        reserved.put(timeStamp, amount);
        SOMap.put(timeStamp, SO);
    }
    
    /**
     * 
     * @return the amount of this good that has been freed.
     */
    public int checkReservations() {
        int retval = 0;
        Date refTimeStamp = new Date();
        for (Date d : reserved.keySet()) {
            d.setMinutes(d.getMinutes() - 15);
            if (refTimeStamp.after(d)) {
                retval = retval + reserved.get(d);
                reserved.remove(d);
                SOMap.remove(d);
            }
        }
        return retval;
    }
    
    public void removeReservation(int SO){
        for(Date d : SOMap.keySet()){
            if(SOMap.get(d) == SO){
                reserved.remove(d);
                SOMap.remove(d);
            }
        }
    }

    public HashMap<Date, Integer> getReserved() {
        return reserved;
    }
    
    public int getAmountReserved(){
        int retval = 0;
        for(int i : reserved.values()){
            retval = retval + i;
        }
        return retval;
    }
}
