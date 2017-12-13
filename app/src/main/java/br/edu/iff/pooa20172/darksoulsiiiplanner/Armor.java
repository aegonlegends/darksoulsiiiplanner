package br.edu.iff.pooa20172.darksoulsiiiplanner;

public class Armor extends Equipment {
    int type;
    static public int TYPE_HELM = 0, TYPE_CHESTPIECE = 1, TYPE_GAUNTLETS = 2, TYPE_BOOTS = 3;
    float physicalAbsorption, strikeAbsorption, slashAbsorption, thrustAbsorption, magicAbsorption,fireAbsorption,lightningAbsorption,darkAbsorption, bleedResistance, poisonResistance, frostResistance, curseResistance;

    public Armor(String name, float weight, int durability, int specialEffects, int type, float physicalAbsorption, float strikeAbsorption, float slashAbsorption, float thrustAbsorption, float magicAbsorption, float fireAbsorption, float lightningAbsorption, float darkAbsorption, float bleedResistance, float poisonResistance, float frostResistance, float curseResistance) {
        this.name = name;
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
    }

    public int getType() {
        return type;
    }

    public float getPhysicalAbsorption() {
        return physicalAbsorption;
    }

    public float getStrikeAbsorption() {
        return strikeAbsorption;
    }

    public float getSlashAbsorption() {
        return slashAbsorption;
    }

    public float getThrustAbsorption() {
        return thrustAbsorption;
    }

    public float getMagicAbsorption() {
        return magicAbsorption;
    }

    public float getFireAbsorption() {
        return fireAbsorption;
    }

    public float getLightningAbsorption() {
        return lightningAbsorption;
    }

    public float getDarkAbsorption() {
        return darkAbsorption;
    }

    public float getBleedResistance() {
        return bleedResistance;
    }

    public float getPoisonResistance() {
        return poisonResistance;
    }

    public float getFrostResistance() {
        return frostResistance;
    }

    public float getCurseResistance() {
        return curseResistance;
    }
}
