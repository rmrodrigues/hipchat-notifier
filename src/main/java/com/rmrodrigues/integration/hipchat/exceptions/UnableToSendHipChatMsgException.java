package com.rmrodrigues.integration.hipchat.exceptions;

public class UnableToSendHipChatMsgException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5732025331466738647L;

    public UnableToSendHipChatMsgException() {
    }

    public UnableToSendHipChatMsgException(String message) {
        super(message);
    }

    public UnableToSendHipChatMsgException(Throwable cause) {
        super(cause);
    }

    public UnableToSendHipChatMsgException(String message, Throwable cause) {
        super(message, cause);
    }

}
