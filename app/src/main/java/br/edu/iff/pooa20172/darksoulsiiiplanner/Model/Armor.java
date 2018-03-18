package br.edu.iff.pooa20172.darksoulsiiiplanner.Model;

public class Armor extends Equipment {
    private int type;
    private double physicalAbsorption, strikeAbsorption, slashAbsorption, thrustAbsorption, magicAbsorption,fireAbsorption,lightningAbsorption,darkAbsorption, poise;
    int bleedResistance, poisonResistance, frostResistance, curseResistance;
    static final public int TYPE_HELM = 0, TYPE_CHESTPIECE = 1, TYPE_GAUNTLETS = 2, TYPE_LEGGINGS = 3;

    public Armor(int index, double weight, int durability, SpecialEffect specialEffects, int type, double physicalAbsorption, double strikeAbsorption, double slashAbsorption, double thrustAbsorption, double magicAbsorption, double fireAbsorption, double lightningAbsorption, double darkAbsorption, int bleedResistance, int poisonResistance, int frostResistance, int curseResistance, double poise) {
        this.index = index;
        this.weight = weight;
        this.durability = durability;
        this.specialEffects = specialEffects;
        this.type = type;
        this.physicalAbsorption = physicalAbsorption;
        this.strikeAbsorption = strikeAbsorption;
        this.slashAbsorption = slashAbsorption;
        this.thrustAbsorption = thrustAbsorption;
        this.magicAbsorption = magicAbsorption;
        this.fireAbsorption = fireAbsorption;
        this.lightningAbsorption = lightningAbsorption;
        this.darkAbsorption = darkAbsorption;
        this.bleedResistance = bleedResistance;
        this.poisonResistance = poisonResistance;
        this.frostResistance = frostResistance;
        this.curseResistance = curseResistance;
        this.poise = poise;
    }

    public int getType() {
        return type;
    }

    public double getPhysicalAbsorption() {
        return physicalAbsorption;
    }

    public double getStrikeAbsorption() {
        return strikeAbsorption;
    }

    public double getSlashAbsorption() {
        return slashAbsorption;
    }

    public double getThrustAbsorption() {
        return thrustAbsorption;
    }

    public double getMagicAbsorption() {
        return magicAbsorption;
    }

    public double getFireAbsorption() {
        return fireAbsorption;
    }

    public double getLightningAbsorption() {
        return lightningAbsorption;
    }

    public double getDarkAbsorption() {
        return darkAbsorption;
    }

    public double getBleedResistance() {
        return bleedResistance;
    }

    public double getPoisonResistance() {
        return poisonResistance;
    }

    public double getFrostResistance() {
        return frostResistance;
    }

    public double getCurseResistance() {
        return curseResistance;
    }

    public double getPoise() {
        return poise;
    }
}
