package br.edu.iff.pooa20172.darksoulsiiiplanner.Model;

import java.io.Serializable;

public class Classe implements Serializable{
    private int level, vigor, attunement, endurance, vitality, strength, dexterity, intelligence, faith, luck, index;

    public static Classe KNIGHT = new Classe(9, 12, 10, 11, 15, 13, 12, 9, 9, 7, 0);
    public static Classe MERCENARY = new Classe(8, 11, 12, 11, 10, 10, 16, 10, 8 , 9,1);
    public static Classe WARRIOR = new Classe(7, 14, 6, 12, 11, 16, 9, 8, 9, 11,2);
    public static Classe HERALD = new Classe(9, 12, 10, 9, 12, 12, 11, 8, 13, 11,3);
    public static Classe THIEF = new Classe(5, 10, 11, 10, 9, 9, 13, 10, 8, 14,4);
    public static Classe ASSASSIN = new Classe(10, 10, 14, 11, 10, 10, 14, 11, 9, 10,5);
    public static Classe SORCERER = new Classe(6, 9, 16, 9, 7, 7, 12, 16, 7, 12,6);
    public static Classe PYROMANCER = new Classe(8, 11, 12, 10, 8, 12, 9, 14, 14, 7,7);
    public static Classe CLERIC = new Classe(7, 10, 14, 9, 7, 12, 8, 7, 16, 13,8);
    public static Classe DEPRIVED = new Classe(1, 10, 10, 10, 10, 10, 10, 10, 10, 10,9);

    public Classe(int level, int vigor, int attunement, int endurance, int vitality, int strength, int dexterity, int intelligence, int faith, int luck, int index) {
        this.level = level;
        this.vigor = vigor;
        this.attunement = attunement;
        this.endurance = endurance;
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.faith = faith;
        this.luck = luck;
        this.index = index;
    }

    static public Classe fromIndex(int index){
        switch (index){
            case 0: return KNIGHT;
            case 1: return MERCENARY;
            case 2: return WARRIOR;
            case 3: return HERALD;
            case 4: return THIEF;
            case 5: return ASSASSIN;
            case 6: return SORCERER;
            case 7: return PYROMANCER;
            case 8: return CLERIC;
            case 9: return DEPRIVED;
            default: return null;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getVigor() {
        return vigor;
    }

    public int getAttunement() {
        return attunement;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getVitality() {
        return vitality;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getFaith() {
        return faith;
    }

    public int getLuck() {
        return luck;
    }

    public int getIndex() {
        return index;
    }
}
