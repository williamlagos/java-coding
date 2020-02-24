/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author Eduardo
 */
public interface Client {
    void gameLoop();
    void register();
    void opponentName();
    void placeShip();
    void placementReady();
    void printBoard();
    void printShoot();
    void executeShoot(String coord);
}
