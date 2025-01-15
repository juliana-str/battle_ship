package battleship;


import java.util.Scanner;

public class Base {

    public static Ship[][] createShips(Ship[][] somePlayerShips) {
        DeckShipService.fullShipTypesMap();
        DeckShipService.inputCoordinates(somePlayerShips, 1, 4);
        return somePlayerShips;
    }

    public static String addPlayers() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println("Начинаем игру Морской бой. Введите имя первого игрока.");
        String firstPlayer = addPlayers();
        System.out.println("Введите имя второго игрока.");
        String secondPlayer = addPlayers();
        System.out.println("Поочередно игроки введят координаты своих кораблей.");
        System.out.println("Начинает " + firstPlayer + ", " + secondPlayer + " не подглядывает!");
        Ship[][] firstPlayerShips = new Ship[10][10];
        BattleBoard battleBoard1 = new BattleBoard(createShips(firstPlayerShips), firstPlayer);
        battleBoard1.print();
        System.out.println("Теперь " + secondPlayer + ", " + firstPlayer + " не подглядывает!");
        Ship[][] secondPlayerShips = new Ship[10][10];
        BattleBoard battleBoard2 = new BattleBoard(createShips(secondPlayerShips), secondPlayer);
        battleBoard2.print();
        Game.startGame(battleBoard1, battleBoard2);
    }
}


// 0,0;0,1;0,2;0,3 -4x
// 2,6;2,7;2,8
// 3,2;4,2;5,2
// 4,4;4,5
// 7,2;7,3
// 9,8;9,9
// 9,0
// 2,0
// 7,6
// 4,8