package com.gmail.aaudevar.model.hero;

import com.gmail.aaudevar.model.artefact.*;

import javax.validation.constraints.NotNull;

public class HigherVampire extends Hero{
    public HigherVampire(String heroName) {
        this.name = heroName;
        this.type = "Higher Vampire";
        this.weapon = WeaponFactory.createWeapon(1);
        this.helm = HelmFactory.createHelm(1);
        this.armor = ArmorFactory.createArmor(1);
        this.level = 1;
        this.xp = 0;
        this.attack = 6 + this.weapon.getModifier();
        this.defense = 5 + this.armor.getModifier();
        this.currentHP = 100 + this.helm.getModifier();
        this.maxHP = this.currentHP;
    }

    public HigherVampire(String name, String type, String weapon, String armor, String helm, int level, int xp) {
        this.name = name;
        this.type = type;
        this.weapon = new Weapon(weapon, (WeaponFactory.getWeaponModifier(weapon) + level));
        this.helm = new Helm(helm, (HelmFactory.getHelmModifier(helm) + level * 10));
        this.armor = new Armor(armor, (ArmorFactory.getArmorModifier(armor) + level));
        this.xp = xp;
        this.level = level;
        this.attack = 6 + ((level - 1) * 2) + this.weapon.getModifier();
        this.defense = 5 + (level - 1) + this.armor.getModifier();
        this.currentHP = 100 + ((level - 1) * 30) + this.helm.getModifier();
        this.maxHP = this.currentHP;
    }

    @Override
    public void levelUp() {
        this.level++;
        this.attack += 2;
        this.defense += 1;
        this.maxHP +=30;
        this.currentHP = this.maxHP;
    }
}
