package br.edu.iff.pooa20172.darksoulsiiiplanner;

public abstract class Equipment {
    protected String name;
    protected float weight;
    protected int durability, index;
    protected SpecialEffect[] specialEffects;

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public int getDurability() {
        return durability;
    }

    public SpecialEffect[] getSpecialEffects() {
        return specialEffects;
    }
}
