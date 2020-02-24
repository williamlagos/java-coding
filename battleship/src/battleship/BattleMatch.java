/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.ArrayList;

/**
 *
 * @author Eduardo
 */
public class BattleMatch implements Match {
    private ArrayList<Player> players;
    private int turn;
    private class Player {
        private int hits;
        private int[][] board;
        private int[][] shoot;
        private int portaAvioes;
        private int encouracados;
        private int cruzadores;
        private int submarinos;
        private final int number;
        private final int id;
        public Player(int number,int id) {
            hits = 14;
            this.id = id;
            this.number = number;
            board = new int[6][6];
            shoot = new int[6][6];
            encouracados = 1;
            portaAvioes = 1;
            cruzadores = 2;
            submarinos = 3;
        }
        @Override
        public String toString(){
            StringBuilder str = new StringBuilder();
            str.append("Player number ").append(number).append(" of ID ").append(id);
            return str.toString();
        }
    }
    
    public BattleMatch() {
        turn = 1;
        players = new ArrayList<>();
    }
    
    @Override
    public boolean itsMyTurn(int userID){
        return getPlayer(1).id == userID && turn == 1 ||
               getPlayer(2).id == userID && turn == 2;
    }
    
    @Override
    public int loserOrWinner(int userID){
        if(getPlayerByID(userID).hits <= 0) return 0;
        else if(getPlayerByID(getOpponent(userID)).hits <= 0) return 1;
        return -1;
    }
    
    public String getPlayers(){
        StringBuilder str = new StringBuilder();
        for(Player p: players) str.append(p.toString()).append("\n");
        return str.toString();
    }
    
    @Override
    public boolean containsPlayer(int userID){
        Player p = null;
        if(getPlayer(1) != null) p = getPlayer(1);
        else if(getPlayer(2) != null) p = getPlayer(2);
        return p != null;
    }

    @Override
    public boolean playersReady(){
        return players.size() == 1;
    }
    
    public boolean playersEntered(){
        return players.size() == 2;
    }
    
    @Override
    public int getOpponent(int userID){
        if(playersEntered()){
            if(getPlayer(1).id == userID) return getPlayer(2).id;
            else if(getPlayer(2).id == userID) return getPlayer(1).id;
        }
        return -1;
    }

    private Player getPlayer(int number) {
        if(players.size() > 0){
            if(number == 1) return players.get(0);
            else if(number == 2) return players.get(1);
        }
        return null;
    }
    
    private Player getPlayerByID(int userID){
        if(userID == getPlayer(1).id) return getPlayer(1);
        else return getPlayer(2);
    }
    
    @Override
    public void addPlayer(int playerID){
        Player p;
        if(players.size() >= 1) p = new Player(2,playerID);
        else p = new Player(1,playerID);
        players.add(p);
    }
    
    @Override
    public int getPortaAvioes(int number) {
        return getPlayer(number).portaAvioes;
    }

    @Override
    public int getEncouracados(int number) {
        return getPlayer(number).encouracados;
    }

    @Override
    public int getCruzadores(int number) {
        return getPlayer(number).cruzadores;
    }

    @Override
    public int getSubmarinos(int number) {
        return getPlayer(number).submarinos;
    }

    @Override
    public int placeShip(int userID, String position, int alignment, int type) {
        String letter = position.charAt(0) + "";
        int intLetter = convertLetter(letter);
        int number = Integer.parseInt(position.charAt(1) + "") - 1;
        if (intLetter < 0 || intLetter > 5 || number < 0 || number > 5) return -2;
        Player p = getPlayerByID(userID);
        if (verifyPlace(number, intLetter, alignment, type, p.board)) return -2;
        if (verifyCount(p,type)) return - 3;
        if (alignment == 0) {
            for (int i = number; i < number + type; i++) {
                p.board[intLetter][i] = type;
                if ((intLetter - 1) >= 0) p.board[intLetter - 1][i] = 9;
                if ((intLetter + 1) <= 5) p.board[intLetter + 1][i] = 9;
                if (i == number && (i - 1) >= 0) p.board[intLetter][i - 1] = 9;
                else if (i == number + type - 1 && (i + 1) <= 5) p.board[intLetter][i + 1] = 9;
            }
            return 0;
        } else if (alignment == 1) {
            for (int i = intLetter; i < intLetter + type; i++) {
                p.board[i][number] = type;
                if ((number - 1) >= 0) p.board[i][number - 1] = 9;
                if ((number + 1) <= 5) p.board[i][number + 1] = 9;
                if (i == intLetter && (i - 1) >= 0) p.board[i - 1][number] = 9;
                else if (i == intLetter + type - 1 && (i + 1) <= 5) p.board[i + 1][number] = 9;
            }
            return 0;
        }
        return -2;
    }

    private boolean verifyPlace(int number, int intLetter, int alignment, int type, int board[][]) {
        if (alignment == 0) {
            for (int i = number; i < number + type; i++) {
                if (board[intLetter][i] == 9 || board[intLetter][i] == 1) {
                    return true;
                }
            }
            return false;
        } else if (alignment == 1) {
            for (int i = intLetter; i < intLetter + type; i++) {
                if (board[i][number] == 9 || board[intLetter][i] == 1) {
                    return true;
                }
            }
            return false;
        }
        return false;
    } 

    @Override
    public boolean verifyCount(int userID, int type) {
        return verifyCount(getPlayerByID(userID),type);
    }
    
    /**
     *
     * @param p
     * @param type
     * @return
     */
    private boolean verifyCount(Player p,int type) {
        switch (type) {
            case 4:
                p.portaAvioes -= 1;
                return (p.portaAvioes < 0);
            case 3:
                p.encouracados -= 1;
                return (p.encouracados < 0);
            case 2:
                p.cruzadores -= 1;
                return (p.cruzadores < 0);
            case 1:
                p.submarinos -= 1;
                return (p.submarinos < 0);
            default:
                return false;
        }
    }

    @Override
    public int executeShoot(int userID, String coord) {
        return executeShoot(getPlayerByID(userID),coord);
    }
    
    private int executeShoot(Player p,String coord) {
        if(turn == 1) turn = 2;
        else if(turn == 2) turn = 1;
        String letter = coord.charAt(0) + "";
        int l = convertLetter(letter);
        int n = Integer.parseInt(coord.charAt(1) + "") - 1;
        if(l < 0 || l > 5 || n < 0 || n > 5) return -2;
        Player o = getPlayerByID(getOpponent(p.id));
        /* Local com tiro ja efetuado */ 
        if (p.shoot[l][n] == 1 || p.shoot[l][n] == 2)
            return -4;
        /* Atingiu agua */
        if (o.board[l][n] == 9 || o.board[l][n] == 0) {
            o.board[l][n] = -2;
            p.shoot[l][n] = 2;
            return 0;
        }
        /* Atingiu navio */
        if (o.board[l][n] == 1 || o.board[l][n] == 2
         || o.board[l][n] == 3 || o.board[l][n] == 4) {
            o.board[l][n] = -1;
            p.shoot[l][n] = 1;
            o.hits--;
            return 1;
        }
        return 0;
    }

    @Override
    public int convertLetter(String letter) {
        switch (letter) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            default:
                return -1;
        }
    }

    @Override
    public String printBoard(int userID) {
        StringBuilder str = new StringBuilder();
        Player p = getPlayerByID(userID);
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++){
                switch(p.board[i][j]){
                    case 0:
                        str.append(".");
                        break;
                    case -1: 
                        str.append("*");
                        break;
                    case -2: 
                        str.append("O");
                        break;
                    case 9:
                        str.append(".");
                        break;
                    default: 
                        str.append("X");
                        break;
                }
                if(j == 5) str.append("\n");
            }
        return str.toString();
    }
    
    @Override
    public String printShoot(int userID) {
        int count = 0;
        Player p = getPlayerByID(userID);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++){
                switch(p.shoot[i][j]){
                    case 0:
                        str.append("?");
                        break;
                    case 1:
                        str.append("*");
                        break;
                    case 2:
                        str.append(".");
                        break;
                }
                if(j == 5) str.append("\n");
            }
        return str.toString();
    }
}
