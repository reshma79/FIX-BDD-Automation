package implementation;

import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
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
    }

    public Message getLastIncomingMessage() {

        return lastIncomingMessage;
    }

    public Message getLastOutgoingMessage() {

        return lastOutgoingMessage;
    }
}