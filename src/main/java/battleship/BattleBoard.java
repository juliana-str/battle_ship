package battleship;

import java.util.Objects;

public class BattleBoard {
    private Ship[][] battleBoard;
    private String playerName;

    public BattleBoard(Ship[][] battleBoard, String playerName) {
        this.battleBoard = battleBoard;
        this.playerName = playerName;
    }

    public Ship[][] getBattleBoard() {
        return this.battleBoard;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean isAliveShips() {
        for (Ship[] shipsOnBoard : this.battleBoard) {
            for (Ship ship : shipsOnBoard) {
                if (Objects.equals(ship.getShipType(), "\uD83D\uDEA2")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isKilledShip(int[] coordinates) {
        if (this.battleBoard[coordinates[0]][coordinates[1]] == Ship.SINGLE_DECK_SHIP) {
            return true;
        }
//        else if() // ToDo
        return false;
    }

    public void print() {
        for (Ship[] shipsOnBoard : battleBoard) {
            for (Ship ship : shipsOnBoard) {
                System.out.print(ship);
            }
            System.out.println();
        }
    }
}
