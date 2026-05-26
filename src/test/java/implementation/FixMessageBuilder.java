package implementation;

import utils.ConfigReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FixMessageBuilder {

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

    public String buildCancelOrder(String clOrdId, String origClOrdId, String symbol) {

        String template = ConfigReader.getProperty("cancel.order.tags");

        template = replaceCommonTags(template);
        template = template.replace("{ClOrdID}", clOrdId);
        template = template.replace("{OrigClOrdID}", origClOrdId);
        template = template.replace("{SYMBOL}", symbol);

        return FixMessageConverter.convertToFixString(template);
    }

    private String replaceCommonTags(String template) {

        return template.replace(
                "{TIME}",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss")
                )
        );
    }
}