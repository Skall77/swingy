package com.gmail.aaudevar.model.map;

import com.gmail.aaudevar.model.hero.Hero;
import lombok.Getter;

import java.util.Random;

public class Map {
    private int mapSize;
    private Hero hero;
    private char[][] map;
    @Getter
    private int[] coordsHero = new int[2];
    @Getter
    private int[] previousCoords = new int[2];
    private Random rng = new Random();

    public Map(Hero hero)
    {
        this.hero = hero;
        createMap(hero);
        placeVillains();
    }

    private void createMap(Hero hero) {
        mapSize = ((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
        this.coordsHero[0] = mapSize / 2;
        this.coordsHero[1] = mapSize / 2;
        this.map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                this.map[i][j] = '*';
            }
        }
        this.map[coordsHero[0]][coordsHero[1]] = 'H';
    }

    public int rollDice() {
        return rng.nextInt(1,20);
    }

    private void placeVillains(){
        for (int i = 0; i < mapSize - 1; i++) {
            for (int j = 0; j < mapSize - 1; j++) {
                if (rollDice() < 8 && map[i][j] != 'H')
                    map[i][j] = 'V';
            }
        }
    }

    public void printMaps() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < mapSize; i++ ) {
            for (int j = 0; j < mapSize; j++) {
                s.append(" ").append(this.map[i][j]);
            }
            s.append("\n");
        }
        System.out.println(s);
    }

    public void setCoordsHero(int x, int y) {
        this.coordsHero[0] = x;
        this.coordsHero[1] = y;
    }

    public void setPreviousCoords(int x, int y) {
        this.previousCoords[0] = x;
        this.previousCoords[1] = y;
    }

    public int getX() {
        return this.coordsHero[0];
    }

    public int getY() {
        return this.coordsHero[1];
    }

    public void goNorth() {
        this.previousCoords[0] = this.coordsHero[0];
        this.previousCoords[1] = this.coordsHero[1];
        this.coordsHero[0] -= 1;
    }

    public void goSouth() {
        this.previousCoords[0] = this.coordsHero[0];
        this.previousCoords[1] = this.coordsHero[1];
        this.coordsHero[0] += 1;
    }

    public void goEast() {
        this.previousCoords[0] = this.coordsHero[0];
        this.previousCoords[1] = this.coordsHero[1];
        this.coordsHero[1] += 1;
    }
    public void goWest() {
        this.previousCoords[0] = this.coordsHero[0];
        this.previousCoords[1] = this.coordsHero[1];
        this.coordsHero[1] -= 1;
    }

    public boolean NotinBound(int x, int y) {
        return x < 0 || x >= mapSize || y < 0 || y >= mapSize;
    }

    public boolean updateMap() {
        this.map[previousCoords[0]][previousCoords[1]] = '*';
        if (this.map[coordsHero[0]][coordsHero[1]] == 'V') {
            this.map[coordsHero[0]][coordsHero[1]] = 'H';
            return true;
        }
        this.map[coordsHero[0]][coordsHero[1]] = 'H';
        return false;
    }

    public void runAwayUpdateMap() {
        this.map[previousCoords[0]][previousCoords[1]] = 'H';
        this.map[coordsHero[0]][coordsHero[1]] = 'V';
        this.setCoordsHero(this.previousCoords[0], this.previousCoords[1]);
    }

    public String MapToString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < mapSize; i++ ) {
            for (int j = 0; j < mapSize; j++) {
                s.append(" ").append(this.map[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }



}
