package br.edu.iff.pooa20172.darksoulsiiiplanner;

public class SpecialEffect {
    private int index, vigor, attunement, endurance, vitality, strength, dexterity, intelligence, faith, luck, attunementSlot;
    private double damage, damagePercent, hpPercent, staminaPercent, equipLoadPercent, physicalAbsorption, strikeAbsorption, slashAbsorption, thrustAbsorption, magicAbsorption,fireAbsorption,lightningAbsorption,darkAbsorption, bleedResistance, poisonResistance, frostResistance, curseResistance;
    private String description;

    public SpecialEffect(int index) {
        this.index = index;
        this.vigor = 0;
        this.attunement = 0;
        this.endurance = 0;
        this.vitality = 0;
        this.strength = 0;
        this.dexterity = 0;
        this.intelligence = 0;
        this.faith = 0;
        this.luck = 0;
        this.attunementSlot = 0;
        this.damage = 0;
        this.damagePercent = 1;
        this.hpPercent = 1;
        this.staminaPercent = 1;
        this.equipLoadPercent = 1;
        this.physicalAbsorption = 0;
        this.strikeAbsorption = 0;
        this.slashAbsorption = 0;
        this.thrustAbsorption = 0;
        this.magicAbsorption = 0;
        this.fireAbsorption = 0;
        this.lightningAbsorption = 0;
        this.darkAbsorption = 0;
        this.bleedResistance = 0;
        this.poisonResistance = 0;
        this.frostResistance = 0;
        this.curseResistance = 0;
        this.description = "";
    }
    public SpecialEffect(){
        this(0);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        this.vigor = vigor;
    }

    public int getAttunement() {
        return attunement;
    }

    public void setAttunement(int attunement) {
        this.attunement = attunement;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getAttunementSlot() {
        return attunementSlot;
    }

    public void setAttunementSlot(int attunementSlot) {
        this.attunementSlot = attunementSlot;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamagePercent() {
        return damagePercent;
    }

    public void setDamagePercent(double damagePercent) {
        this.damagePercent = damagePercent;
    }

    public double getHpPercent() {
        return hpPercent;
    }

    public void setHpPercent(double hpPercent) {
        this.hpPercent = hpPercent;
    }

    public double getStaminaPercent() {
        return staminaPercent;
    }

    public void setStaminaPercent(double staminaPercent) {
        this.staminaPercent = staminaPercent;
    }

    public double getEquipLoadPercent() {
        return equipLoadPercent;
    }

    public void setEquipLoadPercent(double equipLoadPercent) {
        this.equipLoadPercent = equipLoadPercent;
    }

    public double getPhysicalAbsorption() {
        return physicalAbsorption;
    }

    public void setPhysicalAbsorption(double physicalAbsorption) {
        this.physicalAbsorption = physicalAbsorption;
    }

    public double getStrikeAbsorption() {
        return strikeAbsorption;
    }

    public void setStrikeAbsorption(double strikeAbsorption) {
        this.strikeAbsorption = strikeAbsorption;
    }

    public double getSlashAbsorption() {
        return slashAbsorption;
    }

    public void setSlashAbsorption(double slashAbsorption) {
        this.slashAbsorption = slashAbsorption;
    }

    public double getThrustAbsorption() {
        return thrustAbsorption;
    }

    public void setThrustAbsorption(double thrustAbsorption) {
        this.thrustAbsorption = thrustAbsorption;
    }

    public double getMagicAbsorption() {
        return magicAbsorption;
    }

    public void setMagicAbsorption(double magicAbsorption) {
        this.magicAbsorption = magicAbsorption;
    }

    public double getFireAbsorption() {
        return fireAbsorption;
    }

    public void setFireAbsorption(double fireAbsorption) {
        this.fireAbsorption = fireAbsorption;
    }

    public double getLightningAbsorption() {
        return lightningAbsorption;
    }

    public void setLightningAbsorption(double lightningAbsorption) {
        this.lightningAbsorption = lightningAbsorption;
    }

    public double getDarkAbsorption() {
        return darkAbsorption;
    }

    public void setDarkAbsorption(double darkAbsorption) {
        this.darkAbsorption = darkAbsorption;
    }

    public double getBleedResistance() {
        return bleedResistance;
    }

    public void setBleedResistance(double bleedResistance) {
        this.bleedResistance = bleedResistance;
    }

    public double getPoisonResistance() {
        return poisonResistance;
    }

    public void setPoisonResistance(double poisonResistance) {
        this.poisonResistance = poisonResistance;
    }

    public double getFrostResistance() {
        return frostResistance;
    }

    public void setFrostResistance(double frostResistance) {
        this.frostResistance = frostResistance;
    }

    public double getCurseResistance() {
        return curseResistance;
    }

    public void setCurseResistance(double curseResistance) {
        this.curseResistance = curseResistance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
