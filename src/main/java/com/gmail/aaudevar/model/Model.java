package com.gmail.aaudevar.model;

import com.gmail.aaudevar.model.artefact.*;
import com.gmail.aaudevar.model.database.Database;
import com.gmail.aaudevar.model.encounter.Encounter;
import com.gmail.aaudevar.model.encounter.EncounterFactory;
import com.gmail.aaudevar.model.hero.Hero;
import com.gmail.aaudevar.model.hero.HeroFactory;
import com.gmail.aaudevar.model.hero.Witcher;
import com.gmail.aaudevar.model.map.Map;
import com.gmail.aaudevar.model.villain.Villain;
import com.gmail.aaudevar.model.villain.VillainFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    @Getter
    private Hero hero;
    @Getter
    @Setter
    private Villain villain;
    @Getter
    @Setter
    private Map map;
    private final Random rng = new Random();
    @Getter
    private List<List<String>> storedHeroes;
    @Getter
    @Setter
    private Artefact artefact;
    @Getter
    @Setter
    private Encounter encounter;

    public Model(){}

    public void modelInit() throws Exception {
        try {
            Database.databaseInit();
            this.storedHeroes = Database.selectAllHeroes();
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void storeHero() throws Exception {
        try {
            Database.insertHero(this.hero.getName(), this.hero.getType(),
                    this.hero.getWeapon().getName(), this.hero.getArmor().getName(),
                    this.hero.getHelm().getName(), this.hero.getLevel(),
                    this.hero.getXp(), this.hero.getAttack(), this.hero.getDefense(),
                    this.hero.getMaxHP());
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateStatistics() throws Exception {
        try {
            int attack, defense, maxHP;
            switch (hero.getType()) {
                case ("Halfling") -> {
                    attack = 5 + (hero.getLevel() - 1) + hero.getWeapon().getModifier() + (hero.getLevel() - 1);
                    defense = 10 + ((hero.getLevel() - 1) * 3) + hero.getArmor().getModifier() + (hero.getLevel() - 1);
                    maxHP = 60 + ((hero.getLevel() - 1) * 20) + hero.getHelm().getModifier() + ((hero.getLevel() - 1) * 10);
                }
                case ("Higher Vampire") -> {
                    attack = 6 + ((hero.getLevel() - 1) * 2) + hero.getWeapon().getModifier() + (hero.getLevel() - 1);
                    defense = 5 + ((hero.getLevel() - 1)) + hero.getArmor().getModifier() + (hero.getLevel() - 1);
                    maxHP = 100 + ((hero.getLevel() - 1) * 30) + hero.getHelm().getModifier() + ((hero.getLevel() - 1) * 10);
                }
                case ("Sorceress") -> {
                    attack = 10 + ((hero.getLevel() - 1) * 3) + hero.getWeapon().getModifier() + (hero.getLevel() - 1);
                    defense = 6 + ((hero.getLevel() - 1) * 2) + hero.getArmor().getModifier() + (hero.getLevel() - 1);
                    maxHP = 50 + ((hero.getLevel() - 1) * 10) + hero.getHelm().getModifier() + ((hero.getLevel() - 1) * 10);
                }
                case ("Witcher") -> {
                    attack = 7 + ((hero.getLevel() - 1) * 2) + hero.getWeapon().getModifier() + (hero.getLevel() - 1);
                    defense = 7 + ((hero.getLevel() - 1) * 2) + hero.getArmor().getModifier() + (hero.getLevel() - 1);
                    maxHP = 70 + ((hero.getLevel() - 1) * 20) + hero.getHelm().getModifier() + ((hero.getLevel() - 1) * 10);
                }
                default -> {
                    attack = 0;
                    defense = 0;
                    maxHP = 0;
                }
            }
            Database.updateStat("attack", attack, hero.getName());
            Database.updateStat("defense", defense, hero.getName());
            Database.updateStat("maxHP", maxHP, hero.getName());
            Database.updateStat("xp", hero.getXp(), hero.getName());
            Database.updateStat("level", hero.getLevel(), hero.getName());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void selectHero(String index) throws Exception {
        try {
            List<String> selectedHero = Database.selectHeroById(index);
            if (selectedHero.size() == 7) {
                this.hero = HeroFactory.createHeroFromDatabase(selectedHero.get(0),
                        selectedHero.get(1), selectedHero.get(2), selectedHero.get(3),
                        selectedHero.get(4),Integer.parseInt(selectedHero.get(5)),
                                Integer.parseInt(selectedHero.get(6)));
                this.hero.validateHero();
            }
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void selectVillain() {
        this.villain = VillainFactory.createVillain(this.hero.getLevel());
    }

    public void selectEncounter() { this.encounter = EncounterFactory.createEncounter(this.hero);}

    public int rollDice() {
        return rng.nextInt(1,20);
    }

    public void generateMap() {this.map = new Map(hero); }

    public List<String> getHeroViaName(String name) throws Exception {
        List<String> ret = new ArrayList<>();
        try {
            ret = Database.selectHero(name);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return ret;
    }

    public void createHero(String name, String type) throws Exception {
        this.hero = HeroFactory.createNewHero(name, type);
        try {
            this.hero.validateHero();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void loot() {
            int lootType = (int) ((Math.random() * 100) % 3);
            switch (lootType) {
                case 0 -> {
                    this.artefact = ArmorFactory.createArmor(hero.getLevel());
                }
                case 1 -> {
                    this.artefact = HelmFactory.createHelm(hero.getLevel());
                }
                case 2 -> {
                    this.artefact = WeaponFactory.createWeapon(hero.getLevel());
                }
            }
    }

    public void fightSimulation() {
        while (hero.getCurrentHP() > 0 && villain.getCurrentHP() > 0) {
            System.out.println(fightVillainTurn());
            if (hero.getCurrentHP() <= 0 || villain.getCurrentHP() <= 0)
                break;
            System.out.println(fightHeroTurn());
        }
    }

    public String fightVillainTurn() {
        StringBuilder s = new StringBuilder();
        int atk = rollDice();
        int def = rollDice();

        s.append("-- THE VILLAIN TURN -- \n");
        if (atk == 1) {
            villain.setCurrentHP(villain.getCurrentHP() - 5);
            s.append("CRITICAL MISS :\n").append(villain.getName())
                    .append(" hurt himself in it's confusion;\nand take 5 damage.");
        }
        else if (atk == 20) {
            hero.setCurrentHP(hero.getCurrentHP() - ((atk + villain.getAttack()) * 2));
            s.append("CRITICAL HIT :\n").append(villain.getName()).append(" did twice more damage!")
                    .append(hero.getName()).append(" took ").append((atk + villain.getAttack()) * 2)
                    .append(" damage\nAnd have ").append(hero.getCurrentHP()).append(" health point.");
        }
        else if (def == 1) {
            hero.setCurrentHP(hero.getCurrentHP() - ((atk + villain.getAttack()) + 5));
            s.append("CRITICAL MISS :\n").append(hero.getName()).append(" hurt himself in it's confusion;\n")
                    .append(" and take 5 extra damage after ").append(villain.getName()).append(" already did ")
                    .append(atk + villain.getAttack()).append(" damage.\nThe hero now have ")
                    .append(hero.getCurrentHP()).append(" health point.");
        }
        else if (def == 20) {
            villain.setCurrentHP(villain.getCurrentHP() - 5);
            s.append("CRITICAL DEF:\n").append(hero.getName())
                    .append(" dodged the attack with so much speed that he had ").append("time to riposte.\n")
                    .append(villain.getName()).append(" take 5 damage. ");
        }
        else if ( atk + villain.getAttack() > def+ hero.getDefense()) {
            hero.setCurrentHP(hero.getCurrentHP() - (atk + villain.getAttack()));
            s.append(villain.getName()).append(" did a D20 roll of [").append(atk).append(" + ")
                    .append(villain.getAttack()).append("]\nAgainst ").append(hero.getName())
                    .append(" D20 roll of [").append(def).append(" + ")
                    .append(hero.getDefense()).append("]\nTherefore the hero took ")
                    .append(atk + villain.getAttack()).append(" damage and have ")
                    .append(hero.getCurrentHP()).append(" health point");
        }
        else {
            s.append(hero.getName()).append(" dodged the attack");
        }
        return(s.toString());
    }

    public String fightHeroTurn() {
        StringBuilder s = new StringBuilder();
        int atk = rollDice();
        int def = rollDice();

        s.append("\n-- THE HERO TURN -- \n");
        if (atk == 1) {
            hero.setCurrentHP(hero.getCurrentHP() - 5);
            s.append("CRITICAL MISS:\n").append(hero.getName())
                    .append("hurt himself in it's confusion; The hero take 5 damage.");
        }
        else if (atk == 20) {
            villain.setCurrentHP(villain.getCurrentHP() - ((atk + hero.getAttack()) * 2));
            s.append("CRITICAL HIT:\n").append(hero.getName()).append(" did twice more damage! ")
                    .append(villain.getName()).append(" took ").append((atk + hero.getAttack()) * 2)
                    .append("damage\nAnd have ").append(villain.getCurrentHP()).append(" health point.");
        }
        else if (def == 1) {
            villain.setCurrentHP(villain.getCurrentHP() - ((atk + hero.getAttack()) + 5));
            s.append("CRITICAL MISS:\n").append(villain.getName()).append("hurt himself in it's confusion;\n")
                    .append("it take 5 extra damage after ").append(hero.getName()).append(" already did ")
                    .append(atk + hero.getAttack()).append(" damage.\nIt now have ")
                    .append(villain.getCurrentHP()).append(" health point.");
        }
        else if (def == 20) {
            hero.setCurrentHP(hero.getCurrentHP() - 5);
            s.append("CRITICAL DEF:\n").append(villain.getName())
                    .append(" dodged the attack with so much speed that it had")
                    .append("time to riposte.\n").append(hero.getName()).append(" take 5 damage. ");
        }
        else if ( atk + hero.getAttack() > def + villain.getDefense() ) {
            villain.setCurrentHP(villain.getCurrentHP() - (atk + hero.getAttack()));
            s.append(hero.getName()).append(" did a D20 roll of [").append(atk).append(" + ")
                    .append(hero.getAttack()).append("]\nAgainst the ").append(villain.getName())
                    .append(" D20 roll of [").append(def).append(" + ").append(villain.getDefense())
                    .append("]\nTherefore the villain took ").append(atk + hero.getAttack())
                    .append(" damage and have ").append(villain.getCurrentHP()).append(" health point.");
        }
        else {
            s.append(hero.getName()).append(" missed the attack");
        }
        return(s.toString());
    }

    public boolean isLevelUp() {
        return hero.getXp() >= hero.getLevel() * 1000 + (hero.getLevel() - 1) * (hero.getLevel() - 1) * 450;
    }

    public void equipNewArtefact(Artefact artefact) throws Exception{
        try {
            switch (artefact.getType()) {
                case "weapon" -> hero.equipNewWeapon(artefact);
                case "armor" -> hero.equipNewArmor(artefact);
                case "helm" -> hero.equipNewHelm(artefact);
            }
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean isArtefactWon() {
        return rng.nextInt(100) <= 49;
    }

    public String getCurrentMap() { return map.MapToString(); }
}
