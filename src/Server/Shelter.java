/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Common.StorageException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.Permission;
import shelterserver.StorageServer;

/**
 *
 * @author terje
 */
public class Shelter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException, StorageException {
        System.setSecurityManager(new SecurityManager(){
            @Override
            public void checkPermission(Permission perm) {
            }
            @Override
            public void checkPermission(Permission perm, Object context) {
            }
            @Override
            public void checkConnect(String host, int port) {
            }
            @Override
            public void checkConnect(String host, int port, Object context) {
            }
            @Override
            public void checkAccept(String host, int port) {
            }
        });
        Registry reg = LocateRegistry.createRegistry(1099);
        StorageServerImpl ssi = new StorageServerImpl();
        Remote stub = UnicastRemoteObject.exportObject(ssi, 0);
        Naming.rebind(StorageServer.class.getName(), stub);
        System.out.println("Server is running...");
    }
}
