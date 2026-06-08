package implementation;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;

public class QuickFixApplication implements Application {

    private Message lastIncomingMessage;
    private Message lastOutgoingMessage;

    @Override
    public void onCreate(SessionID sessionId) {}

    @Override
    public void onLogon(SessionID sessionId) {}

    @Override
    public void onLogout(SessionID sessionId) {}

    @Override
    public void toAdmin(Message message, SessionID sessionId) {}

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {}

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {

        lastOutgoingMessage = message;
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

        lastIncomingMessage = message;
        sendConfirmation(message, sessionId);
    }

    public Message getLastIncomingMessage() {

        return lastIncomingMessage;
    }

    public Message getLastOutgoingMessage() {

        return lastOutgoingMessage;
    }
    
    private void sendConfirmation(Message message, SessionID sessionId) {

        try {

        	if(message.getString(35).equals("D")) {
            	message.setString(35, "8");
            	message.setString(39, "0");
            	message.setString(150, "0");
            }else if(message.getString(35).equals("G")) {
            	message.setString(35, "8");
            	message.setString(39, "5");
            	message.setString(150, "5");
            }else if(message.getString(35).equals("F")) {
            	message.setString(35, "8");
            	message.setString(39, "4");
            	message.setString(150, "4");
            }
            
            
            Session.sendToTarget(message, sessionId);

            System.out.println("Confirmation Sent : " + message);

        } catch (Exception e) {

            throw new RuntimeException("Unable To Send Confirmation", e);
        }
    }
}