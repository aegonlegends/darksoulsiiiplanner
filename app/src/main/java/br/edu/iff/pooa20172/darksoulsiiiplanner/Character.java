
package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.provider.ContactsContract;

import java.io.Serializable;

public class Character implements Serializable {
    private String name;
    private Classe classe;
    private int covenant, vigor, attunement, endurance, vitality, strength, dexterity, intelligence, faith, luck;
    private Weapon leftHand1, leftHand2, leftHand3, rightHand1, rightHand2, rightHand3;
    private Armor helm, chest, gauntlets, leggings;
    private Ring ring1, ring2, ring3, ring4;
    private boolean twoHanded;
    private int[] spells;

    public static final int VIGOR=0, ATTUNEMENT=1, ENDURANCE=2, VITALITY=3, STRENGTH=4, DEXTERITY=5, INTELLIGENCE=6, FAITH=7, LUCK=8;
    private static int[] hpValues = {0,0,0,0,0,0,0,0, 381, 403, 427, 454, 483, 515, 550, 594, 638, 681, 723, 764, 804, 842, 879, 914, 947, 977, 1000, 1019, 1038, 1056, 1074, 1092, 1109, 1125, 1141, 1157, 1172, 1186, 1200, 1213, 1226, 1238, 1249, 1260, 1269, 1278, 1285, 1292, 1297, 1300, 1302, 1304, 1307, 1309, 1312, 1314, 1316, 1319, 1321, 1323, 1326, 1328, 1330, 1333, 1335, 1337, 1340, 1342, 1344, 1346, 1348, 1351, 1353, 1355, 1357, 1359, 1361, 1363, 1365, 1367, 1369, 1371, 1373, 1375, 1377, 1379, 1381, 1383, 1385, 1386, 1388, 1390, 1391, 1393, 1395, 1396, 1397, 1399, 1400};
    private static int[] fpValues = {0,0,0,0,0, 72, 77, 82, 87, 93, 98, 103, 109, 114, 120, 124, 130, 136, 143, 150, 157, 165, 173, 181, 189, 198, 206, 215, 224, 233, 242, 251, 260, 270, 280, 283, 286, 289, 293, 296, 299, 302, 305, 309, 312, 315, 318, 320, 323, 326, 329, 332, 334, 337, 339, 342, 344, 346, 348, 350, 352, 355, 358, 361, 364, 366, 369, 372, 375, 377, 380, 383, 385, 388, 391, 394, 396, 399, 402, 404, 407, 409, 412, 415, 417, 420, 422, 425, 427, 430, 432, 434, 437, 439, 441, 444, 446, 448, 450};
    private static int[] staminaValues = {0,0,0,0,0,0,0,0, 90, 94, 95, 97, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 125, 127, 129, 132, 134, 136, 139, 141, 144, 146, 149, 152, 154, 157, 160};
    private static int[] attSlotsBreakpoints = {10, 14, 18, 24, 30, 40, 50, 60, 80, 99};

    public Character(String name, int covenant, Classe classe) {
        this.classe = classe;
        this.covenant = covenant;
        this.name = name;
        this.vigor = classe.getVigor();
        this.attunement = classe.getAttunement();
        this.endurance = classe.getEndurance();
        this.vitality = classe.getVitality();
        this.strength = classe.getStrength();
        this.dexterity = classe.getDexterity();
        this.intelligence = classe.getIntelligence();
        this.faith = classe.getFaith();
        this.luck = classe.getLuck();
        this.twoHanded = false;
        this.spells = new int[14];
        setDefaultEquip();
    }

    public Character(String name, int covenant, Classe classe, int vigor, int attunement, int endurance, int vitality, int strength, int dexterity, int intelligence, int faith, int luck, Weapon leftHand1, Weapon leftHand2, Weapon leftHand3, Weapon rightHand1, Weapon rightHand2, Weapon rightHand3, Armor helm, Armor chest, Armor gauntlets, Armor leggings, Ring ring1, Ring ring2, Ring ring3, Ring ring4, boolean twoHanded, int[] spells) {
        this.name = name;
        this.classe = classe;
        this.covenant = covenant;
        this.vigor = vigor;
        this.attunement = attunement;
        this.endurance = endurance;
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.faith = faith;
        this.luck = luck;
        this.leftHand1 = leftHand1;
        this.leftHand2 = leftHand2;
        this.leftHand3 = leftHand3;
        this.rightHand1 = rightHand1;
        this.rightHand2 = rightHand2;
        this.rightHand3 = rightHand3;
        this.helm = helm;
        this.chest = chest;
        this.gauntlets = gauntlets;
        this.leggings = leggings;
        this.ring1 = ring1;
        this.ring2 = ring2;
        this.ring3 = ring3;
        this.ring4 = ring4;
        this.twoHanded = twoHanded;
        this.spells = spells;
    }

    private void setDefaultEquip(){
        setLeftHand1(Database.getWeapon(0, Weapon.REGULAR));
        setLeftHand2(Database.getWeapon(0, Weapon.REGULAR));
        setLeftHand3(Database.getWeapon(0, Weapon.REGULAR));

        setRightHand1(Database.getWeapon(0, Weapon.REGULAR));
        setRightHand2(Database.getWeapon(0, Weapon.REGULAR));
        setRightHand3(Database.getWeapon(0, Weapon.REGULAR));

        setHelm(Database.getArmor(0, Armor.TYPE_HELM));
        setChest(Database.getArmor(0, Armor.TYPE_CHESTPIECE));
        setGauntlets(Database.getArmor(0, Armor.TYPE_GAUNTLETS));
        setLeggings(Database.getArmor(0, Armor.TYPE_LEGGINGS));

        setRing1(Database.getRing(0));
        setRing2(Database.getRing(0));
        setRing3(Database.getRing(0));
        setRing4(Database.getRing(0));
    }

    int calculateHP(){
        int vigor = this.vigor + getBonusStat(VIGOR);
        if(vigor>99){ vigor = 99;}

        return hpValues[vigor-1];
    }

    int calculateFP(){
        return fpValues[attunement-1];
    }

    int calculateStamina(){
        int endurance = this.endurance + getBonusStat(ENDURANCE);
        if(endurance>99){ endurance = 99;}

        if(endurance > 40){
            return staminaValues[40] + (endurance-40)/6;
        }
        return staminaValues[endurance];
    }

    double calculateEquipLoad(){
        int vitality = this.vitality + getBonusStat(VITALITY);
        if(vitality>99){ vitality= 99;}

        return 40 + vitality;
    }

    int calculateItemDisc(){
        int luck = this.luck + getBonusStat(LUCK);
        if(luck>99){ luck= 99;}

        return 100 + luck;
    }

    int calculateAttSlots(){
        int attunement = this.attunement + getBonusStat(ATTUNEMENT);
        if(attunement>99){ attunement= 99;}

        for(int i=attSlotsBreakpoints.length-1; i>=0; i--){
            if(attunement >= attSlotsBreakpoints[i]){
                return i+1;
            }
        }

        return 0;
    }

    int calculatePhysicalDefenses(){
        int def = 0;
        def += (vigor + getBonusStat(VIGOR)) * 0.4;
        def += (endurance + getBonusStat(ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(VITALITY)) * 1.5;
        def += (strength + getBonusStat(STRENGTH)) * 0.73;
        def += (dexterity + getBonusStat(DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(FAITH)) * 0.4;
        def += (luck + getBonusStat(LUCK)) * 0.4;
        return def;
    }

    int calculateMagicDefense(){
        int def = 0;
        def += (vigor + getBonusStat(VIGOR)) * 0.4;
        def += (endurance + getBonusStat(ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(VITALITY)) * 0.4;
        def += (strength + getBonusStat(STRENGTH)) * 0.4;
        def += (dexterity + getBonusStat(DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(INTELLIGENCE)) * 1.1;
        def += (faith + getBonusStat(FAITH)) * 0.4;
        def += (luck + getBonusStat(LUCK)) * 0.4;
        return def;
    }

    int calculateFireDefense(){
        int def = 0;
        def += (vigor + getBonusStat(VIGOR)) * 0.4;
        def += (endurance + getBonusStat(ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(VITALITY)) * 0.4;
        def += (strength + getBonusStat(STRENGTH)) * 1.1;
        def += (dexterity + getBonusStat(DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(FAITH)) * 0.4;
        def += (luck + getBonusStat(LUCK)) * 0.4;
        return def;
    }

    int calculateLightningDefense(){
        int def = 0;
        def += (vigor + getBonusStat(VIGOR)) * 0.4;
        def += (endurance + getBonusStat(ENDURANCE)) * 1.1;
        def += (attunement + getBonusStat(ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(VITALITY)) * 0.4;
        def += (strength + getBonusStat(STRENGTH)) * 0.4;
        def += (dexterity + getBonusStat(DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(FAITH)) * 0.4;
        def += (luck + getBonusStat(LUCK)) * 0.4;
        return def;
    }

    int calculateDarkDefense(){
        int def = 0;
        def += (vigor + getBonusStat(VIGOR)) * 0.4;
        def += (endurance + getBonusStat(ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(VITALITY)) * 0.4;
        def += (strength + getBonusStat(STRENGTH)) * 0.4;
        def += (dexterity + getBonusStat(DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(FAITH)) * 1.1;
        def += (luck + getBonusStat(LUCK)) * 0.4;
        return def;
    }

    int calculateBleedResistance(){
        int resist=0;

        resist += (vigor + getBonusStat(VIGOR)) * 0.2;
        resist += (endurance + getBonusStat(ENDURANCE)) * 1.1;
        resist += (attunement + getBonusStat(ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(VITALITY)) * 0.2;
        resist += (strength + getBonusStat(STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(FAITH)) * 0.2;
        resist += (luck + getBonusStat(LUCK)) * 0.2;

        return resist;
    }

    int calculatePoisonResistance(){
        int resist=0;

        resist += (vigor + getBonusStat(VIGOR)) * 0.2;
        resist += (endurance + getBonusStat(ENDURANCE)) * 0.2;
        resist += (attunement + getBonusStat(ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(VITALITY)) * 1.1;
        resist += (strength + getBonusStat(STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(FAITH)) * 0.2;
        resist += (luck + getBonusStat(LUCK)) * 0.2;

        return resist;
    }

    int calculateFrostResistance(){
        int resist=0;

        resist += (vigor + getBonusStat(VIGOR)) * 1.1;
        resist += (endurance + getBonusStat(ENDURANCE)) * 0.2;
        resist += (attunement + getBonusStat(ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(VITALITY)) * 0.2;
        resist += (strength + getBonusStat(STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(FAITH)) * 0.2;
        resist += (luck + getBonusStat(LUCK)) * 0.2;

        return resist;
    }

    int calculateCurseResistance(){
        int resist=0;

        resist += (vigor + getBonusStat(VIGOR)) * 0.2;
        resist += (endurance + getBonusStat(ENDURANCE)) * 0.2;
        resist += (attunement + getBonusStat(ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(VITALITY)) * 0.2;
        resist += (strength + getBonusStat(STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(FAITH)) * 0.2;
        resist += (luck + getBonusStat(LUCK)) * 1.1;

        return resist;
    }

    // Getters and Setters

    public int getLevel() {
        int level = classe.getLevel();

        level += getVigor() - classe.getVigor();
        level += getAttunement() - classe.getAttunement();
        level += getEndurance() - classe.getEndurance();
        level += getVitality() - classe.getVitality();
        level += getStrength() - classe.getStrength();
        level += getDexterity() - classe.getDexterity();
        level += getIntelligence() - classe.getIntelligence();
        level += getFaith() - classe.getFaith();
        level += getLuck() - classe.getLuck();
        return level;
    }

    public int getCovenant() {
        return covenant;
    }

    public void setCovenant(int covenant) {
        this.covenant = covenant;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
        this.vigor = getVigor() < classe.getVigor() ? classe.getVigor() : getVigor();
        this.attunement = getAttunement() < classe.getAttunement() ? classe.getAttunement() : getAttunement();
        this.endurance = getEndurance() < classe.getEndurance() ? classe.getEndurance() : getEndurance();
        this.vitality = getVitality() < classe.getVitality() ? classe.getVitality() : getVitality();
        this.strength = getStrength() < classe.getStrength() ? classe.getStrength() : getStrength();
        this.dexterity = getDexterity() < classe.getDexterity() ? classe.getDexterity() : getDexterity();
        this.intelligence = getIntelligence() < classe.getIntelligence() ? classe.getIntelligence() : getIntelligence();
        this.faith = getFaith() < classe.getFaith() ? classe.getFaith() : getFaith();
        this.luck = getLuck() < classe.getLuck() ? classe.getLuck() : getLuck();
    }

    public void setClasse(int index){
        switch (index){
            case 0 : setClasse(Classe.KNIGHT); break;
            case 1 : setClasse(Classe.MERCENARY); break;
            case 2 : setClasse(Classe.WARRIOR); break;
            case 3 : setClasse(Classe.HERALD); break;
            case 4 : setClasse(Classe.THIEF); break;
            case 5 : setClasse(Classe.ASSASSIN); break;
            case 6 : setClasse(Classe.SORCERER); break;
            case 7 : setClasse(Classe.PYROMANCER); break;
            case 8 : setClasse(Classe.CLERIC); break;
            case 9 : setClasse(Classe.DEPRIVED);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        if(vigor < classe.getVigor()) {
            this.vigor = classe.getVigor();
            return;
        }
        this.vigor = vigor;
    }

    public int getAttunement() {
        return attunement;
    }

    public void setAttunement(int attunement) {
        if(attunement < classe.getAttunement()) {
            this.attunement = classe.getAttunement();
            return;
        }
        this.attunement = attunement;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        if(endurance < classe.getEndurance()) {
            this.endurance = classe.getEndurance();
            return;
        }
        this.endurance = endurance;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        if(vitality < classe.getVitality()) {
            this.vitality = classe.getVitality();
            return;
        }
        this.vitality = vitality;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if(strength < classe.getStrength()) {
            this.strength = classe.getStrength();
            return;
        }
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        if(dexterity < classe.getDexterity()) {
            this.dexterity = classe.getDexterity();
            return;
        }
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        if(intelligence < classe.getIntelligence()) {
            this.intelligence = classe.getIntelligence();
            return;
        }
        this.intelligence = intelligence;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        if(faith < classe.getFaith()) {
            this.faith = classe.getFaith();
            return;
        }
        this.faith = faith;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        if(luck < classe.getLuck()) {
            this.luck = classe.getLuck();
            return;
        }
        this.luck = luck;
    }

    public int getBonusStat(int stat){
        switch (stat){
            case VIGOR:
                return getHelm().getSpecialEffects().getVigor()
                        + getChest().getSpecialEffects().getVigor()
                        + getGauntlets().getSpecialEffects().getVigor()
                        + getLeggings().getSpecialEffects().getVigor()
                        + getRing1().getSpecialEffects().getVigor()
                        + getRing2().getSpecialEffects().getVigor()
                        + getRing3().getSpecialEffects().getVigor()
                        + getRing4().getSpecialEffects().getVigor()
                        + getLeftHand1().getSpecialEffects().getVigor()
                        + getLeftHand2().getSpecialEffects().getVigor()
                        + getLeftHand3().getSpecialEffects().getVigor()
                        + getRightHand1().getSpecialEffects().getVigor()
                        + getRightHand2().getSpecialEffects().getVigor()
                        + getRightHand3().getSpecialEffects().getVigor();
            case ATTUNEMENT:
                return getHelm().getSpecialEffects().getAttunement()
                        + getChest().getSpecialEffects().getAttunement()
                        + getGauntlets().getSpecialEffects().getAttunement()
                        + getLeggings().getSpecialEffects().getAttunement()
                        + getRing1().getSpecialEffects().getAttunement()
                        + getRing2().getSpecialEffects().getAttunement()
                        + getRing3().getSpecialEffects().getAttunement()
                        + getRing4().getSpecialEffects().getAttunement()
                        + getLeftHand1().getSpecialEffects().getAttunement()
                        + getLeftHand2().getSpecialEffects().getAttunement()
                        + getLeftHand3().getSpecialEffects().getAttunement()
                        + getRightHand1().getSpecialEffects().getAttunement()
                        + getRightHand2().getSpecialEffects().getAttunement()
                        + getRightHand3().getSpecialEffects().getAttunement();
            case ENDURANCE:
                return getHelm().getSpecialEffects().getEndurance()
                        + getChest().getSpecialEffects().getEndurance()
                        + getGauntlets().getSpecialEffects().getEndurance()
                        + getLeggings().getSpecialEffects().getEndurance()
                        + getRing1().getSpecialEffects().getEndurance()
                        + getRing2().getSpecialEffects().getEndurance()
                        + getRing3().getSpecialEffects().getEndurance()
                        + getRing4().getSpecialEffects().getEndurance()
                        + getLeftHand1().getSpecialEffects().getEndurance()
                        + getLeftHand2().getSpecialEffects().getEndurance()
                        + getLeftHand3().getSpecialEffects().getEndurance()
                        + getRightHand1().getSpecialEffects().getEndurance()
                        + getRightHand2().getSpecialEffects().getEndurance()
                        + getRightHand3().getSpecialEffects().getEndurance();
            case VITALITY:
                return getHelm().getSpecialEffects().getVitality()
                        + getChest().getSpecialEffects().getVitality()
                        + getGauntlets().getSpecialEffects().getVitality()
                        + getLeggings().getSpecialEffects().getVitality()
                        + getRing1().getSpecialEffects().getVitality()
                        + getRing2().getSpecialEffects().getVitality()
                        + getRing3().getSpecialEffects().getVitality()
                        + getRing4().getSpecialEffects().getVitality()
                        + getLeftHand1().getSpecialEffects().getVitality()
                        + getLeftHand2().getSpecialEffects().getVitality()
                        + getLeftHand3().getSpecialEffects().getVitality()
                        + getRightHand1().getSpecialEffects().getVitality()
                        + getRightHand2().getSpecialEffects().getVitality()
                        + getRightHand3().getSpecialEffects().getVitality();
            case STRENGTH:
                return getHelm().getSpecialEffects().getStrength()
                        + getChest().getSpecialEffects().getStrength()
                        + getGauntlets().getSpecialEffects().getStrength()
                        + getLeggings().getSpecialEffects().getStrength()
                        + getRing1().getSpecialEffects().getStrength()
                        + getRing2().getSpecialEffects().getStrength()
                        + getRing3().getSpecialEffects().getStrength()
                        + getRing4().getSpecialEffects().getStrength()
                        + getLeftHand1().getSpecialEffects().getStrength()
                        + getLeftHand2().getSpecialEffects().getStrength()
                        + getLeftHand3().getSpecialEffects().getStrength()
                        + getRightHand1().getSpecialEffects().getStrength()
                        + getRightHand2().getSpecialEffects().getStrength()
                        + getRightHand3().getSpecialEffects().getStrength();
            case DEXTERITY:
                return getHelm().getSpecialEffects().getDexterity()
                        + getChest().getSpecialEffects().getDexterity()
                        + getGauntlets().getSpecialEffects().getDexterity()
                        + getLeggings().getSpecialEffects().getDexterity()
                        + getRing1().getSpecialEffects().getDexterity()
                        + getRing2().getSpecialEffects().getDexterity()
                        + getRing3().getSpecialEffects().getDexterity()
                        + getRing4().getSpecialEffects().getDexterity()
                        + getLeftHand1().getSpecialEffects().getDexterity()
                        + getLeftHand2().getSpecialEffects().getDexterity()
                        + getLeftHand3().getSpecialEffects().getDexterity()
                        + getRightHand1().getSpecialEffects().getDexterity()
                        + getRightHand2().getSpecialEffects().getDexterity()
                        + getRightHand3().getSpecialEffects().getDexterity();
            case INTELLIGENCE:
                return getHelm().getSpecialEffects().getIntelligence()
                        + getChest().getSpecialEffects().getIntelligence()
                        + getGauntlets().getSpecialEffects().getIntelligence()
                        + getLeggings().getSpecialEffects().getIntelligence()
                        + getRing1().getSpecialEffects().getIntelligence()
                        + getRing2().getSpecialEffects().getIntelligence()
                        + getRing3().getSpecialEffects().getIntelligence()
                        + getRing4().getSpecialEffects().getIntelligence()
                        + getLeftHand1().getSpecialEffects().getIntelligence()
                        + getLeftHand2().getSpecialEffects().getIntelligence()
                        + getLeftHand3().getSpecialEffects().getIntelligence()
                        + getRightHand1().getSpecialEffects().getIntelligence()
                        + getRightHand2().getSpecialEffects().getIntelligence()
                        + getRightHand3().getSpecialEffects().getIntelligence();
            case FAITH:
                return getHelm().getSpecialEffects().getFaith()
                        + getChest().getSpecialEffects().getFaith()
                        + getGauntlets().getSpecialEffects().getFaith()
                        + getLeggings().getSpecialEffects().getFaith()
                        + getRing1().getSpecialEffects().getFaith()
                        + getRing2().getSpecialEffects().getFaith()
                        + getRing3().getSpecialEffects().getFaith()
                        + getRing4().getSpecialEffects().getFaith()
                        + getLeftHand1().getSpecialEffects().getFaith()
                        + getLeftHand2().getSpecialEffects().getFaith()
                        + getLeftHand3().getSpecialEffects().getFaith()
                        + getRightHand1().getSpecialEffects().getFaith()
                        + getRightHand2().getSpecialEffects().getFaith()
                        + getRightHand3().getSpecialEffects().getFaith();
            case LUCK:
                return getHelm().getSpecialEffects().getLuck()
                        + getChest().getSpecialEffects().getLuck()
                        + getGauntlets().getSpecialEffects().getLuck()
                        + getLeggings().getSpecialEffects().getLuck()
                        + getRing1().getSpecialEffects().getLuck()
                        + getRing2().getSpecialEffects().getLuck()
                        + getRing3().getSpecialEffects().getLuck()
                        + getRing4().getSpecialEffects().getLuck()
                        + getLeftHand1().getSpecialEffects().getLuck()
                        + getLeftHand2().getSpecialEffects().getLuck()
                        + getLeftHand3().getSpecialEffects().getLuck()
                        + getRightHand1().getSpecialEffects().getLuck()
                        + getRightHand2().getSpecialEffects().getLuck()
                        + getRightHand3().getSpecialEffects().getLuck();
            default: throw new IllegalArgumentException();
        }
    }

    public Weapon getLeftHand1() {
        return leftHand1;
    }

    public void setLeftHand1(Weapon leftHand1) {
        this.leftHand1 = leftHand1;
    }

    public Weapon getLeftHand2() {
        return leftHand2;
    }

    public void setLeftHand2(Weapon leftHand2) {
        this.leftHand2 = leftHand2;
    }

    public Weapon getLeftHand3() {
        return leftHand3;
    }

    public void setLeftHand3(Weapon leftHand3) {
        this.leftHand3 = leftHand3;
    }

    public Weapon getRightHand1() {
        return rightHand1;
    }

    public void setRightHand1(Weapon rightHand1) {
        this.rightHand1 = rightHand1;
    }

    public Weapon getRightHand2() {
        return rightHand2;
    }

    public void setRightHand2(Weapon rightHand2) {
        this.rightHand2 = rightHand2;
    }

    public Weapon getRightHand3() {
        return rightHand3;
    }

    public void setRightHand3(Weapon rightHand3) {
        this.rightHand3 = rightHand3;
    }

    public Armor getHelm() {
        return helm;
    }

    public void setHelm(Armor helm) {
        this.helm = helm;
    }

    public Armor getChest() {
        return chest;
    }

    public void setChest(Armor chest) {
        this.chest = chest;
    }

    public Armor getGauntlets() {
        return gauntlets;
    }

    public void setGauntlets(Armor gauntlets) {
        this.gauntlets = gauntlets;
    }

    public Armor getLeggings() {
        return leggings;
    }

    public void setLeggings(Armor leggings) {
        this.leggings = leggings;
    }

    public Ring getRing1() {
        return ring1;
    }

    public void setRing1(Ring ring1) {
        this.ring1 = ring1;
    }

    public Ring getRing2() {
        return ring2;
    }

    public void setRing2(Ring ring2) {
        this.ring2 = ring2;
    }

    public Ring getRing3() {
        return ring3;
    }

    public void setRing3(Ring ring3) {
        this.ring3 = ring3;
    }

    public Ring getRing4() {
        return ring4;
    }

    public void setRing4(Ring ring4) {
        this.ring4 = ring4;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    public int[] getSpells() {
        return spells;
    }

    public void setSpells(int[] spells) {
        this.spells = spells;
    }
}