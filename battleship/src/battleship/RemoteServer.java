/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eduardo
 */
public class RemoteServer extends UnicastRemoteObject implements Server {

    private int maxMatches;
    private ArrayList<BattleMatch> matches;
    private HashMap<Integer, String> users;
    private final static Logger log = Logger.getLogger(RemoteServer.class.getName());

    /**
     *
     * @param max
     * @throws RemoteException
     */
    public RemoteServer(int max) throws RemoteException {
        maxMatches = max;
        users = new HashMap<>();
        matches = new ArrayList<>();
        try {
            log.addHandler(new FileHandler("server_log.txt"));
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(RemoteServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @throws RemoteException
     */
    @Override
    public void start() throws RemoteException {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            log.info("RMI registry ready.");
        } catch (RemoteException e) {
            log.warning("RMI registry already running.");
        }
        try {
            Naming.rebind("BattleServer", this);
            log.info("BattleServer is ready.");
        } catch (RemoteException | MalformedURLException e) {
            log.warning("BattleServer failed:");
        }
    }

    @Override
    public int registraUsuario(String nome) throws RemoteException {
        BattleMatch m;
        int count = users.size();
        if (matches.size() > maxMatches) {
            return RemoteServer.ALREADY_FULL;
        } else if (users.containsValue(nome)) {
            return RemoteServer.ALREADY_REGISTERED;
        } else {
            users.put(count, nome);
            if(matches.isEmpty()){
                m = new BattleMatch();
                matches.add(m);
            } else {
                int match = matches.size()-1;
                if(matches.get(match).playersReady()){
                    m = new BattleMatch();
                    matches.add(m);
                }
                m = matches.get(match);
            }
            m.addPlayer(count);
            log.log(Level.INFO, "User {0} registered in ID {1} in match {2}", new Object[]{nome, count, matches.indexOf(m)});
            log.log(Level.INFO, "\nPlayers on this match:\n{0}", new Object[]{m.getPlayers()});
            return count;
        }
    }

    @Override
    public String nomeOponente(int idUsuario) throws RemoteException {
        String name = "";
        BattleMatch m = searchMatch(idUsuario);
        if(m == null) return name;
        int ret = m.getOpponent(idUsuario);
        if(ret == -1) return name;
        else return users.get(ret);
    }

    @Override
    public BattleMatch searchMatch(int userID) throws RemoteException{
        for(BattleMatch m: matches)
            if(m.containsPlayer(userID)) return m;
        return null;
    }
    
    @Override
    public int posicionaNavio(int idUsuario, String coord, int alinha, int tipo) throws RemoteException {
        BattleMatch m = searchMatch(idUsuario);
        if(m == null) return RemoteServer.USER_NOT_FOUND;
        switch(m.placeShip(idUsuario, coord, alinha, tipo)){
            case 0: return RemoteServer.SUCCESS;
            case -2: return RemoteServer.INVALID_COORDINATE;
            case -3: return RemoteServer.ALREADY_POSITIONED;
        }
        return RemoteServer.SUCCESS;
    }

    @Override
    public int posicionamentoPronto(int idUsuario) throws RemoteException {
        int maxPositions = 0;
        boolean ready = true;
        BattleMatch m = searchMatch(idUsuario);
        if(m == null) return RemoteServer.USER_NOT_FOUND;
        for(int i = 4; i >= 1; i--){
            if(!(m.verifyCount(idUsuario,i))){
                maxPositions = i;
                ready = false;
                break;
            }else if(m.playersReady() && m.verifyCount(m.getOpponent(idUsuario),i)){
                ready = false;
                break;
            }else if(!(m.playersReady())){
                ready = false;
                break;
            }
        }
        if(ready) return RemoteServer.SHIPS_POSITIONED;
        else return maxPositions;
    }

    @Override
    public int ehMinhaVez(int idUsuario) throws RemoteException {
        BattleMatch m = searchMatch(idUsuario);
        if(!(m.playersEntered())) return RemoteServer.ERROR;
        if(m.itsMyTurn(idUsuario)) return RemoteServer.ITS_MY_TURN;
        else if(!(m.itsMyTurn(idUsuario)))return RemoteServer.NOT_MY_TURN;
        if(m.loserOrWinner(idUsuario) == 1) return RemoteServer.WINNER;
        else if(m.loserOrWinner(idUsuario) == 0) return RemoteServer.LOSER;
        return 0;
    }
    
    @Override
    public int executaTiro(int idUsuario, String coord) throws RemoteException {
        BattleMatch m = searchMatch(idUsuario);
        if(m == null) return RemoteServer.USER_NOT_FOUND;
        int ret = m.executeShoot(idUsuario, coord);
        switch(ret){
            case 0: return RemoteServer.SHOT_AT_WATER;
            case 1: return RemoteServer.SHOT_SUCCESS;
            case -2: return RemoteServer.INVALID_COORDINATE;
            case -3: return RemoteServer.USER_WAITING;
            case -4: return RemoteServer.ALREADY_SHOT;
            default: return 0;
        }
    }

    @Override
    public String obtemTabuleiro(int idUsuario) throws RemoteException {
        return searchMatch(idUsuario).printBoard(idUsuario);
    }

    @Override
    public String obtemLancamentos(int idUsuario) throws RemoteException {
        return searchMatch(idUsuario).printShoot(idUsuario);
    }

}
