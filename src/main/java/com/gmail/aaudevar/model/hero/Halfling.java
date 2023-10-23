package com.gmail.aaudevar.model.hero;

import com.gmail.aaudevar.model.artefact.*;

import javax.validation.constraints.NotNull;

public class Halfling extends Hero {
    public Halfling(String heroName) {
        this.name = heroName;
        this.type = "Halfling";
        this.weapon = WeaponFactory.createWeapon(1);
        this.helm = HelmFactory.createHelm(1);
        this.armor = ArmorFactory.createArmor(1);
        this.level = 1;
        this.xp = 0;
        this.attack = 5 + this.weapon.getModifier();
        this.defense = 10 + this.armor.getModifier();
        this.currentHP = 60 + this.helm.getModifier();
        this.maxHP = this.currentHP;
    }

    public Halfling(String name, String type, String weapon, String armor, String helm, int level, int xp) {
        this.name = name;
        this.type = type;
        this.weapon = new Weapon(weapon, (WeaponFactory.getWeaponModifier(weapon) + level));
        this.helm = new Helm(helm, (HelmFactory.getHelmModifier(helm) + level * 10));
        this.armor = new Armor(armor, (ArmorFactory.getArmorModifier(armor) + level));
        this.xp = xp;
        this.level = level;
        this.attack = 5 + (level - 1) + this.weapon.getModifier();
        this.defense = 10 + ((level - 1) * 3) + this.armor.getModifier();
        this.currentHP = 60 + ((level - 1) * 20) + this.helm.getModifier();
        this.maxHP = this.currentHP;
    }

    @Override
    public void levelUp() {
        this.level++;
        this.attack += 1;
        this.defense += 3;
        this.maxHP +=20;
        this.currentHP = this.maxHP;
    }
}
