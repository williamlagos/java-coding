/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author william
 */
public interface Match {

    void addPlayer(int playerID);

    boolean containsPlayer(int userID);

    int convertLetter(String letter);

    int executeShoot(int userID, String coord);

    int getCruzadores(int number);

    int getEncouracados(int number);

    int getOpponent(int userID);

    int getPortaAvioes(int number);

    int getSubmarinos(int number);

    boolean itsMyTurn(int userID);

    int loserOrWinner(int userID);

    int placeShip(int userID, String position, int alignment, int type);

    boolean playersReady();

    String printBoard(int userID);

    String printShoot(int userID);

    boolean verifyCount(int userID, int type);
    
}
