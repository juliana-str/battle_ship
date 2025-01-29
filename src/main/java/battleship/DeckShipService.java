package battleship;

import battleship.exceptions.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckShipService {
    private static Map<Integer, String[]> shipTypesMap = new HashMap<>();

    public static void fullShipTypesMap() {
        shipTypesMap.put(4, new String[]{"x1,y1;x2,y2;x3,y3;x4,y4", "четырех", "1"});
        shipTypesMap.put(3, new String[]{"x1,y1;x2,y2;x3,y3", "трех", "2"});
        shipTypesMap.put(2, new String[]{"x1,y1;x2,y2", "двух", "3"});
        shipTypesMap.put(1, new String[]{"x1,y1", "одно", "4"});
    }

    private static List<Coordinates> checkFormat(String input, int len) {
        List<Coordinates> coordinatesList = new ArrayList();
        try {
            Pattern pattern = Pattern.compile("\\d\\,\\d");
            Matcher matcher = pattern.matcher(input);
            if (!matcher.find()) {
                throw new CoordinateNumberException();
            }
            String[] inputCoordinates = input.split(";");
            for (String inputCoords : inputCoordinates) {
                if (!inputCoords.matches("\\d\\,\\d")) {
                    throw new CoordinateInputFormatException();
                }
                String[] coord = inputCoords.split(",");
                Coordinates coordinates = new Coordinates(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
                if (coordinates.getX() > 9 || coordinates.getY() > 9 || coordinates.getX() < 0 || coordinates.getY() < 0) {
                    throw new CoordinateNumberException();
                }
                coordinatesList.add(coordinates);
            }
            if (!(inputCoordinates.length == len)) {
                throw new CoordinateInputCount();
            }
            return coordinatesList;
        } catch (CoordinateInputFormatException | CoordinateInputCount | CoordinateNumberException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    private static void createAroundShipArea(Ship[][] ship, List<Coordinates> coordinatesList) {
        for (Coordinates coords : coordinatesList) {
            int x = coords.getX();
            int y = coords.getY();
            if (x > 0 && (ship[x - 1][y] == null)) {
                ship[x - 1][y] = Ship.AREA;
            }
            if (y > 0 && (ship[x][y - 1] == null)) {
                ship[x][y - 1] = Ship.AREA;
            }
            if (x < 9 && (ship[x + 1][y] == null)) {
                ship[x + 1][y] = Ship.AREA;
            }
            if (y < 9 && (ship[x][y + 1] == null)) {
                ship[x][y + 1] = Ship.AREA;
            }
            if ((x > 0 && y > 0) && (ship[x - 1][y - 1] == null)) {
                ship[x - 1][y - 1] = Ship.AREA;
            }
            if ((x < 9 && y < 9) && (ship[x + 1][y + 1] == null)) {
                ship[x + 1][y + 1] = Ship.AREA;
            }
            if ((x > 0 && y < 9) && (ship[x - 1][y + 1] == null)) {
                ship[x - 1][y + 1] = Ship.AREA;
            }
            if ((x < 9 && y > 0) && (ship[x + 1][y - 1] == null)) {
                ship[x + 1][y - 1] = Ship.AREA;
            }
        }
    }

    private static boolean checkCoordinatesLocation(List<Coordinates> coordinatesList, Ship[][] ship) {
        try {
            for (Coordinates coordinates : coordinatesList) {
                Ship currentShip = ship[coordinates.getX()][coordinates.getY()];
                if (!(currentShip == null)) {
                    if (Objects.equals(currentShip.getShipType(), "\uD83D\uDEA2")) {
                        throw new BorderViolationError();
                    } else if (Objects.equals(currentShip.getShipType(), "\uD83D\uDFE6")) {
                        throw new CoordinateAreaException();
                    }
                }
            }
            Comparator<Coordinates> comparatorY = (p1, p2) -> p1.getY() - p2.getY();
            coordinatesList.sort(comparatorY);
            if (coordinatesList.size() > 1) {
                for (int i = 1; i < coordinatesList.size(); i++) {
                    if (!(((coordinatesList.get(i - 1).getX() == coordinatesList.get(i).getX()) &&
                            (coordinatesList.get(i).getY() - coordinatesList.get(i - 1).getY() == 1)) ||
                            (((coordinatesList.get(i - 1).getY() == coordinatesList.get(i).getY()) &&
                                    (coordinatesList.get(i).getX() - coordinatesList.get(i - 1).getX() == 1))))) {
                        throw new CoordinateLocationError();
                    }
                }
            }
            return true;
        } catch (BorderViolationError | CoordinateLocationError | CoordinateAreaException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    public static void inputCoordinates(Ship[][] ship, int count, int shipDesk, Scanner scanner) {
        while (shipDesk >= 1) {
            String[] shipType = shipTypesMap.get(shipDesk);
            while (count <= Integer.parseInt(shipType[2]) && shipDesk > 0) {
                System.out.println("Введите координаты для " + (count) + " " +
                        shipType[1] + "палубного корабля, формат данных: " + shipType[0]);
                String coordinates = scanner.nextLine();
                List<Coordinates> coordinatesList = checkFormat(coordinates, shipDesk);
                if (coordinatesList == null) {
                    inputCoordinates(ship, count, shipDesk, scanner);
                } else if (!checkCoordinatesLocation(coordinatesList, ship)) {
                    inputCoordinates(ship, count, shipDesk, scanner);
                }
                switch (shipDesk) {
                    case (4):
                        createFourDeckShip(ship, coordinatesList);
                        BattleBoard.fullShipCoordinates("FOUR_DECK_SHIP" + count, coordinatesList);
                        break;
                    case (3):
                        createThreeDeckShip(ship, coordinatesList);
                        BattleBoard.fullShipCoordinates("THREE_DECK_SHIP" + count, coordinatesList);
                        break;
                    case (2):
                        createTwoDeckShip(ship, coordinatesList);
                        BattleBoard.fullShipCoordinates("TWO_DECK_SHIP" + count, coordinatesList);
                        break;
                    case (1):
                        createSingleDeckShip(ship, coordinatesList);
                        BattleBoard.fullShipCoordinates("SINGLE_DECK_SHIP" + count, coordinatesList);
                }
                count++;
            }
            count = 1;
            shipDesk--;
        }
        createEmpty(ship);
    }

    public static void createFourDeckShip(Ship[][] ship, List<Coordinates> coordinatesList) {
        for (Coordinates coordinate : coordinatesList) {
            ship[coordinate.getX()][coordinate.getY()] = Ship.FOUR_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinatesList);
    }

    public static void createThreeDeckShip(Ship[][] ship, List<Coordinates> coordinatesList) {
        for (Coordinates coordinate : coordinatesList) {
            ship[coordinate.getX()][coordinate.getY()] = Ship.THREE_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinatesList);
    }

    public static void createTwoDeckShip(Ship[][] ship, List<Coordinates> coordinatesList) {
        for (Coordinates coordinate : coordinatesList) {
            ship[coordinate.getX()][coordinate.getY()] = Ship.TWO_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinatesList);
    }

    public static void createSingleDeckShip(Ship[][] ship, List<Coordinates> coordinatesList) {
        for (Coordinates coordinate : coordinatesList) {
            ship[coordinate.getX()][coordinate.getY()] = Ship.SINGLE_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinatesList);
    }

    public static void createEmpty(Ship[][] ship) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ship[i][j] == null) {
                    ship[i][j] = Ship.EMPTY;
                }
            }
        }
    }
}
