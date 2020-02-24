/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author william
 */
public interface Server extends Remote {
    public static final int ALREADY_REGISTERED = -1;
    public static final int ALREADY_FULL = -2;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int SUCCESS = 0;
    public static final int USER_NOT_FOUND = -1;
    public static final int INVALID_COORDINATE = -2;
    public static final int ALREADY_POSITIONED = -3;
    public static final int SHIPS_POSITIONED = 0;
    public static final int ERROR = -1;
    public static final int NOT_MY_TURN = 0;
    public static final int ITS_MY_TURN = 1;
    public static final int WINNER = 2;
    public static final int LOSER = 3;
    public static final int SHOT_AT_WATER = 0;
    public static final int SHOT_SUCCESS = 1;
    public static final int USER_WAITING = -3;
    public static final int ALREADY_SHOT = -4;
    public static final int SUBMARINE = 1;
    public static final int CRUISER = 2;
    public static final int ARMORCLAD = 3;
    public static final int AEROCARRIER = 4;

    int ehMinhaVez(int idUsuario) throws RemoteException;

    int executaTiro(int idUsuario, String coord) throws RemoteException;

    String nomeOponente(int idUsuario) throws RemoteException;

    String obtemLancamentos(int idUsuario) throws RemoteException;

    String obtemTabuleiro(int idUsuario) throws RemoteException;

    int posicionaNavio(int idUsuario, String coord, int alinha, int tipo) throws RemoteException;

    int posicionamentoPronto(int idUsuario) throws RemoteException;

    int registraUsuario(String nome) throws RemoteException;

    BattleMatch searchMatch(int userID) throws RemoteException;

    void start() throws RemoteException;
    
}
