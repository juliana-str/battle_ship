package autotests.steps;

import battleship.Base;
import battleship.Ship;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SingleDeskShipCreationSteps {
    public Ship[][] ships = new Ship[10][10];;

    @When("the user input coordinates and create single desk ship")
    public void userInputCoordinatesAndCreateSingleDeskShip() {
        Scanner scanner = new Scanner("8,9");
        Base.createShips(ships, scanner,1, 4);
        scanner.close();
    }

    @Then("the result created single desk ship")
    public void theResultCreatedSingleDeskShip() {
        assertTrue(Objects.equals(ships[8][9],Ship.SINGLE_DECK_SHIP));
        }

}
