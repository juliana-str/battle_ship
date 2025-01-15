package battleship;

import battleship.exceptions.CoordinateInputFormatException;

import java.util.Objects;
import java.util.Scanner;

public class Game {

    public static boolean checkShootingCoordinates(String coordinates) {
        try {
            if (coordinates.matches("\\w")) {
                throw new CoordinateInputFormatException();
            }
            return true;
        } catch (CoordinateInputFormatException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static int[] inputCoordinates() {

        Scanner scanner = new Scanner(System.in);
        String coordinates = scanner.nextLine();
        if (!checkShootingCoordinates(coordinates)) {
            inputCoordinates();
        }
        return new int[]{Integer.parseInt(coordinates.substring(0, 1)), Integer.parseInt(coordinates.substring(2))};
    }

    public static void shoot(BattleBoard battleBoard) {
        Ship[][] shipsOnBoard = battleBoard.getBattleBoard();
        String result = "";
        int[] coordinates;
        while (!(Objects.equals(result, "Мимо!"))) {
            System.out.println(battleBoard.getPlayerName() + " стреляй. Введи координаты для стрельбы в формате x,y.");
            coordinates = inputCoordinates();
            if (shipsOnBoard[coordinates[0]][coordinates[1]] == Ship.POINT | shipsOnBoard[coordinates[0]][coordinates[1]] == Ship.FIRE) {
                System.out.println("Такой выстрел уже был!");
            } else if (shipsOnBoard[coordinates[0]][coordinates[1]] == Ship.EMPTY | shipsOnBoard[coordinates[0]][coordinates[1]] == Ship.AREA) {
                result = "Мимо!";
                shipsOnBoard[coordinates[0]][coordinates[1]] = Ship.POINT;
                System.out.println(result);
            } else if (Objects.equals(shipsOnBoard[coordinates[0]][coordinates[1]].getShipType(), "\uD83D\uDEA2")) {
                shipsOnBoard[coordinates[0]][coordinates[1]] = Ship.FIRE;
                result = "Попадание!";
                System.out.println(result);
                if (battleBoard.isKilledShip(coordinates)) {
                    System.out.println("Утопил!");
                }
            }
        }
    }

    public static void startGame(BattleBoard battleBoard1, BattleBoard battleBoard2) {
        System.out.println(battleBoard1.getPlayerName() + " стреляй первым.");
        BattleBoard currentBattelBoard = battleBoard1;
        String winner = currentBattelBoard.getPlayerName();
        while (battleBoard1.isAliveShips() | battleBoard2.isAliveShips()) {
            shoot(currentBattelBoard);
            if (currentBattelBoard.equals(battleBoard1)) {
                currentBattelBoard = battleBoard2;
            } else {
                currentBattelBoard = battleBoard1;
            }
        }
        System.out.println("Победил " + winner + "!");
    }
}
