package implementation;

import io.cucumber.datatable.DataTable;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MessageValidator {

    public void validateClientExecutionReport(DataTable table) {

        String response = ClientSessionManager.receiveMessage();

        Map<String, String> actualFixMap = FixMessageParser.parseFixMessage(response);

        Map<String, String> expectedFixMap = table.asMap(String.class, String.class);

        for(Map.Entry<String, String> entry : expectedFixMap.entrySet()) {

            String actualValue = actualFixMap.get(entry.getKey());

            assertEquals("Mismatch for FIX Tag: " + entry.getKey(), entry.getValue(), actualValue);
        }

        System.out.println("Client FIX Validation Passed");
    }

    public void validateReceiverSideMessage(DataTable table) {

        String response = ReceiverSessionManager.captureOutgoingFixMessage();

        Map<String, String> actualFixMap = FixMessageParser.parseFixMessage(response);

        Map<String, String> expectedFixMap = table.asMap(String.class, String.class);

        for(Map.Entry<String, String> entry : expectedFixMap.entrySet()) {

            String actualValue = actualFixMap.get(entry.getKey());

            assertEquals("Mismatch for FIX Tag: " + entry.getKey(), entry.getValue(), actualValue);
        }

        System.out.println("Receiver FIX Validation Passed");
    }
}