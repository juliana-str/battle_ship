import battleship.BattleBoard;
import battleship.DeckShipService;
import battleship.Ship;
import org.junit.jupiter.api.*;

public class BaseTests {

    @DisplayName("Тест создание кораблей.")
    @Test
    public void shipCreationTest() {
        System.out.println("shipCreationTest");
        Ship[][] ship = new Ship[10][10];
        DeckShipService.createFourDeckShip(ship, new int[][]{{0,0},{0,1},{0,2},{0,3}});
        DeckShipService.createThreeDeckShip(ship,  new int[][]{{2,6},{2,7},{2,8}});
        DeckShipService.createThreeDeckShip(ship,  new int[][]{{3,2},{4,2},{5,2}});
        DeckShipService.createTwoDeckShip(ship, new int[][]{{4,4},{4,5}});
        DeckShipService.createTwoDeckShip(ship, new int[][]{{9,8},{9,9}});
        DeckShipService.createTwoDeckShip(ship, new int[][]{{7,2},{7,3}});
        DeckShipService.createSingleDeckShip(ship, new int[][]{{9,0}});
        DeckShipService.createSingleDeckShip(ship, new int[][]{{2,0}});
        DeckShipService.createSingleDeckShip(ship, new int[][]{{7,6}});
        DeckShipService.createSingleDeckShip(ship, new int[][]{{4,8}});
        DeckShipService.createEmpty(ship);
        BattleBoard battleBoard2 = new BattleBoard(ship, "Vasya");
        battleBoard2.print();


    }

    @BeforeEach
    public void setUpTest() {
        CommentSteps.addComment(CommentSteps.COMMENT_MINUS);
        CommentSteps.addComment("СТАРТ ТЕСТА");
        CommentSteps.addComment(CommentSteps.COMMENT_MINUS);

    }

    @AfterEach
    public void finishTest() {
        CommentSteps.addComment(CommentSteps.COMMENT_MINUS);
        CommentSteps.addComment("ТЕСТ УСПЕШНО ЗАВЕРШЕН");
        CommentSteps.addComment(CommentSteps.COMMENT_MINUS);
    }
}