package com.gmail.aaudevar.model.artefact;

public class Armor extends Artefact {
    public Armor(String name, int modifier) {
        this.name = name;
        this.type = "armor";
        this.modifier = modifier;
    }
}
