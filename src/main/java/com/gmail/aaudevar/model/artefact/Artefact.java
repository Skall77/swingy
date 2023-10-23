package com.gmail.aaudevar.model.artefact;

public class Artefact {
    protected String name;
    protected String type;
    protected int modifier;

    Artefact() {};

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModifier() {
        return this.modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
}


