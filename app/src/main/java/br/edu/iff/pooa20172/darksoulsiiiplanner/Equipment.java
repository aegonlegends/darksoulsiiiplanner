package br.edu.iff.pooa20172.darksoulsiiiplanner;

public abstract class Equipment {
    protected double weight;
    protected int durability, index;
    protected SpecialEffect specialEffects;

    public double getWeight() {
        return weight;
    }

    public int getDurability() {
        return durability;
    }

    public SpecialEffect getSpecialEffects() {
        return specialEffects;
    }

    public int getIndex() {
        return index;
    }
}
