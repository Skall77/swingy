package com.gmail.aaudevar.model.database;

import com.gmail.aaudevar.model.hero.Hero;
import com.gmail.aaudevar.model.hero.Witcher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static Connection getConnection() throws Exception {

        String url = "jdbc:sqlite:heroesDatabase.db";
        String sql = "CREATE TABLE IF NOT EXISTS heroes ("
                + " id integer PRIMARY KEY, "
                + " name TEXT NOT NULL UNIQUE "
                + " CHECK(typeof(\"name\") = \"text\" AND "
                + " length(\"name\") <= 15), "
                + " type TEXT NOT NULL "
                + " CHECK(typeof(\"type\") = \"text\" AND "
                + " length(\"type\") <= 20),"
                + " weapon TEXT NOT NULL "
                + " CHECK(typeof(\"weapon\") = \"text\" AND "
                + " length(\"weapon\") <= 40),"
                + " armor TEXT NOT NULL "
                + " CHECK(typeof(\"armor\") = \"text\" AND "
                + " length(\"armor\") <= 40),"
                + " helm TEXT NOT NULL "
                + " CHECK(typeof(\"helm\") = \"text\" AND "
                + " length(\"helm\") <= 40),"
                + " level integer NOT NULL, "
                + " xp integer NOT NULL, "
                + " attack integer NOT NULL, "
                + " defense integer NOT NULL, "
                + " maxHp integer NOT NULL);";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return conn;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void insertHero(String name, String type, String weapon, String armor, String helm,
                                  int level, int xp, int attack, int defense, int maxHp) throws Exception {
        String sql;
        sql = "INSERT INTO heroes"
                + " (name, type, weapon, armor, helm, level, xp, attack, defense, maxHp)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, type);
            preparedStmt.setString(3, weapon);
            preparedStmt.setString(4, armor);
            preparedStmt.setString(5, helm);
            preparedStmt.setInt(6, level);
            preparedStmt.setInt(7, xp);
            preparedStmt.setInt(8, attack);
            preparedStmt.setInt(9, defense);
            preparedStmt.setInt(10, maxHp);
            preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static List<String> selectHero(String name) throws Exception {
        List<String> hero = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM heroes WHERE name = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, name);
            ResultSet res = preparedStmt.executeQuery();
            if (res.next()) {
                hero.add(res.getString("name"));
                hero.add(res.getString("type"));
                hero.add(res.getString("weapon"));
                hero.add(res.getString("armor"));
                hero.add(res.getString("helm"));
                hero.add(res.getString("level"));
                hero.add(res.getString("xp"));
            }
            conn.close();
            return hero;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static List<String> selectHeroById(String id) throws Exception {
        List<String> hero = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM heroes WHERE id = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, id);
            ResultSet res = preparedStmt.executeQuery();
            if (res.next()) {
                hero.add(res.getString("name"));
                hero.add(res.getString("type"));
                hero.add(res.getString("weapon"));
                hero.add(res.getString("armor"));
                hero.add(res.getString("helm"));
                hero.add(res.getString("level"));
                hero.add(res.getString("xp"));
            }
            conn.close();
            return hero;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void databaseInit() throws Exception {
        try {
            if (selectHero("Geralt").isEmpty()) {
                Hero geralt = new Witcher("Geralt");
                insertHero(geralt.getName(),
                        geralt.getType(),
                        geralt.getWeapon().getName(),
                        geralt.getArmor().getName(),
                        geralt.getHelm().getName(),
                        geralt.getLevel(),
                        geralt.getXp(),
                        geralt.getAttack(),
                        geralt.getDefense(),
                        geralt.getMaxHP());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static List<List<String>> selectAllHeroes() throws Exception {
        List<List<String>> result = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM heroes";

        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                List<String> temp = new ArrayList<>();
                temp.add(res.getString(1));
                temp.add(res.getString(2));
                temp.add(res.getString(3));
                temp.add(res.getString(4));
                temp.add(res.getString(5));
                temp.add(res.getString(6));
                temp.add(res.getString(7));
                temp.add(res.getString(8));
                temp.add(res.getString(9));
                temp.add(res.getString(10));
                temp.add(res.getString(11));
                result.add(temp);
            }
            conn.close();
            return result;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void updateArtefact(String firstCol, String firstVal,
                                     String secondCol, int secondVal,
                                     String name) throws Exception {
        String sql = "UPDATE heroes SET " + firstCol + " = ? , " + secondCol + " = ? WHERE name = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, firstVal);
            preparedStmt.setInt(2, secondVal);
            preparedStmt.setString(3, name);
            preparedStmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void updateStat(String col, int val, String name) throws Exception {
        String sql = "UPDATE heroes SET " + col + " = ? WHERE name = ?";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, val);
            preparedStmt.setString(2, name);
            preparedStmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

