package autotests.test_context;

import battleship.Base;
import battleship.Ship;

import java.util.Scanner;

public class ShipsObjects {
    private static Ship[][] ships;


    public static Ship[][] shipCreate() {
        ships = new Ship[10][10];
        String coordinates = "0,0;0,1;0,2;0,3\n2,6;2,7;2,8\n3,2;4,2;5,2\n4,4;4,5\n9,8;9,9\n7,2;7,3\n9,0\n2,0\n7,6\n4,8";
        Scanner scanner = new Scanner(coordinates).useDelimiter("\n");
        Base.createShips(ships, scanner,4, 1);
        scanner.close();
        return ships;
    }
}
