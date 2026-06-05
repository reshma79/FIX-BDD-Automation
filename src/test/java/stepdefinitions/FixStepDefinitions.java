package stepdefinitions;

import implementation.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.*;

import java.util.Map;

public class FixStepDefinitions {

    EngineManager engineManager = new EngineManager();
//    ClientSessionManager clientSession = new ClientSessionManager();
//    ReceiverSessionManager receiverSession = new ReceiverSessionManager();
//    QuickFixSessionManager clientSession = new QuickFixSessionManager();
//    QuickFixSessionManager receiverSession = new QuickFixSessionManager();
    OrderManager orderManager = new OrderManager();
    MessageValidator validator = new MessageValidator();

    @Given("FIX application is started")
    public void startApplication() {

        engineManager.startEngine();
    }
/*
    @Given("client FIX session is connected with:")
    public void connectClientSession(DataTable table) {

        Map<String, String> data = table.asMap(String.class, String.class);

        clientSession.connectSession(
                data.get("host"),
                Integer.parseInt(data.get("port"))
        );
    }

    @Given("receiver FIX session is connected with:")
    public void connectReceiverSession(DataTable table) {

        Map<String, String> data = table.asMap(String.class, String.class);

        receiverSession.connectSession(
                data.get("host"),
                Integer.parseInt(data.get("port"))
        );
    }
*/
    @Given("Client FIX session is started using config {configFile}")
    public void connectClientSession(String configFile) {

    	ClientSessionManager.connect(configFile);

    }

    @Given("Receiver FIX session is started using config {string}")
    public void startReceiverSession(String configFile) {

        ReceiverSessionManager.connect(configFile);
    }

    @When("user places FIX order:")
    public void placeOrder(DataTable table) {

        Map<String, String> data = table.asMaps(String.class, String.class).get(0);
        
        orderManager.placeOrder(
                data.get("symbol"),
                data.get("quantity"),
                data.get("price"),
                data.get("side")
        );
    }

    @When("user modifies FIX order:")
    public void modifyOrder(DataTable table) {

        Map<String, String> data = table.asMap(String.class, String.class);

        orderManager.modifyOrder(
                data.get("quantity"),
                data.get("price")
        );
    }

    @When("user cancels the FIX order")
    public void cancelOrder() {

        orderManager.cancelOrder();
    }

    @Then("client execution report should contain:")
    public void validateClientExecutionReport(DataTable table) {

        validator.validateClientExecutionReport(table);
    }

    @Then("receiver FIX message should contain:")
    public void validateReceiverFixMessage(DataTable table) {

        validator.validateReceiverSideMessage(table);
    }

    @AfterAll
    public void disconnectSession() {
    	
    	ClientSessionManager.disconnect();
    	ReceiverSessionManager.disconnect();
    	
    	engineManager.stopEngine();

        System.out.println("Sessions Closed");
    }
}