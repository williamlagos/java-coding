/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import battleship.RemoteClient;
import userinterface.TextClientUI;

/**
 * @author Eduardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RemoteClient cli = new RemoteClient(new TextClientUI());
        cli.register();
        cli.placeShip();
        cli.placementReady();
        cli.gameLoop();
    }
}
