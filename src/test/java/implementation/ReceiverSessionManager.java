package implementation;

import quickfix.Message;

public class ReceiverSessionManager {

    private static final QuickFixApplication application = new QuickFixApplication();
    private static final QuickFixSessionManager sessionManager = new QuickFixSessionManager();

    public static void connect(String configFile) {

        sessionManager.startSession(configFile, application);
    }

    public static void disconnect() {

        sessionManager.stopSession();
    }

    public static Message getLastIncomingMessage() {

        return application.getLastIncomingMessage();
    }

    public static Message getLastOutgoingMessage() {

        return application.getLastOutgoingMessage();
    }
}