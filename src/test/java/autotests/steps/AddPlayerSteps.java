package autotests.steps;

import battleship.Base;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class AddPlayerSteps {
    public static String playerName;

    @When("the user start a game")
    public void userStartAGame() {
        Scanner scanner = new Scanner("Vasiliy");
        playerName = Base.addPlayers(scanner);
        scanner.close();
    }

    @Then("the result of the start game added name")
    public void theResultOfStartGameAddedName() {
        assertEquals("Vasiliy", playerName);
    }
}
