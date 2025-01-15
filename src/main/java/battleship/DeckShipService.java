package battleship;

import battleship.exceptions.*;

import java.util.*;

public class DeckShipService {
    private static Map<Integer, String[]> shipTypesMap = new HashMap<>();

    public static void fullShipTypesMap() {
        shipTypesMap.put(4, new String[]{"x1,y1;x2,y2;x3,y3;x4,y4", "четырех", "1"});
        shipTypesMap.put(3, new String[]{"x1,y1;x2,y2;x3,y3", "трех", "2"});
        shipTypesMap.put(2, new String[]{"x1,y1;x2,y2", "двух", "3"});
        shipTypesMap.put(1, new String[]{"x1,y1", "одно", "4"});
    }

    private static int[][] checkFormat(String input, int len) {
        int[][] newCoordinates = new int[len][2];
        try {
            if (input.matches("\\w")) {
                throw new CoordinateNumberException();
            }
            String[] coordinates = input.split(";");

            for (int i = 0; i < coordinates.length; i++) {
                if (!coordinates[i].matches("\\d\\,\\d")) {
                    throw new CoordinateInputFormatException();
                }
                int[] newCoordinat = new int[]{
                        Integer.parseInt(coordinates[i].substring(0, 1)),
                        Integer.parseInt(coordinates[i].substring(2))
                };
                if ((newCoordinat[0] > 9 | newCoordinat[0] < 0) && (newCoordinat[1] > 9 | newCoordinat[1] < 0)) {
                    throw new CoordinateNumberException();
                }
                if (!(coordinates.length == len)) {
                    throw new CoordinateInputCount();
                }
                newCoordinates[i] = newCoordinat;
            }
            return newCoordinates;
        } catch (CoordinateInputFormatException | CoordinateInputCount | CoordinateNumberException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private static void createAroundShipArea(Ship[][] ship, int[][] formattedCoordinates) {
        for (int[] coords : formattedCoordinates) {
            if (coords[0] > 0 && (ship[coords[0] - 1][coords[1]] == null)) {
                ship[coords[0] - 1][coords[1]] = Ship.AREA;
            }
            if (coords[1] > 0 && (ship[coords[0]][coords[1] - 1] == null)) {
                ship[coords[0]][coords[1] - 1] = Ship.AREA;
            }
            if (coords[0] < 9 && (ship[coords[0] + 1][coords[1]] == null)) {
                ship[coords[0] + 1][coords[1]] = Ship.AREA;
            }
            if (coords[1] < 9 && (ship[coords[0]][coords[1] + 1] == null)) {
                ship[coords[0]][coords[1] + 1] = Ship.AREA;
            }
            if ((coords[0] > 0 && coords[1] > 0) && (ship[coords[0] - 1][coords[1] - 1] == null)) {
                ship[coords[0] - 1][coords[1] - 1] = Ship.AREA;
            }
            if ((coords[0] < 9 && coords[1] < 9) && (ship[coords[0] + 1][coords[1] + 1] == null)) {
                ship[coords[0] + 1][coords[1] + 1] = Ship.AREA;
            }
            if ((coords[0] > 0 && coords[1] < 9) && (ship[coords[0] - 1][coords[1] + 1] == null)) {
                ship[coords[0] - 1][coords[1] + 1] = Ship.AREA;
            }
            if ((coords[0] < 9 && coords[1] > 0) && (ship[coords[0] + 1][coords[1] - 1] == null)) {
                ship[coords[0] + 1][coords[1] - 1] = Ship.AREA;
            }
        }
    }

    private static boolean checkCoordinatesLocation(int[][] input, Ship[][] ship) {
        try {
            Arrays.stream(input).sorted();
            int x = input[0][0];
            int y = input[0][1];
            for (int[] nums : input)
                if (!(ship[nums[0]][nums[1]] == null)) {
                    if (Objects.equals(ship[nums[0]][nums[1]].getShipType(), "\uD83D\uDEA2")) {
                        throw new BorderViolationError();
                    } else if (Objects.equals(ship[nums[0]][nums[1]].getShipType(), "\uD83D\uDFE6")) {
                        throw new CoordinateAreaException();
                    }
                } else if (!(nums[0] == x) | !(nums[1] == y) && nums[0] - 1 == x | nums[1] - 1 == y) {
//                    x = nums[0];
//                    y = nums[1];
//                    throw new CoordinateLocationError(); //ToDo
                }
            return true;
        } catch (BorderViolationError | CoordinateAreaException exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

//    public static void inputCoordinates(Ship[][] ship, int count, int shipDesk) {
//        while (shipDesk >= 1) {
//            String[] shipType = shipTypesMap.get(shipDesk);
//            while (count <= Integer.parseInt(shipType[2])) {
//                System.out.println("Введите координаты для " + (count) + " " +
//                        shipType[1] + "палубного корабля, формат данных: " + shipType[0]);
//                Scanner scanner = new Scanner(System.in);
//                String coordinates = scanner.nextLine();
//                int[][] formattedCoordinates = checkFormat(coordinates, shipDesk);
//                if (formattedCoordinates == null) {
//                    inputCoordinates(ship, count, shipDesk);
//                } else if (!checkCoordinatesLocation(formattedCoordinates, ship)) {
//                    inputCoordinates(ship, count, shipDesk);
//                }
//                assert formattedCoordinates != null;
//                switch (shipDesk) {
//                    case (4):
//                        createFourDeckShip(ship, formattedCoordinates);
//                    case (3):
//                        createThreeDeckShip(ship, formattedCoordinates);
//                    case (2):
//                        createTwoDeckShip(ship, formattedCoordinates);
//                    case (1):
//                        createSingleDeckShip(ship, formattedCoordinates);
//                }
//                count++;
//            }
//            count = 1;
//            shipDesk--;
//        }
//        createEmpty(ship);
//    }

    public static void inputCoordinates(Ship[][] ship, int count, int shipDesk) {
        createFourDeckShip(ship, new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}});
        createThreeDeckShip(ship, new int[][]{{2, 6}, {2, 7}, {2, 8}});
        createThreeDeckShip(ship, new int[][]{{3, 2}, {4, 2}, {5, 2}});
        createTwoDeckShip(ship, new int[][]{{4, 4}, {4, 5}});
        createTwoDeckShip(ship, new int[][]{{9, 8}, {9, 9}});
        createTwoDeckShip(ship, new int[][]{{7, 2}, {7, 3}});
        createSingleDeckShip(ship, new int[][]{{9, 0}});
        createSingleDeckShip(ship, new int[][]{{2, 0}});
        createSingleDeckShip(ship, new int[][]{{7, 6}});
        createSingleDeckShip(ship, new int[][]{{4, 8}});
        createEmpty(ship);
    }

    public static void createFourDeckShip(Ship[][] ship, int[][] coordinates) {
        for (int[] coordinate : coordinates) {
            ship[coordinate[0]][coordinate[1]] = Ship.FOUR_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinates);
    }

    public static void createThreeDeckShip(Ship[][] ship, int[][] coordinates) {
        for (int[] coordinate : coordinates) {
            ship[coordinate[0]][coordinate[1]] = Ship.THREE_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinates);
    }

    public static void createTwoDeckShip(Ship[][] ship, int[][] coordinates) {
        for (int[] coordinate : coordinates) {
            ship[coordinate[0]][coordinate[1]] = Ship.TWO_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinates);
    }

    public static void createSingleDeckShip(Ship[][] ship, int[][] coordinates) {
        for (int[] coordinate : coordinates) {
            ship[coordinate[0]][coordinate[1]] = Ship.SINGLE_DECK_SHIP;
        }
        createAroundShipArea(ship, coordinates);
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
