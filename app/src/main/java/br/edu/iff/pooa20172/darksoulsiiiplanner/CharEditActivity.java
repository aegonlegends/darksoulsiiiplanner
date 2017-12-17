package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class CharEditActivity extends AppCompatActivity implements CharEditFragmentListener {
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private Character character;
    private boolean saveEdits = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_edit);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new CharEditPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tlTabs);
        tabLayout.setupWithViewPager(pager);

        if (savedInstanceState == null) {
            character = new Character("Arty", 2, Classe.PYROMANCER);
        }
        else{
            character = (Character) savedInstanceState.getSerializable("character");
        }

    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
        outState.putSerializable("character", character);
        super.onSaveInstanceState(outState);
    }

    public Character getCharacter(){
        return character;
    }

    public void onEditTextChanged(String idName, String value){
        if(TextUtils.isEmpty(value)){
            value = "0";
        }
        int val;
        if(idName.equals("etName")) {
            character.setName(value);
            return;
        }
        try{
            val = Integer.parseInt(value);
        }
        catch(NumberFormatException e){
            return;
        }
        switch (idName){
            case "etVIG": character.setVigor(val);
                break;
            case "etATT": character.setAttunement(val);
                break;
            case "etEND": character.setEndurance(val);
                break;
            case "etVIT": character.setVitality(val);
                break;
            case "etSTR": character.setStrength(val);
                break;
            case "etDEX": character.setDexterity(val);
                break;
            case "etINT": character.setIntelligence(val);
                break;
            case "etFTH": character.setFaith(val);
                break;
            case "etLCK": character.setLuck(val);
                break;
        }
    }

    public void onSpinnerChanged(String idName, int index){
        if(index < 0){
            return;
        }

        switch(idName){
            case "sClass": character.setClasse(index);
                break;
            case "sCovenant": character.setCovenant(index);
                break;
            case "sWeaponL1": character.setLeftHand1(Database.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponL2": character.setLeftHand2(Database.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponL3": character.setLeftHand3(Database.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponR1": character.setRightHand1(Database.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponR2": character.setRightHand2(Database.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponR3": character.setRightHand3(Database.getWeapon(index, Weapon.REGULAR));
                break;
            case "sHelm" :  character.setHelm(Database.getArmor(index, Armor.TYPE_HELM));
                break;
            case "sChest" :  character.setChest(Database.getArmor(index, Armor.TYPE_CHESTPIECE));
                break;
            case "sGauntlets" :  character.setGauntlets(Database.getArmor(index, Armor.TYPE_GAUNTLETS));
                break;
            case "sLeggings" :  character.setLeggings(Database.getArmor(index, Armor.TYPE_LEGGINGS));
                break;
            case "sRing1" :  character.setRing1(Database.getRing(index));
                break;
            case "sRing2" :  character.setRing2(Database.getRing(index));
                break;
            case "sRing3" :  character.setRing3(Database.getRing(index));
                break;
            case "sRing4" :  character.setRing4(Database.getRing(index));
                break;
        }
    }

    public void onCheckboxChanged(String idName, boolean checked){
        switch (idName){
            case "cbTwoHanded" : character.setTwoHanded(checked);
                break;
        }
    }

    public void clearFocus(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        findViewById(R.id.pager).requestFocus();
    }

    private class CharEditPagerAdapter extends FragmentStatePagerAdapter {
        public CharEditPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CharEditStatsFragment();
                case 1:
                    return new CharEditEquipFragment();
                case 2:
                    return new CharEditOverviewFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Stats";
                case 1:
                    return "Equipment";
                case 2:
                    return "Overview";
            }
            return null;
        }
    }
}
