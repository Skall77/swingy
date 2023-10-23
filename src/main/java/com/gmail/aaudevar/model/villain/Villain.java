package com.gmail.aaudevar.model.villain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Villain {
    private String name;
    private int     level;
    private int     currentHP;
    private int     attack;
    private int     defense;
    private int     xp;

    Villain(String name, int lvl, int atk, int def, int hp, int xp) {
        this.name = name;
        this.level = lvl;
        this.currentHP = hp + ((lvl * 2) * 10);
        this.attack = atk + (lvl * 2);
        this.defense = def + (lvl * 2);
        this.xp = xp * ((lvl / 2) + 1);
    }
}
