package battleship;


import java.util.Scanner;

public class Base {

    public static Ship[][] createShips(Ship[][] somePlayerShips, Scanner scanner, int shipDesk, int count) {
        DeckShipService.fullShipTypesMap();
        DeckShipService.inputCoordinates(somePlayerShips, count, shipDesk, scanner);
        return somePlayerShips;
    }

    public static String addPlayers(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начинаем игру Морской бой. Введите имя первого игрока.");
        String firstPlayer = addPlayers(scanner);
        System.out.println("Введите имя второго игрока.");
        String secondPlayer = addPlayers(scanner);
        System.out.println("Поочередно игроки введят координаты своих кораблей.");
        System.out.println("Начинает " + firstPlayer + ", " + secondPlayer + " не подглядывает!");
        Ship[][] firstPlayerShips = new Ship[10][10];
        BattleBoard battleBoard1 = new BattleBoard(createShips(firstPlayerShips, scanner, 4, 1), firstPlayer);
        battleBoard1.print();
        System.out.println("Теперь " + secondPlayer + ", " + firstPlayer + " не подглядывает!");
        Ship[][] secondPlayerShips = AutoCreation.shipCreationMethod();
        BattleBoard battleBoard2 = new BattleBoard(secondPlayerShips, secondPlayer);
        battleBoard2.print();
        System.out.println(Game.startGame(battleBoard1, battleBoard2, scanner));
        scanner.close();
    }
}
