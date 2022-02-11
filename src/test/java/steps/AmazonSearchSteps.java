package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonSearchSteps {
    @Given("the user navigates to {string}")
    public void theUserNavigatesTo(String arg0) {
        System.out.println("step 1");
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String arg0) {
        System.out.println("step 1");
    }

    @And("navigates to the second page")
    public void navigatesToTheSecondPage() {
        System.out.println("step 1");
    }

    @And("selects the third item")
    public void selectsTheThirdItem() {
        System.out.println("step 1");
    }

    @Then("the user is able to add the item to the cart")
    public void theUserIsAbleToAddTheItemToTheCart() {
        System.out.println("step 1");
    }
}
