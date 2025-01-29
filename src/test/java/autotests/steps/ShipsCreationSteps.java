package autotests.steps;

import autotests.test_context.ShipsObjects;
import battleship.BattleBoard;
import battleship.Ship;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;


import static org.junit.Assert.assertEquals;

public class ShipsCreationSteps {
    private BattleBoard battleBoard;
    private Ship[][] ships;

    @Given("a battle board")
    public void aBattleBoard() {
    }

    @When("the user full ship coordinates")
    public void fullShipCoordinates() {
        ships = ShipsObjects.shipCreate();
    }
    @When("battle board creation")
    public void battleBoardCreation() {
        battleBoard = new BattleBoard(ships, "Vasya");
        battleBoard.print();
    }

    @Then("the result of ships creation a ships on battle board")
    public void theResultOfCalculationMustBeSumOfValues() {
        assertEquals(battleBoard.getBattleBoard().length, 10);
    }

}
