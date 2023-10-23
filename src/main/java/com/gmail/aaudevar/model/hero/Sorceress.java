package com.gmail.aaudevar.model.hero;

import com.gmail.aaudevar.model.artefact.*;


public class Sorceress extends Hero{
    public Sorceress(String heroName) {
        this.name = heroName;
        this.type = "Sorceress";
        this.weapon = WeaponFactory.createWeapon(1);
        this.helm = HelmFactory.createHelm(1);
        this.armor = ArmorFactory.createArmor(1);
        this.level = 1;
        this.xp = 0;
        this.attack = 10 + this.weapon.getModifier();
        this.defense = 6 + this.armor.getModifier();
        this.currentHP = 50 + this.helm.getModifier();
        this.maxHP = this.currentHP;
    }

    public Sorceress(String name, String type, String weapon, String armor, String helm, int level, int xp) {
        this.name = name;
        this.type = type;
        this.weapon = new Weapon(weapon, (WeaponFactory.getWeaponModifier(weapon) + level));
        this.helm = new Helm(helm, (HelmFactory.getHelmModifier(helm) + level * 10));
        this.armor = new Armor(armor, (ArmorFactory.getArmorModifier(armor) + level));
        this.xp = xp;
        this.level = level;
        this.attack = 10 + ((level - 1) * 3) + this.weapon.getModifier();
        this.defense = 6 + ((level - 1) * 2) + this.armor.getModifier();
        this.currentHP = 50 + ((level - 1) * 10) + this.helm.getModifier();
        this.maxHP = this.currentHP;
    }

    @Override
    public void levelUp() {
        this.level++;
        this.attack += 3;
        this.defense += 2;
        this.maxHP +=10;
        this.currentHP = this.maxHP;
    }
}
