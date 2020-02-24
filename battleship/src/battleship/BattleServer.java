/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.rmi.RemoteException;

/**
 *
 * @author willi_000
 */
public class BattleServer {
    // Programa servidor para o exemplo "Hello, World!"
    public static void main (String[] argv) throws RemoteException {
        Server srv = new RemoteServer(50);
        srv.start();
    }
}
