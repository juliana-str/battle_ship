package battleship;

import java.util.*;

public class BattleBoard {
    private Ship[][] battleBoard;
    private String playerName;
    private static Map<String, List<Coordinates>> shipCoordinates = new HashMap<>();

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

    public static void fullShipCoordinates(String shipType, List<Coordinates> coordinatesList) {
        shipCoordinates.put(shipType, coordinatesList);
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

    public boolean isKilledShip(Coordinates coordinates, String shipType) {
        if (Objects.equals(shipType, "SINGLE_DECK_SHIP")) {
            return true;
        }
        for (int count = 1; shipCoordinates.containsKey(shipType + count); count++) {
            List<Coordinates> values = shipCoordinates.get(shipType + count);
            if (values.contains(coordinates)) {
                for (Coordinates coords : values) {
                    if (!(this.battleBoard[coords.getX()][coords.getY()] == Ship.FIRE)) {
                        return false;
                    }
                }
                return true;
            }
        }
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
