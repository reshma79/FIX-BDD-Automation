package implementation;

import io.cucumber.datatable.DataTable;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import quickfix.Message;

public class MessageValidator {

	/*
    public void validateClientExecutionReport(DataTable table) {

        String response = ClientSessionManager.receiveMessage();

        if(response == null) {
        	System.out.println("Client FIX Validation Not done as Response is NULL");
        	return;
        }
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

        if(response == null) {
        	System.out.println("Receiver FIX Validation Not done as Response is NULL");
        	return;
        }

        Map<String, String> actualFixMap = FixMessageParser.parseFixMessage(response);

        Map<String, String> expectedFixMap = table.asMap(String.class, String.class);

        for(Map.Entry<String, String> entry : expectedFixMap.entrySet()) {

            String actualValue = actualFixMap.get(entry.getKey());

            assertEquals("Mismatch for FIX Tag: " + entry.getKey(), entry.getValue(), actualValue);
        }

        System.out.println("Receiver FIX Validation Passed");
    }
    */
    public static void validateClientExecutionReport(DataTable table) {

    	Map<String, String> expectedData = table.asMaps(String.class, String.class).get(0);
    	
        validate(ClientSessionManager.getLastIncomingMessage(), expectedData);
    }

    public static void validateReceiverSideMessage(DataTable table) {

    	Map<String, String> expectedData = table.asMaps(String.class, String.class).get(0);
    	
        validate(ReceiverSessionManager.getLastIncomingMessage(), expectedData);
    }
    
    public static void validate(Message actualMessage, Map<String, String> expectedTags) {

        try {

            for (Map.Entry<String, String> entry : expectedTags.entrySet()) {

                String tag = entry.getKey();
                String expectedValue = entry.getValue();

                String actualValue = actualMessage.getString(Integer.parseInt(tag));

                assertEquals(
                        "Validation Failed For Tag " + tag,
                        expectedValue,
                        actualValue
                );
            }

        } catch (Exception e) {

            throw new RuntimeException("FIX Message Validation Failed", e);
        }
    }
}