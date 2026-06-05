package implementation;

import quickfix.Application;
import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.LogFactory;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.ScreenLogFactory;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;

public class QuickFixSessionManager {

    private SocketInitiator initiator;
    private SessionID sessionId;

    public void startSession(String configFile, Application application) {

        try {

            SessionSettings settings = new SessionSettings(configFile);

            MessageStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new ScreenLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory();

            initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);

            initiator.start();

            sessionId = initiator.getSessions().get(0);

        } catch (Exception e) {

            throw new RuntimeException("Unable To Start FIX Session", e);
        }
    }

    public void stopSession() {

        try {

            if (initiator != null) {

                initiator.stop();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {

        try {

            Session.sendToTarget(message, sessionId);

        } catch (Exception e) {

            throw new RuntimeException("Unable To Send FIX Message", e);
        }
    }

    public SessionID getSessionId() {

        return sessionId;
    }
}