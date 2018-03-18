package br.edu.iff.pooa20172.darksoulsiiiplanner.Persistence;

import java.io.Serializable;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Wesley Gomes on 18/03/2018.
 */

public class CharacterDB extends RealmObject implements Serializable {
    @PrimaryKey
    private int id;
    private String name;
    private int classe,
                covenant, vigor, attunement, endurance, vitality, strength, dexterity, intelligence, faith, luck,
                leftHand1, leftHand2, leftHand3, rightHand1, rightHand2, rightHand3,
                helm, chest, gauntlets, leggings,
                ring1, ring2, ring3, ring4;
                private boolean twoHanded;
                private RealmList<Integer> spells;

    public CharacterDB() {
    }

    public CharacterDB(Character chara) {
        this.id = chara.getId();
        this.name = chara.getName();
        this.classe = chara.getClasse().getIndex();
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
        this.leftHand1 = chara.getLeftHand1().getIndex();
        this.leftHand2 = chara.getLeftHand2().getIndex();
        this.leftHand3 = chara.getLeftHand3().getIndex();
        this.rightHand1 = chara.getRightHand1().getIndex();
        this.rightHand2 = chara.getRightHand2().getIndex();
        this.rightHand3 = chara.getRightHand3().getIndex();
        this.helm = chara.getHelm().getIndex();
        this.chest = chara.getChest().getIndex();
        this.gauntlets = chara.getGauntlets().getIndex();
        this.leggings = chara.getLeggings().getIndex();
        this.ring1 = chara.getRing1().getIndex();
        this.ring2 = chara.getRing2().getIndex();
        this.ring3 = chara.getRing3().getIndex();
        this.ring4 = chara.getRing4().getIndex();
        this.twoHanded = chara.isTwoHanded();
        setSpells(chara.getSpells());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public int getCovenant() {
        return covenant;
    }

    public void setCovenant(int covenant) {
        this.covenant = covenant;
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

    public int getLeftHand1() {
        return leftHand1;
    }

    public void setLeftHand1(int leftHand1) {
        this.leftHand1 = leftHand1;
    }

    public int getLeftHand2() {
        return leftHand2;
    }

    public void setLeftHand2(int leftHand2) {
        this.leftHand2 = leftHand2;
    }

    public int getLeftHand3() {
        return leftHand3;
    }

    public void setLeftHand3(int leftHand3) {
        this.leftHand3 = leftHand3;
    }

    public int getRightHand1() {
        return rightHand1;
    }

    public void setRightHand1(int rightHand1) {
        this.rightHand1 = rightHand1;
    }

    public int getRightHand2() {
        return rightHand2;
    }

    public void setRightHand2(int rightHand2) {
        this.rightHand2 = rightHand2;
    }

    public int getRightHand3() {
        return rightHand3;
    }

    public void setRightHand3(int rightHand3) {
        this.rightHand3 = rightHand3;
    }

    public int getHelm() {
        return helm;
    }

    public void setHelm(int helm) {
        this.helm = helm;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getGauntlets() {
        return gauntlets;
    }

    public void setGauntlets(int gauntlets) {
        this.gauntlets = gauntlets;
    }

    public int getLeggings() {
        return leggings;
    }

    public void setLeggings(int leggings) {
        this.leggings = leggings;
    }

    public int getRing1() {
        return ring1;
    }

    public void setRing1(int ring1) {
        this.ring1 = ring1;
    }

    public int getRing2() {
        return ring2;
    }

    public void setRing2(int ring2) {
        this.ring2 = ring2;
    }

    public int getRing3() {
        return ring3;
    }

    public void setRing3(int ring3) {
        this.ring3 = ring3;
    }

    public int getRing4() {
        return ring4;
    }

    public void setRing4(int ring4) {
        this.ring4 = ring4;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    public RealmList<Integer> getSpells() {
        return spells;
    }

    public void setSpells(int[] spells) {
        this.spells = new RealmList<Integer>() {};
        for(int spell : spells){
            this.spells.add(spell);
        }
    }
}
