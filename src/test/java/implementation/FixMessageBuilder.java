package implementation;

//import utils.ConfigReader;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import quickfix.field.ClOrdID;
import quickfix.field.Symbol;
import quickfix.field.Side;
import quickfix.field.OrderQty;
import quickfix.field.OrigClOrdID;
import quickfix.field.Price;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReplaceRequest;
import quickfix.fix44.OrderCancelRequest;


public class FixMessageBuilder {

	/*
    public String buildNewOrderSingle(String clOrdId, String symbol, String quantity, String price, String side) {

        String template = ConfigReader.getProperty("new.order.tags");

        template = replaceCommonTags(template);
        template = template.replace("{ClOrdID}", clOrdId);
        template = template.replace("{SYMBOL}", symbol);
        template = template.replace("{QTY}", quantity);
        template = template.replace("{PRICE}", price);
        template = template.replace("{SIDE}", side);
        
        return FixMessageConverter.convertToFixString(template);
    }
	*/
    public NewOrderSingle buildNewOrderSingle(String clOrdId, String symbol, String quantity, String price, String side) {

        NewOrderSingle order = new NewOrderSingle();

        order.set(new ClOrdID(clOrdId));
        order.set(new Symbol(symbol));
//        order.set(new Side(side.equalsIgnoreCase("1") ? Side.BUY : Side.SELL));
        order.set(new Side(side.charAt(0)));
        order.set(new OrderQty(Double.parseDouble(quantity)));
        order.set(new Price(Double.parseDouble(price)));

        return order;
    }
    /*
    public String buildModifyOrder(String clOrdId, String origClOrdId, String symbol, String quantity, String price) {

        String template = ConfigReader.getProperty("modify.order.tags");

        template = replaceCommonTags(template);
        template = template.replace("{ClOrdID}", clOrdId);
        template = template.replace("{OrigClOrdID}", origClOrdId);
        template = template.replace("{SYMBOL}", symbol);
        template = template.replace("{QTY}", quantity);
        template = template.replace("{PRICE}", price);

        return FixMessageConverter.convertToFixString(template);
    }
	*/
    public OrderCancelReplaceRequest buildModifyOrder(String clOrdId, String origClOrdId, String symbol, String quantity, String price) {

        OrderCancelReplaceRequest order = new OrderCancelReplaceRequest();

        order.set(new ClOrdID(clOrdId));
        order.set(new OrigClOrdID(origClOrdId));
        order.set(new Symbol(symbol));
        order.set(new OrderQty(Double.parseDouble(quantity)));
        order.set(new Price(Double.parseDouble(price)));

        return order;
    }
    /*
    public String buildCancelOrder(String clOrdId, String origClOrdId, String symbol) {

        String template = ConfigReader.getProperty("cancel.order.tags");

        template = replaceCommonTags(template);
        template = template.replace("{ClOrdID}", clOrdId);
        template = template.replace("{OrigClOrdID}", origClOrdId);
        template = template.replace("{SYMBOL}", symbol);

        return FixMessageConverter.convertToFixString(template);
    }
	*/
    public OrderCancelRequest buildCancelOrder(String clOrdId, String origClOrdId, String symbol) {

    	OrderCancelRequest order = new OrderCancelRequest();

        order.set(new ClOrdID(clOrdId));
        order.set(new OrigClOrdID(origClOrdId));
        order.set(new Symbol(symbol));
        
        return order;

    }
/*
    private String replaceCommonTags(String template) {

        return template.replace(
                "{TIME}",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss")
                )
        );
    }
 */
    
}