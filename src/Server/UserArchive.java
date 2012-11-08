/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shelterserver;

import Common.Person;
import Common.Person.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author terje
 */
public final class UserArchive {
    private HashMap<Integer, Person> users;
    private HashMap<Person, String> pwd;
    private int lastID;
    
    public UserArchive(){
        users = new HashMap<>();
        pwd = new HashMap<>();
        lastID = 0;
        addAdmin();
        addTestUsers();
    }
    
    public UserArchive(HashMap users, HashMap pwd, int lastID){
        this.users = users;
        this.pwd = pwd;
        lastID = lastID;
    }
    
    public int addUser(String fName, String lName, Role role, String password){
        lastID = lastID+1;
        Person p = new Person(fName, lName, lastID, role);
        users.put(lastID, p);
        pwd.put(p, password);
        return lastID;
    }
    
    public void removeUser(int id){
        Person p = users.get(id);
        users.remove(id);
        pwd.remove(p);
        p = null;
    }
    
    public boolean authenticate(int id, String password){
        Person p = users.get(id);
        if(password.equalsIgnoreCase(pwd.get(p))){
            return true;
        }
        else{return false;}
    }
    
    public HashMap getUsers(){
        return users;
    }
    
    public void addAdmin(){
        addUser("Admin", "", Role.ADMINISTRATOR, "admin");
    }
    
    public void addTestUsers(){
        addUser("Terje", "Wallem", Role.ADMINISTRATOR, "test");
        addUser("Georg", "Reite", Role.SELLER, "lepra");
        addUser("Helen", "Rberg", Role.GUEST, "boobs");
    }
    
    public Role checkRole(int id){
        Person p = users.get(id);
        return p.getRole();
    }
}

    