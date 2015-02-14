package com.rmrodrigues.integration.hipchat.parameters;

public enum HipChatMessageColor {
    Default("yellow"), yellow("yellow"), green("green"), red("red"), purple("purple"), gray("gray"), random("random");

    private String color;

    private HipChatMessageColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
