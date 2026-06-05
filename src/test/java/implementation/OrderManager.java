package implementation;

import java.util.UUID;

import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReplaceRequest;
import quickfix.fix44.OrderCancelRequest;

public class OrderManager {

    FixMessageBuilder builder = new FixMessageBuilder();
//    QuickFixSessionManager sessionManager = new QuickFixSessionManager();
    
    String currentOrderId;
    String symbol;

    public void placeOrder(String symbol, String quantity, String price, String side) {

        this.symbol = symbol;

        currentOrderId = UUID.randomUUID().toString();

        NewOrderSingle fixMessage = builder.buildNewOrderSingle(currentOrderId, symbol, quantity, price, side);
        ClientSessionManager.sendMessage(fixMessage);
    }

    public void modifyOrder(String quantity, String price) {

        String modifyOrderId = UUID.randomUUID().toString();

        OrderCancelReplaceRequest fixMessage = builder.buildModifyOrder(modifyOrderId, currentOrderId, symbol, quantity, price);

        currentOrderId = modifyOrderId;

        ClientSessionManager.sendMessage(fixMessage);
    }

    public void cancelOrder() {

        String cancelOrderId = UUID.randomUUID().toString();

        OrderCancelRequest fixMessage = builder.buildCancelOrder(cancelOrderId, currentOrderId, symbol);

        currentOrderId = cancelOrderId;

        ClientSessionManager.sendMessage(fixMessage);
    }
    
}