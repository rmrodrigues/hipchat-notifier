package com.rmrodrigues.integration.hipchat;

import com.rmrodrigues.integration.hipchat.parameters.HipChatMessageColor;
import com.rmrodrigues.integration.hipchat.parameters.HipChatMessateType;

/**
 * The Class HipchatMessage.
 */
/**
 * Class description.
 *
 * @author <a href="mailto:rmrodrigues@xpand-it.com">rmrodrigues</a>
 * @version $Revision: 666 $
 *
 */
public class HipChatMessage {

    /** The color. */
    private HipChatMessageColor color;

    /** The message. */
    private String message;

    /** The notify. */
    private boolean notify;

    /** The message_format. */
    private HipChatMessateType message_format;

    /**
     * Instantiates a new hipchat message.
     */
    private HipChatMessage() {

    }

    private HipChatMessage(HipChatMessageColor color, String message, boolean notify, HipChatMessateType message_format) {
        super();
        this.color = color;
        this.message = message;
        this.notify = notify;
        this.message_format = message_format;
    }

    public HipChatMessageColor getColor() {
        return color;
    }

    public String getMessage() {
        return message;
    }

    public boolean isNotify() {
        return notify;
    }

    public HipChatMessateType getMessage_format() {
        return message_format;
    }

    public static HipChatMessage build(HipChatMessageColor color, String message, boolean notify,
            HipChatMessateType message_format) throws IllegalArgumentException {
        if (message == null || message.length() < 1 || message.length() > 10000) {
            throw new IllegalArgumentException("Message value should be >=1 and <=10000");
        }
        return new HipChatMessage(color, message, notify, message_format);
    }
}
