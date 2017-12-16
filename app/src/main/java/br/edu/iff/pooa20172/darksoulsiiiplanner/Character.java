
package br.edu.iff.pooa20172.darksoulsiiiplanner;

public class Character {
    private String name;
    private Classe classe;
    private int covenant, vigor, attunement, endurance, vitality, strength, dexterity, intelligence, faith, luck;
    private Weapon leftHand1, leftHand2, leftHand3, rightHand1, rightHand2, rightHand3;
    private Armor helm, chest, gauntlets, boots;
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
    }

    public Character(String name, Classe classe, int covenant, int vig, int att, int end, int vit, int str, int dex, int inte, int fth, int luck) {
        this.name = name;
        this.classe = classe;
        this.covenant = covenant;
        this.vigor = vig;
        this.attunement = att;
        this.endurance = end;
        this.vitality = vit;
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = inte;
        this.faith = fth;
        this.luck = luck;
    }

    int calculateHP(int bonusVigor){
        int vigor = this.vigor + bonusVigor;
        if(vigor>99){ vigor = 99;}

        return hpValues[vigor-1];
    }

    int calculateFP(){
        return fpValues[attunement-1];
    }

    int calculateStamina(int bonusEndurance){
        int endurance = this.endurance + bonusEndurance;
        if(endurance>99){ endurance = 99;}

        if(endurance > 40){
            return staminaValues[40] + (endurance-40)/6;
        }
        return staminaValues[endurance];
    }

    float calculateEquipLoad(int bonusVitality){
        int vitality = this.vitality + bonusVitality;
        if(vitality>99){ vitality= 99;}

        return 40 + vitality;
    }

    int calculateItemDisc(){
        return 100 + luck;
    }

    int calculateAttSlots(){
        int i=0, slots=0;
        while(attSlotsBreakpoints[i] <= attunement){
            slots++;
            i++;
        }
        return slots;
    }

    int calculatePhysicalDefenses(){
        int def = 0;
        def += vigor * 0.4;
        def += endurance * 0.4;
        def += attunement * 0.4;
        def += vitality * 1.5;
        def += strength * 0.73;
        def += dexterity * 0.4;
        def += intelligence * 0.4;
        def += faith * 0.4;
        def += luck * 0.4;
        return def;
    }

    int calculateMagicDefense(){
        int def = 0;
        def += vigor * 0.4;
        def += endurance * 0.4;
        def += attunement * 0.4;
        def += vitality * 0.4;
        def += strength * 0.4;
        def += dexterity * 0.4;
        def += intelligence * 1.1;
        def += faith * 0.4;
        def += luck * 0.4;
        return def;
    }

    int calculateFireDefense(){
        int def = 0;
        def += vigor * 0.4;
        def += endurance * 0.4;
        def += attunement * 0.4;
        def += vitality * 0.4;
        def += strength * 1.1;
        def += dexterity * 0.4;
        def += intelligence * 1.1;
        def += faith * 0.4;
        def += luck * 0.4;
        return def;
    }

    int calculateLightningDefense(){
        int def = 0;
        def += vigor * 0.4;
        def += endurance * 1.1;
        def += attunement * 0.4;
        def += vitality * 0.4;
        def += strength * 0.4;
        def += dexterity * 0.4;
        def += intelligence * 0.4;
        def += faith * 0.4;
        def += luck * 0.4;
        return def;
    }

    int calculateDarkDefense(){
        int def = 0;
        def += vigor * 0.4;
        def += endurance * 0.4;
        def += attunement * 0.4;
        def += vitality * 0.4;
        def += strength * 0.4;
        def += dexterity * 0.4;
        def += intelligence * 1.1;
        def += faith * 1.1;
        def += luck * 0.4;
        return def;
    }

    int calculateBleedResistance(){
        int resist=0;

        resist += vigor * 0.2;
        resist += endurance * 1.1;
        resist += attunement * 0.2;
        resist += vitality * 0.2;
        resist += strength * 0.2;
        resist += dexterity * 0.2;
        resist += intelligence * 0.2;
        resist += faith * 0.2;
        resist += luck * 0.2;

        return resist;
    }

    int calculatePoisonResistance(){
        int resist=0;

        resist += vigor * 0.2;
        resist += endurance * 0.2;
        resist += attunement * 0.2;
        resist += vitality * 1.1;
        resist += strength * 0.2;
        resist += dexterity * 0.2;
        resist += intelligence * 0.2;
        resist += faith * 0.2;
        resist += luck * 0.2;

        return resist;
    }

    int calculateFrostResistance(){
        int resist=0;

        resist += vigor * 1.1;
        resist += endurance * 0.2;
        resist += attunement * 0.2;
        resist += vitality * 0.2;
        resist += strength * 0.2;
        resist += dexterity * 0.2;
        resist += intelligence * 0.2;
        resist += faith * 0.2;
        resist += luck * 0.2;

        return resist;
    }

    int calculateCurseResistance(){
        int resist=0;

        resist += vigor * 0.2;
        resist += endurance * 0.2;
        resist += attunement * 0.2;
        resist += vitality * 0.2;
        resist += strength * 0.2;
        resist += dexterity * 0.2;
        resist += intelligence * 0.2;
        resist += faith * 0.2;
        resist += luck * 1.1;

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
        if (helm.getType() != Armor.TYPE_HELM) {
            throw new IllegalArgumentException();
        }
        this.helm = helm;
    }

    public Armor getChest() {
        return chest;
    }

    public void setChest(Armor chest) {
        if (chest.getType() != Armor.TYPE_CHESTPIECE) {
            throw new IllegalArgumentException();
        }
        this.chest = chest;
    }

    public Armor getGauntlets() {
        return gauntlets;
    }

    public void setGauntlets(Armor gauntlets) {
        if (gauntlets.getType() != Armor.TYPE_GAUNTLETS) {
            throw new IllegalArgumentException();
        }
        this.gauntlets = gauntlets;
    }

    public Armor getBoots() {
        return boots;
    }

    public void setBoots(Armor boots) {
        if (boots.getType() != Armor.TYPE_BOOTS) {
            throw new IllegalArgumentException();
        }
        this.boots = boots;
    }
}