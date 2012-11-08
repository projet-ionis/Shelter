/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.io.Serializable;

/**
 *
 * @author terje
 */
public class Person implements Serializable {
    private String fName;
    private String lName;
    private int id;
    public enum Role{PACKER, SELLER, GUEST, ADMINISTRATOR}
    Role role;

    public Person(String fName, String lName, int id, Role role) {
        this.fName = fName;
        this.lName = lName;
        this.id = id;
        this.role = role;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }
    
    
    
}
