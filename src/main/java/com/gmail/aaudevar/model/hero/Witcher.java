package com.gmail.aaudevar.model.hero;


import com.gmail.aaudevar.model.artefact.*;

import javax.validation.constraints.NotNull;

public class Witcher extends Hero {

	public Witcher(String heroName) {
		this.name = heroName;
		this.type = "Witcher";
		this.weapon = WeaponFactory.createWeapon(1);
		this.helm = HelmFactory.createHelm(1);
		this.armor = ArmorFactory.createArmor(1);
		this.level = 1;
		this.xp = 0;
		this.attack = 7 + this.weapon.getModifier();
		this.defense = 7 + this.armor.getModifier();
		this.currentHP = 70 + this.helm.getModifier();
		this.maxHP = this.currentHP;
	}

	public Witcher(String name, String type, String weapon, String armor, String helm, int level, int xp) {
		this.name = name;
		this.type = type;
		this.weapon = new Weapon(weapon, (WeaponFactory.getWeaponModifier(weapon) + level));
		this.helm = new Helm(helm, (HelmFactory.getHelmModifier(helm) + level * 10));
		this.armor = new Armor(armor, (ArmorFactory.getArmorModifier(armor) + level));
		this.xp = xp;
		this.level = level;
		this.attack = 7 + ((level - 1) * 2) + this.weapon.getModifier();
		this.defense = 7 + ((level - 1) * 2) + this.armor.getModifier();
		this.currentHP = 70 + ((level - 1) * 20) + this.helm.getModifier();
		this.maxHP = this.currentHP;
	}

	@Override
	public void levelUp() {
		this.level++;
		this.attack += 2;
		this.defense += 2;
		this.maxHP +=20;
		this.currentHP = this.maxHP;
	}
}

