package com.gmail.aaudevar.model.hero;

import com.gmail.aaudevar.model.artefact.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import com.gmail.aaudevar.model.database.Database;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public abstract class Hero {

	@NotNull( message = "Hero name can not be null." )
	@Size( min = 2, max = 15, message = "Has to be between 2 and 15 characters. ")
	@NotBlank(message = "Hero name can't be blank.")
	protected String		name;

	@NotNull( message = "Can not have a blank class" )
	protected String		type;

	@Min(value = 1, message = "8 is level max")
	@Max(value = 8, message = "8 is level max")
	protected int			level;

	@Max(value = 30050, message = "8 is level max")
	protected int			xp;
	protected int			attack;
	protected int			defense;

	@Min(value = 0, message = "can't have negative hp")
	protected int			maxHP;
	
	@Min(value = 0, message = "can't have negative hp")
	protected int			currentHP;
	protected Weapon		weapon;
	protected Armor			armor;
	protected Helm			helm;

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	public void levelUp() {
		this.level++;
	}

	public void validateHero() throws Exception {
		Set<ConstraintViolation<Hero>> cvs = validator.validate(this);
		StringBuilder error = new StringBuilder();

		if (!cvs.isEmpty()) {
			for (ConstraintViolation<Hero> cv : cvs) {
				error.append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append("\n");
			}
			throw new Exception(error.toString());
		}
	}


	public void summary() {
		System.out.println(this.name + " is a " + this.type + " of level " + this.level
							+ " with an attack of " + this.attack + ", a defense of " + this.defense
							+ " and max Hit Points of " + this.maxHP + ". The hero current health is of : " + this.currentHP);
	}
	
	public void viewEquipment(){
		System.out.println(this.name + " is currently equipped with a " + this.weapon.getName() + " which does "
				+ this.weapon.getModifier() + " extra damage, also with a " + this.armor.getName() + " which increase his armor by "
				+ this.armor.getModifier() + " and with a " + this.helm.getName() + " which give him "
				+ this.helm.getModifier() + " extra health.");
	}
	public void equipNewWeapon(Artefact weapon) throws Exception {
		this.attack -= this.weapon.getModifier();
		this.weapon = (Weapon) weapon;
		this.attack += this.weapon.getModifier();
		try {
			Database.updateArtefact("weapon", this.getWeapon().getName(), "attack", this.getAttack(), this.getName());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void equipNewArmor(Artefact armor) throws Exception {
		this.defense -= this.armor.getModifier();
		this.armor = (Armor) armor;
		this.defense += this.armor.getModifier();
		try {
			Database.updateArtefact("armor", this.getArmor().getName(), "defense", this.getDefense(), this.getName());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void equipNewHelm(Artefact helm) throws Exception {
		if (this.currentHP - this.helm.getModifier() < 0)
			this.currentHP = 0;
		else
			this.currentHP -= this.helm.getModifier();
		this.maxHP -= this.helm.getModifier();
		this.helm = (Helm) helm;
		this.currentHP += this.helm.getModifier();
		this.maxHP += this.helm.getModifier();
		try {
			Database.updateArtefact("helm", this.getHelm().getName(), "maxHP", this.getMaxHP(), this.getName());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}