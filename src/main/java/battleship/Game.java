package battleship;

import battleship.exceptions.CoordinateInputFormatException;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    public static boolean checkShootingCoordinates(String coordinates) {
        try {
            Pattern pattern = Pattern.compile("\\d\\,\\d");
            Matcher matcher = pattern.matcher(coordinates);
            if (!matcher.find()) {
                throw new CoordinateInputFormatException();
            }
            return true;
        } catch (CoordinateInputFormatException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static Coordinates inputCoordinates(Scanner scanner) {
        String newCoordinates = scanner.nextLine();
        if (!checkShootingCoordinates(newCoordinates)) {
            return inputCoordinates(scanner);
        } else {
            String[] coord = newCoordinates.split(",");
            return new Coordinates(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
        }
    }

    public static void shoot(BattleBoard battleBoard, Scanner scaner) {
        Ship[][] shipsOnBoard = battleBoard.getBattleBoard();
        String result = "";
        while (!(Objects.equals(result, "Мимо!")) && battleBoard.isAliveShips()) {
            System.out.println(battleBoard.getPlayerName() + " стреляй. Введи координаты для стрельбы в формате x,y.");
            Coordinates coordinates = inputCoordinates(scaner);
            int x = coordinates.getX();
            int y = coordinates.getY();
            if (shipsOnBoard[x][y] == Ship.POINT | shipsOnBoard[x][y] == Ship.FIRE) {
                System.out.println("Такой выстрел уже был!");
            } else if (shipsOnBoard[x][y] == Ship.EMPTY | shipsOnBoard[x][y] == Ship.AREA) {
                result = "Мимо!";
                shipsOnBoard[x][y] = Ship.POINT;
                System.out.println(result);
            } else if (Objects.equals(shipsOnBoard[x][y].getShipType(), "\uD83D\uDEA2")) {
                result = "Попадание!";
                String shipType = shipsOnBoard[x][y].name();
                shipsOnBoard[x][y] = Ship.FIRE;
                if (battleBoard.isKilledShip(coordinates, shipType)) {
                    result = "Утопил!";
                }
                System.out.println(result);
            }
        }
    }

    public static String startGame(BattleBoard battleBoard1, BattleBoard battleBoard2, Scanner scanner) {
        System.out.println(battleBoard1.getPlayerName() + " стреляй первым.");
        BattleBoard currentBattelBoard = battleBoard1;
        String winner = currentBattelBoard.getPlayerName();
        while (battleBoard1.isAliveShips() && battleBoard2.isAliveShips()) {
            shoot(currentBattelBoard, scanner);
            if (currentBattelBoard.equals(battleBoard1)) {
                currentBattelBoard = battleBoard2;
            } else {
                currentBattelBoard = battleBoard1;
            }
        }
        return "Победил " + winner + "! Примите поздравления! ✨✨✨✨✨";
    }
}
