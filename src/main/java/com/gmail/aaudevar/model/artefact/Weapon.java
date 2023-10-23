package com.gmail.aaudevar.model.artefact;

public class Weapon extends Artefact {
    public Weapon(String name, int modifier) {
        this.name = name;
        this.type = "weapon";
        this.modifier = modifier;
    }
}
