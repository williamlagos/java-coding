/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import userinterface.ClientUI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class RemoteClient implements Client {

    private Server battle;
    private int clientUserID;
    private ClientUI ui;

    public RemoteClient(ClientUI userInterfaceChosen) {
        try {
            ui = userInterfaceChosen;
            battle = (Server) Naming.lookup("//localhost/BattleServer");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            ui.output("BattleClient failed:");
        }
    }

    @Override
    public void gameLoop() {
        boolean yourTurn = false;
        opponentName();
        StringBuilder str = new StringBuilder();
        str.append("Type the coordinate (A1,B2,C3... F6):");
        while (true) {
            try {
                printBoard();
                printShoot();
                switch (battle.ehMinhaVez(clientUserID)) {
                    case Server.ERROR:
                        ui.output("Players not ready in this match.");
                        break;
                    case Server.NOT_MY_TURN:
                        ui.output("Not your turn.");
                        yourTurn = false;
                        break;
                    case Server.ITS_MY_TURN:
                        ui.output("It's your turn, shoot to a coordinate.");
                        yourTurn = true;
                        break;
                    case Server.WINNER:
                        ui.output("You win!");
                        break;
                    case Server.LOSER:
                        ui.output("You lose!");
                        break;
                }
                while(!yourTurn){
                    if(battle.ehMinhaVez(clientUserID) == Server.ITS_MY_TURN){
                        ui.output("It's your turn, shoot to a coordinate.");
                        break;
                    }
                }
                ui.output(str.toString());
                executeShoot(ui.input());
            } catch (RemoteException ex) {
                Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void register() {
        try {
            ui.output("Please put your name:");
            String name = ui.input();
            int ret = battle.registraUsuario(name);
            if (ret > 0) {
                clientUserID = ret;
                ui.output("User " + name + " of ID " + ret + " registered.");
            } else {
                if (ret == Server.ALREADY_REGISTERED) {
                    ui.output("User already registered.");
                } else if (ret == Server.ALREADY_FULL) {
                    ui.output("Server too much crowded. Try again another moment.");
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void opponentName() {
        String opponent;
        try {
            opponent = battle.nomeOponente(clientUserID);
            if (opponent.isEmpty()) {
                ui.output("Opponent has yet not found.");
            } else {
                ui.output("Name of your opponent: " + opponent);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void placeShip() {
        try {
            for (int i = 7; i > 0; i--) {
                printBoard();
                ui.output("Coordinate: A1,B2,C3... F6");
                String coord = ui.input();
                ui.output("Alignment - 0 (Horizontal) / 1 (Vertical)\n");
                int align = Integer.parseInt(ui.input());
                StringBuilder str = new StringBuilder();
                str.append("Place your ships:\n");
                str.append("1 - Submarine\n2 - Cruiser\n3 - Armorclad\n4 - Aerocarrier\n");
                ui.output(str.toString());
                int type = Integer.parseInt(ui.input());
                int ret = battle.posicionaNavio(clientUserID, coord, align, type);
                switch (ret) {
                    case Server.SUCCESS:
                        ui.output("Ship placed successfully.");
                        break;
                    case Server.USER_NOT_FOUND:
                        ui.output("User not found.");
                        i++;
                        break;
                    case Server.INVALID_COORDINATE:
                        ui.output("Invalid coordinate or positioning.");
                        i++;
                        break;
                    case Server.ALREADY_POSITIONED:
                        ui.output("Ships already positioned.");
                        i++;
                        break;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void executeShoot(String coord) {
        try {
            int ret = battle.executaTiro(clientUserID, coord);
            switch (ret) {
                case Server.SHOT_AT_WATER:
                    ui.output("Shot at water.");
                    break;
                case Server.SHOT_SUCCESS:
                    ui.output("Shot hit in a ship.");
                    break;
                case Server.USER_NOT_FOUND:
                    ui.output("User not found.");
                    break;
                case Server.INVALID_COORDINATE:
                    ui.output("Invalid coordinate.");
                    break;
                case Server.USER_WAITING:
                    ui.output("Not your turn.");
                    break;
                case Server.ALREADY_SHOT:
                    ui.output("Already shot.");
                    break;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printBoard() {
        try {
            ui.output(battle.obtemTabuleiro(clientUserID));
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void placementReady() {
        try {
            ui.output("Waiting for registering and positioning of the opponent.");
            forever:
            while (true) {
                switch (battle.posicionamentoPronto(clientUserID)) {
                    case Server.SHIPS_POSITIONED:
                        ui.output("All ships are positioned.");
                        break forever;
                    case Server.USER_NOT_FOUND:
                        ui.output("User still not found.");
                        break;
                    case Server.AEROCARRIER:
                        ui.output("Aerocarrier (4 positions) is missing.");
                        break;
                    case Server.ARMORCLAD:
                        ui.output("Armorclad (3 positions) is missing.");
                        break;
                    case Server.CRUISER:
                        ui.output("Cruiser (2 positions) is missing.");
                        break;
                    case Server.SUBMARINE:
                        ui.output("Submarine (1 position) is missing.");
                        break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printShoot() {
        try {
            ui.output(battle.obtemLancamentos(clientUserID));
        } catch (RemoteException ex) {
            Logger.getLogger(RemoteClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
