
package br.edu.iff.pooa20172.darksoulsiiiplanner.Model;

import java.io.Serializable;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Persistence.CharacterDB;

public class Character implements Serializable {
    private int id = -1;
    private String name;
    private Classe classe;
    private int covenant, vigor, attunement, endurance, vitality, strength, dexterity, intelligence, faith, luck;
    private Weapon leftHand1, leftHand2, leftHand3, rightHand1, rightHand2, rightHand3;
    private Armor helm, chest, gauntlets, leggings;
    private Ring ring1, ring2, ring3, ring4;
    private boolean twoHanded;
    private int[] spells;

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

    public Character(CharacterDB chara){
        this.id = chara.getId();
        this.name = chara.getName();
        this.classe = Classe.fromIndex(chara.getClasse());
        this.covenant = chara.getCovenant();
        this.vigor = chara.getVigor();
        this.attunement = chara.getAttunement();
        this.endurance = chara.getEndurance();
        this.vitality = chara.getVitality();
        this.strength = chara.getStrength();
        this.dexterity = chara.getDexterity();
        this.intelligence = chara.getIntelligence();
        this.faith = chara.getFaith();
        this.luck = chara.getLuck();
        this.leftHand1 = EquipmentData.getWeapon(chara.getLeftHand1(), Weapon.REGULAR);
        this.leftHand2 = EquipmentData.getWeapon(chara.getLeftHand2(), Weapon.REGULAR);
        this.leftHand3 = EquipmentData.getWeapon(chara.getLeftHand3(), Weapon.REGULAR);
        this.rightHand1 = EquipmentData.getWeapon(chara.getRightHand1(), Weapon.REGULAR);
        this.rightHand2 = EquipmentData.getWeapon(chara.getRightHand2(), Weapon.REGULAR);
        this.rightHand3 = EquipmentData.getWeapon(chara.getRightHand3(), Weapon.REGULAR);
        this.helm = EquipmentData.getArmor(chara.getHelm(), Armor.TYPE_HELM);
        this.chest = EquipmentData.getArmor(chara.getChest(), Armor.TYPE_CHESTPIECE);
        this.gauntlets = EquipmentData.getArmor(chara.getGauntlets(), Armor.TYPE_GAUNTLETS);
        this.leggings = EquipmentData.getArmor(chara.getLeggings(), Armor.TYPE_LEGGINGS);
        this.ring1 = EquipmentData.getRing(chara.getRing1());
        this.ring2 = EquipmentData.getRing(chara.getRing2());
        this.ring3 = EquipmentData.getRing(chara.getRing3());
        this.ring4 = EquipmentData.getRing(chara.getRing4());
        this.twoHanded = chara.isTwoHanded();
        this.spells = new int[chara.getSpells().size()];
        Integer[] getspells = chara.getSpells().toArray(new Integer[chara.getSpells().size()]);

        for(int i=0; i < getspells.length; i++){
            spells[i] = getspells[i];
        }
    }

    private void setDefaultEquip(){
        setLeftHand1(EquipmentData.getWeapon(0, Weapon.REGULAR));
        setLeftHand2(EquipmentData.getWeapon(0, Weapon.REGULAR));
        setLeftHand3(EquipmentData.getWeapon(0, Weapon.REGULAR));

        setRightHand1(EquipmentData.getWeapon(0, Weapon.REGULAR));
        setRightHand2(EquipmentData.getWeapon(0, Weapon.REGULAR));
        setRightHand3(EquipmentData.getWeapon(0, Weapon.REGULAR));

        setHelm(EquipmentData.getArmor(0, Armor.TYPE_HELM));
        setChest(EquipmentData.getArmor(0, Armor.TYPE_CHESTPIECE));
        setGauntlets(EquipmentData.getArmor(0, Armor.TYPE_GAUNTLETS));
        setLeggings(EquipmentData.getArmor(0, Armor.TYPE_LEGGINGS));

        setRing1(EquipmentData.getRing(0));
        setRing2(EquipmentData.getRing(0));
        setRing3(EquipmentData.getRing(0));
        setRing4(EquipmentData.getRing(0));
    }

    public int calculateHP(){
        int vigor = this.vigor + getBonusStat(SpecialEffect.VIGOR);
        if(vigor>99){ vigor = 99;}

        return hpValues[vigor-1];
    }

    public int calculateFP(){
        return fpValues[attunement-1];
    }

    public int calculateStamina(){
        int endurance = this.endurance + getBonusStat(SpecialEffect.ENDURANCE);
        if(endurance>99){ endurance = 99;}

        if(endurance > 40){
            return staminaValues[40] + (endurance-40)/6;
        }
        return staminaValues[endurance];
    }

    public double calculateEquipLoad(){
        int vitality = this.vitality + getBonusStat(SpecialEffect.VITALITY);
        if(vitality>99){ vitality= 99;}

        return 40 + vitality;
    }

    public int calculateItemDisc(){
        int luck = this.luck + getBonusStat(SpecialEffect.LUCK);
        if(luck>99){ luck= 99;}

        return 100 + luck;
    }

    public int calculateAttSlots(){
        int attunement = this.attunement + getBonusStat(SpecialEffect.ATTUNEMENT);
        if(attunement>99){ attunement= 99;}

        for(int i=attSlotsBreakpoints.length-1; i>=0; i--){
            if(attunement >= attSlotsBreakpoints[i]){
                return i+1;
            }
        }

        return 0;
    }

    public int calculatePhysicalDefenses(){
        double def = 0;
        def += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.4;
        def += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 1.5;
        def += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.73;
        def += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.4;
        def += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.4;
        return (int) Math.floor(def);
    }

    public int calculateMagicDefense(){
        double def = 0;
        def += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.4;
        def += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.4;
        def += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.4;
        def += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 1.1;
        def += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.4;
        def += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.4;
        return (int) Math.floor(def);
    }

    public int calculateFireDefense(){
        double def = 0;
        def += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.4;
        def += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.4;
        def += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 1.1;
        def += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.4;
        def += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.4;
        return (int) Math.floor(def);
    }

    public int calculateLightningDefense(){
        double def = 0;
        def += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.4;
        def += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 1.1;
        def += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.4;
        def += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.4;
        def += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.4;
        def += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.4;
        return (int) Math.floor(def);
    }

    public int calculateDarkDefense(){
        double def = 0;
        def += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.4;
        def += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.4;
        def += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.4;
        def += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.4;
        def += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.4;
        def += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.4;
        def += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.4;
        def += (faith + getBonusStat(SpecialEffect.FAITH)) * 1.1;
        def += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.4;
        return (int) Math.floor(def);
    }

    public int calculateBleedResistance(){
        double resist = 0;

        resist += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.2;
        resist += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 1.1;
        resist += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.2;
        resist += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.2;
        resist += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.2;

        return (int) Math.floor(resist);
    }

    public int calculatePoisonResistance(){
        double resist = 0;

        resist += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.2;
        resist += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.2;
        resist += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 1.1;
        resist += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.2;
        resist += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.2;

        return (int) Math.floor(resist);
    }

    public int calculateFrostResistance(){
        double resist = 0;

        resist += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 1.1;
        resist += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.2;
        resist += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.2;
        resist += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.2;
        resist += (luck + getBonusStat(SpecialEffect.LUCK)) * 0.2;

        return (int) Math.floor(resist);
    }

    public int calculateCurseResistance(){
        double resist = 0;

        resist += (vigor + getBonusStat(SpecialEffect.VIGOR)) * 0.2;
        resist += (endurance + getBonusStat(SpecialEffect.ENDURANCE)) * 0.2;
        resist += (attunement + getBonusStat(SpecialEffect.ATTUNEMENT)) * 0.2;
        resist += (vitality + getBonusStat(SpecialEffect.VITALITY)) * 0.2;
        resist += (strength + getBonusStat(SpecialEffect.STRENGTH)) * 0.2;
        resist += (dexterity + getBonusStat(SpecialEffect.DEXTERITY)) * 0.2;
        resist += (intelligence + getBonusStat(SpecialEffect.INTELLIGENCE)) * 0.2;
        resist += (faith + getBonusStat(SpecialEffect.FAITH)) * 0.2;
        resist += (luck + getBonusStat(SpecialEffect.LUCK)) * 1.1;

        return (int) Math.floor(resist);
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
            case SpecialEffect.VIGOR:
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
            case SpecialEffect.ATTUNEMENT:
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
            case SpecialEffect.ENDURANCE:
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
            case SpecialEffect.VITALITY:
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
            case SpecialEffect.STRENGTH:
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
            case SpecialEffect.DEXTERITY:
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
            case SpecialEffect.INTELLIGENCE:
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
            case SpecialEffect.FAITH:
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
            case SpecialEffect.LUCK:
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
            case SpecialEffect.ATTUNEMENT_SLOTS:
                return getHelm().getSpecialEffects().getAttunementSlot()
                        * getChest().getSpecialEffects().getAttunementSlot()
                        * getGauntlets().getSpecialEffects().getAttunementSlot()
                        * getLeggings().getSpecialEffects().getAttunementSlot()
                        * getRing1().getSpecialEffects().getAttunementSlot()
                        * getRing2().getSpecialEffects().getAttunementSlot()
                        * getRing3().getSpecialEffects().getAttunementSlot()
                        * getRing4().getSpecialEffects().getAttunementSlot()
                        * getLeftHand1().getSpecialEffects().getAttunementSlot()
                        * getLeftHand2().getSpecialEffects().getAttunementSlot()
                        * getLeftHand3().getSpecialEffects().getAttunementSlot()
                        * getRightHand1().getSpecialEffects().getAttunementSlot()
                        * getRightHand2().getSpecialEffects().getAttunementSlot()
                        * getRightHand3().getSpecialEffects().getAttunementSlot();
            case SpecialEffect.POISE:
                return getHelm().getSpecialEffects().getPoise()
                        * getChest().getSpecialEffects().getPoise()
                        * getGauntlets().getSpecialEffects().getPoise()
                        * getLeggings().getSpecialEffects().getPoise()
                        * getRing1().getSpecialEffects().getPoise()
                        * getRing2().getSpecialEffects().getPoise()
                        * getRing3().getSpecialEffects().getPoise()
                        * getRing4().getSpecialEffects().getPoise()
                        * getLeftHand1().getSpecialEffects().getPoise()
                        * getLeftHand2().getSpecialEffects().getPoise()
                        * getLeftHand3().getSpecialEffects().getPoise()
                        * getRightHand1().getSpecialEffects().getPoise()
                        * getRightHand2().getSpecialEffects().getPoise()
                        * getRightHand3().getSpecialEffects().getPoise();

            case SpecialEffect.ITEM_DISCOVERY:
                return getHelm().getSpecialEffects().getItemDiscovery()
                        * getChest().getSpecialEffects().getItemDiscovery()
                        * getGauntlets().getSpecialEffects().getItemDiscovery()
                        * getLeggings().getSpecialEffects().getItemDiscovery()
                        * getRing1().getSpecialEffects().getItemDiscovery()
                        * getRing2().getSpecialEffects().getItemDiscovery()
                        * getRing3().getSpecialEffects().getItemDiscovery()
                        * getRing4().getSpecialEffects().getItemDiscovery()
                        * getLeftHand1().getSpecialEffects().getItemDiscovery()
                        * getLeftHand2().getSpecialEffects().getItemDiscovery()
                        * getLeftHand3().getSpecialEffects().getItemDiscovery()
                        * getRightHand1().getSpecialEffects().getItemDiscovery()
                        * getRightHand2().getSpecialEffects().getItemDiscovery()
                        * getRightHand3().getSpecialEffects().getItemDiscovery();
            default: throw new IllegalArgumentException();
        }
    }

    public double getBonusMultipliers(int multiplier){
        double abs;
        double[] nums;

        switch (multiplier){
            case SpecialEffect.DAMAGE_MULTIPLIER:
                return getHelm().getSpecialEffects().getDamagePercent()
                        + getChest().getSpecialEffects().getDamagePercent()
                        + getGauntlets().getSpecialEffects().getDamagePercent()
                        + getLeggings().getSpecialEffects().getDamagePercent()
                        + getRing1().getSpecialEffects().getDamagePercent()
                        + getRing2().getSpecialEffects().getDamagePercent()
                        + getRing3().getSpecialEffects().getDamagePercent()
                        + getRing4().getSpecialEffects().getDamagePercent()
                        + getLeftHand1().getSpecialEffects().getDamagePercent()
                        + getLeftHand2().getSpecialEffects().getDamagePercent()
                        + getLeftHand3().getSpecialEffects().getDamagePercent()
                        + getRightHand1().getSpecialEffects().getDamagePercent()
                        + getRightHand2().getSpecialEffects().getDamagePercent()
                        + getRightHand3().getSpecialEffects().getDamagePercent();
            case SpecialEffect.HP_MULTIPLIER:
                return getHelm().getSpecialEffects().getHpPercent()
                        * getChest().getSpecialEffects().getHpPercent()
                        * getGauntlets().getSpecialEffects().getHpPercent()
                        * getLeggings().getSpecialEffects().getHpPercent()
                        * getRing1().getSpecialEffects().getHpPercent()
                        * getRing2().getSpecialEffects().getHpPercent()
                        * getRing3().getSpecialEffects().getHpPercent()
                        * getRing4().getSpecialEffects().getHpPercent()
                        * getLeftHand1().getSpecialEffects().getHpPercent()
                        * getLeftHand2().getSpecialEffects().getHpPercent()
                        * getLeftHand3().getSpecialEffects().getHpPercent()
                        * getRightHand1().getSpecialEffects().getHpPercent()
                        * getRightHand2().getSpecialEffects().getHpPercent()
                        * getRightHand3().getSpecialEffects().getHpPercent();
            case SpecialEffect.STAMINA_MULTIPLIER:
                return getHelm().getSpecialEffects().getStaminaPercent()
                        * getChest().getSpecialEffects().getStaminaPercent()
                        * getGauntlets().getSpecialEffects().getStaminaPercent()
                        * getLeggings().getSpecialEffects().getStaminaPercent()
                        * getRing1().getSpecialEffects().getStaminaPercent()
                        * getRing2().getSpecialEffects().getStaminaPercent()
                        * getRing3().getSpecialEffects().getStaminaPercent()
                        * getRing4().getSpecialEffects().getStaminaPercent()
                        * getLeftHand1().getSpecialEffects().getStaminaPercent()
                        * getLeftHand2().getSpecialEffects().getStaminaPercent()
                        * getLeftHand3().getSpecialEffects().getStaminaPercent()
                        * getRightHand1().getSpecialEffects().getStaminaPercent()
                        * getRightHand2().getSpecialEffects().getStaminaPercent()
                        * getRightHand3().getSpecialEffects().getStaminaPercent();
            case SpecialEffect.EQUIPMENT_LOAD_MULTIPLIER:
                return getHelm().getSpecialEffects().getEquipLoadPercent()
                        * getChest().getSpecialEffects().getEquipLoadPercent()
                        * getGauntlets().getSpecialEffects().getEquipLoadPercent()
                        * getLeggings().getSpecialEffects().getEquipLoadPercent()
                        * getRing1().getSpecialEffects().getEquipLoadPercent()
                        * getRing2().getSpecialEffects().getEquipLoadPercent()
                        * getRing3().getSpecialEffects().getEquipLoadPercent()
                        * getRing4().getSpecialEffects().getEquipLoadPercent()
                        * getLeftHand1().getSpecialEffects().getEquipLoadPercent()
                        * getLeftHand2().getSpecialEffects().getEquipLoadPercent()
                        * getLeftHand3().getSpecialEffects().getEquipLoadPercent()
                        * getRightHand1().getSpecialEffects().getEquipLoadPercent()
                        * getRightHand2().getSpecialEffects().getEquipLoadPercent()
                        * getRightHand3().getSpecialEffects().getEquipLoadPercent();
            case SpecialEffect.PHYSICAL_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getPhysicalAbsorption()
                        , getChest().getSpecialEffects().getPhysicalAbsorption()
                        , getGauntlets().getSpecialEffects().getPhysicalAbsorption()
                        , getLeggings().getSpecialEffects().getPhysicalAbsorption()
                        , getRing1().getSpecialEffects().getPhysicalAbsorption()
                        , getRing2().getSpecialEffects().getPhysicalAbsorption()
                        , getRing3().getSpecialEffects().getPhysicalAbsorption()
                        , getRing4().getSpecialEffects().getPhysicalAbsorption()
                        , getLeftHand1().getSpecialEffects().getPhysicalAbsorption()
                        , getLeftHand2().getSpecialEffects().getPhysicalAbsorption()
                        , getLeftHand3().getSpecialEffects().getPhysicalAbsorption()
                        , getRightHand1().getSpecialEffects().getPhysicalAbsorption()
                        , getRightHand2().getSpecialEffects().getPhysicalAbsorption()
                        , getRightHand3().getSpecialEffects().getPhysicalAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.STRIKE_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getStrikeAbsorption()
                        , getChest().getSpecialEffects().getStrikeAbsorption()
                        , getGauntlets().getSpecialEffects().getStrikeAbsorption()
                        , getLeggings().getSpecialEffects().getStrikeAbsorption()
                        , getRing1().getSpecialEffects().getStrikeAbsorption()
                        , getRing2().getSpecialEffects().getStrikeAbsorption()
                        , getRing3().getSpecialEffects().getStrikeAbsorption()
                        , getRing4().getSpecialEffects().getStrikeAbsorption()
                        , getLeftHand1().getSpecialEffects().getStrikeAbsorption()
                        , getLeftHand2().getSpecialEffects().getStrikeAbsorption()
                        , getLeftHand3().getSpecialEffects().getStrikeAbsorption()
                        , getRightHand1().getSpecialEffects().getStrikeAbsorption()
                        , getRightHand2().getSpecialEffects().getStrikeAbsorption()
                        , getRightHand3().getSpecialEffects().getStrikeAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.SLASH_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getSlashAbsorption()
                        , getChest().getSpecialEffects().getSlashAbsorption()
                        , getGauntlets().getSpecialEffects().getSlashAbsorption()
                        , getLeggings().getSpecialEffects().getSlashAbsorption()
                        , getRing1().getSpecialEffects().getSlashAbsorption()
                        , getRing2().getSpecialEffects().getSlashAbsorption()
                        , getRing3().getSpecialEffects().getSlashAbsorption()
                        , getRing4().getSpecialEffects().getSlashAbsorption()
                        , getLeftHand1().getSpecialEffects().getSlashAbsorption()
                        , getLeftHand2().getSpecialEffects().getSlashAbsorption()
                        , getLeftHand3().getSpecialEffects().getSlashAbsorption()
                        , getRightHand1().getSpecialEffects().getSlashAbsorption()
                        , getRightHand2().getSpecialEffects().getSlashAbsorption()
                        , getRightHand3().getSpecialEffects().getSlashAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.THRUST_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getThrustAbsorption()
                        , getChest().getSpecialEffects().getThrustAbsorption()
                        , getGauntlets().getSpecialEffects().getThrustAbsorption()
                        , getLeggings().getSpecialEffects().getThrustAbsorption()
                        , getRing1().getSpecialEffects().getThrustAbsorption()
                        , getRing2().getSpecialEffects().getThrustAbsorption()
                        , getRing3().getSpecialEffects().getThrustAbsorption()
                        , getRing4().getSpecialEffects().getThrustAbsorption()
                        , getLeftHand1().getSpecialEffects().getThrustAbsorption()
                        , getLeftHand2().getSpecialEffects().getThrustAbsorption()
                        , getLeftHand3().getSpecialEffects().getThrustAbsorption()
                        , getRightHand1().getSpecialEffects().getThrustAbsorption()
                        , getRightHand2().getSpecialEffects().getThrustAbsorption()
                        , getRightHand3().getSpecialEffects().getThrustAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.MAGIC_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getMagicAbsorption()
                        , getChest().getSpecialEffects().getMagicAbsorption()
                        , getGauntlets().getSpecialEffects().getMagicAbsorption()
                        , getLeggings().getSpecialEffects().getMagicAbsorption()
                        , getRing1().getSpecialEffects().getMagicAbsorption()
                        , getRing2().getSpecialEffects().getMagicAbsorption()
                        , getRing3().getSpecialEffects().getMagicAbsorption()
                        , getRing4().getSpecialEffects().getMagicAbsorption()
                        , getLeftHand1().getSpecialEffects().getMagicAbsorption()
                        , getLeftHand2().getSpecialEffects().getMagicAbsorption()
                        , getLeftHand3().getSpecialEffects().getMagicAbsorption()
                        , getRightHand1().getSpecialEffects().getMagicAbsorption()
                        , getRightHand2().getSpecialEffects().getMagicAbsorption()
                        , getRightHand3().getSpecialEffects().getMagicAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.FIRE_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getFireAbsorption()
                        , getChest().getSpecialEffects().getFireAbsorption()
                        , getGauntlets().getSpecialEffects().getFireAbsorption()
                        , getLeggings().getSpecialEffects().getFireAbsorption()
                        , getRing1().getSpecialEffects().getFireAbsorption()
                        , getRing2().getSpecialEffects().getFireAbsorption()
                        , getRing3().getSpecialEffects().getFireAbsorption()
                        , getRing4().getSpecialEffects().getFireAbsorption()
                        , getLeftHand1().getSpecialEffects().getFireAbsorption()
                        , getLeftHand2().getSpecialEffects().getFireAbsorption()
                        , getLeftHand3().getSpecialEffects().getFireAbsorption()
                        , getRightHand1().getSpecialEffects().getFireAbsorption()
                        , getRightHand2().getSpecialEffects().getFireAbsorption()
                        , getRightHand3().getSpecialEffects().getFireAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.LIGHTNING_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getLightningAbsorption()
                        , getChest().getSpecialEffects().getLightningAbsorption()
                        , getGauntlets().getSpecialEffects().getLightningAbsorption()
                        , getLeggings().getSpecialEffects().getLightningAbsorption()
                        , getRing1().getSpecialEffects().getLightningAbsorption()
                        , getRing2().getSpecialEffects().getLightningAbsorption()
                        , getRing3().getSpecialEffects().getLightningAbsorption()
                        , getRing4().getSpecialEffects().getLightningAbsorption()
                        , getLeftHand1().getSpecialEffects().getLightningAbsorption()
                        , getLeftHand2().getSpecialEffects().getLightningAbsorption()
                        , getLeftHand3().getSpecialEffects().getLightningAbsorption()
                        , getRightHand1().getSpecialEffects().getLightningAbsorption()
                        , getRightHand2().getSpecialEffects().getLightningAbsorption()
                        , getRightHand3().getSpecialEffects().getLightningAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.DARK_ABSORPTION:
                abs = 1;
                nums = new double[]{getHelm().getSpecialEffects().getDarkAbsorption()
                        , getChest().getSpecialEffects().getDarkAbsorption()
                        , getGauntlets().getSpecialEffects().getDarkAbsorption()
                        , getLeggings().getSpecialEffects().getDarkAbsorption()
                        , getRing1().getSpecialEffects().getDarkAbsorption()
                        , getRing2().getSpecialEffects().getDarkAbsorption()
                        , getRing3().getSpecialEffects().getDarkAbsorption()
                        , getRing4().getSpecialEffects().getDarkAbsorption()
                        , getLeftHand1().getSpecialEffects().getDarkAbsorption()
                        , getLeftHand2().getSpecialEffects().getDarkAbsorption()
                        , getLeftHand3().getSpecialEffects().getDarkAbsorption()
                        , getRightHand1().getSpecialEffects().getDarkAbsorption()
                        , getRightHand2().getSpecialEffects().getDarkAbsorption()
                        , getRightHand3().getSpecialEffects().getDarkAbsorption()};
                for(double num : nums){
                    abs *= 1-(num/100);
                }
                return abs;
            case SpecialEffect.BLEED_RESISTANCE:
                return getHelm().getSpecialEffects().getBleedResistance()
                        * getChest().getSpecialEffects().getBleedResistance()
                        * getGauntlets().getSpecialEffects().getBleedResistance()
                        * getLeggings().getSpecialEffects().getBleedResistance()
                        * getRing1().getSpecialEffects().getBleedResistance()
                        * getRing2().getSpecialEffects().getBleedResistance()
                        * getRing3().getSpecialEffects().getBleedResistance()
                        * getRing4().getSpecialEffects().getBleedResistance()
                        * getLeftHand1().getSpecialEffects().getBleedResistance()
                        * getLeftHand2().getSpecialEffects().getBleedResistance()
                        * getLeftHand3().getSpecialEffects().getBleedResistance()
                        * getRightHand1().getSpecialEffects().getBleedResistance()
                        * getRightHand2().getSpecialEffects().getBleedResistance()
                        * getRightHand3().getSpecialEffects().getBleedResistance();
            case SpecialEffect.POISON_RESISTANCE:
                return getHelm().getSpecialEffects().getPoisonResistance()
                        * getChest().getSpecialEffects().getPoisonResistance()
                        * getGauntlets().getSpecialEffects().getPoisonResistance()
                        * getLeggings().getSpecialEffects().getPoisonResistance()
                        * getRing1().getSpecialEffects().getPoisonResistance()
                        * getRing2().getSpecialEffects().getPoisonResistance()
                        * getRing3().getSpecialEffects().getPoisonResistance()
                        * getRing4().getSpecialEffects().getPoisonResistance()
                        * getLeftHand1().getSpecialEffects().getPoisonResistance()
                        * getLeftHand2().getSpecialEffects().getPoisonResistance()
                        * getLeftHand3().getSpecialEffects().getPoisonResistance()
                        * getRightHand1().getSpecialEffects().getPoisonResistance()
                        * getRightHand2().getSpecialEffects().getPoisonResistance()
                        * getRightHand3().getSpecialEffects().getPoisonResistance();
            case SpecialEffect.FROST_RESISTANCE:
                return getHelm().getSpecialEffects().getFrostResistance()
                        * getChest().getSpecialEffects().getFrostResistance()
                        * getGauntlets().getSpecialEffects().getFrostResistance()
                        * getLeggings().getSpecialEffects().getFrostResistance()
                        * getRing1().getSpecialEffects().getFrostResistance()
                        * getRing2().getSpecialEffects().getFrostResistance()
                        * getRing3().getSpecialEffects().getFrostResistance()
                        * getRing4().getSpecialEffects().getFrostResistance()
                        * getLeftHand1().getSpecialEffects().getFrostResistance()
                        * getLeftHand2().getSpecialEffects().getFrostResistance()
                        * getLeftHand3().getSpecialEffects().getFrostResistance()
                        * getRightHand1().getSpecialEffects().getFrostResistance()
                        * getRightHand2().getSpecialEffects().getFrostResistance()
                        * getRightHand3().getSpecialEffects().getFrostResistance();
            case SpecialEffect.CURSE_RESISTANCE:
                return getHelm().getSpecialEffects().getCurseResistance()
                        * getChest().getSpecialEffects().getCurseResistance()
                        * getGauntlets().getSpecialEffects().getCurseResistance()
                        * getLeggings().getSpecialEffects().getCurseResistance()
                        * getRing1().getSpecialEffects().getCurseResistance()
                        * getRing2().getSpecialEffects().getCurseResistance()
                        * getRing3().getSpecialEffects().getCurseResistance()
                        * getRing4().getSpecialEffects().getCurseResistance()
                        * getLeftHand1().getSpecialEffects().getCurseResistance()
                        * getLeftHand2().getSpecialEffects().getCurseResistance()
                        * getLeftHand3().getSpecialEffects().getCurseResistance()
                        * getRightHand1().getSpecialEffects().getCurseResistance()
                        * getRightHand2().getSpecialEffects().getCurseResistance()
                        * getRightHand3().getSpecialEffects().getCurseResistance();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}