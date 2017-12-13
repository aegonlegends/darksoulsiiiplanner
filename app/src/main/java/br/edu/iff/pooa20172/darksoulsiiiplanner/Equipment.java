package br.edu.iff.pooa20172.darksoulsiiiplanner;

public abstract class Equipment {
    protected String name;
    protected float weight;
    protected int durability, specialEffects;

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public int getDurability() {
        return durability;
    }

    public int getSpecialEffects() {
        return specialEffects;
    }
}
