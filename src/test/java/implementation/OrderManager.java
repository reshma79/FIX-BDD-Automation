package implementation;

import java.util.UUID;

public class OrderManager {

    FixMessageBuilder builder = new FixMessageBuilder();

    String currentOrderId;
    String symbol;

    public void placeOrder(String symbol, String quantity, String price, String side) {

        this.symbol = symbol;

        currentOrderId = UUID.randomUUID().toString();

        String sideValue = getSideValue(side);

        String fixMessage = builder.buildNewOrderSingle(currentOrderId, symbol, quantity, price, sideValue);

        ClientSessionManager.sendMessage(fixMessage);
    }

    public void modifyOrder(String quantity, String price) {

        String modifyOrderId = UUID.randomUUID().toString();

        String fixMessage = builder.buildModifyOrder(modifyOrderId, currentOrderId, symbol, quantity, price);

        currentOrderId = modifyOrderId;

        ClientSessionManager.sendMessage(fixMessage);
    }

    public void cancelOrder() {

        String cancelOrderId = UUID.randomUUID().toString();

        String fixMessage = builder.buildCancelOrder(cancelOrderId, currentOrderId, symbol);

        currentOrderId = cancelOrderId;

        ClientSessionManager.sendMessage(fixMessage);
    }

    private String getSideValue(String side) {

        return side.equalsIgnoreCase("BUY") ? "1" : "2";
    }
}