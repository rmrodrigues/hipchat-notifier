package com.rmrodrigues.integration.hipchat.parameters;

public enum HipChatMessateType {
    Default("html"), html("html, text"), text("text");

    private String type;

    private HipChatMessateType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
