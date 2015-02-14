package com.rmrodrigues.integration.hipchat.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rmrodrigues.integration.hipchat.HipChatMessage;
import com.rmrodrigues.integration.hipchat.HipChatNotifier;
import com.rmrodrigues.integration.hipchat.exceptions.UnableToSendHipChatMsgException;
import com.rmrodrigues.integration.hipchat.parameters.HipChatMessageColor;
import com.rmrodrigues.integration.hipchat.parameters.HipChatMessateType;

public class HipChatNotifierTest {

    HipChatNotifier hipchatNotifier;

    @Before
    public void setUp() throws Exception {
        hipchatNotifier = new HipChatNotifier();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMessageMinSizeValidation() {
        HipChatMessage.build(HipChatMessageColor.Default, "", true, HipChatMessateType.text);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMessageMaxSizeValidation() {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            sbf.append("qwertyuiop");
        }
        sbf.append("error");
        HipChatMessage.build(HipChatMessageColor.Default, sbf.toString(), true, HipChatMessateType.text);
    }

    @Test
    public void testMessageValidSize() {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            sbf.append("qwertyuiop");
        }
        assertTrue(HipChatMessage.build(HipChatMessageColor.Default, sbf.toString(), true, HipChatMessateType.text) != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIdOrRoomMinSizeValidation() throws IOException {
        try {
            hipchatNotifier.send("", "", null);
        } catch (UnableToSendHipChatMsgException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIdOrRoomMaxSizeValidation() throws IOException {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sbf.append("qwertyuiop");
        }
        sbf.append("qwertyuiop");
        try {
            hipchatNotifier.send(sbf.toString(), "", null);
        } catch (UnableToSendHipChatMsgException e) {
            e.printStackTrace();
        }

    }
}
