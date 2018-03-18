package br.edu.iff.pooa20172.darksoulsiiiplanner.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments.CharEditEquipFragment;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments.CharEditFragmentListener;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments.CharEditOverviewFragment;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments.CharEditStatsFragment;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Armor;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Persistence.CharacterDB;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.EquipmentData;
import br.edu.iff.pooa20172.darksoulsiiiplanner.R;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Weapon;
import io.realm.Realm;

public class CharEditActivity extends AppCompatActivity implements CharEditFragmentListener {
    private ViewPager pager;
    private Realm realm;
    private PagerAdapter pagerAdapter;
    private Character character;
    int charIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_edit);

        realm = Realm.getDefaultInstance();

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new CharEditPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tlTabs);
        tabLayout.setupWithViewPager(pager);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            character = (Character) bundle.getSerializable("character");
            charIndex = bundle.getInt("charIndex");
        }
        else{
            character = (Character) savedInstanceState.getSerializable("character");
            charIndex = savedInstanceState.getInt("charIndex");
        }

    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
        outState.putSerializable("character", character);
        outState.putInt("charIndex", charIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        clearFocus();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Editor");
        builder.setMessage("Do you wish to save your changes?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent intent = new Intent();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("character", character);
                        bundle.putInt("charIndex", charIndex);

                        salvarDB();

                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        finish();
                    }
                });
        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        builder.create().show();
    }

    private void salvarDB(){
        if(character.getId() != -1){
            editarDB();
            return;
        }

        int proximoID = 1;
        if(realm.where(CharacterDB.class).max("id") !=null)
            proximoID = realm.where(CharacterDB.class).max("id").intValue()+1;

        character.setId(proximoID);
        realm.beginTransaction();

        CharacterDB chara = new CharacterDB(character);

        realm.copyToRealm(chara);
        realm.commitTransaction();
        realm.close();
    }

    private void editarDB(){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(new CharacterDB(character));
        realm.commitTransaction();
        realm.close();
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
            case "sWeaponL1": character.setLeftHand1(EquipmentData.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponL2": character.setLeftHand2(EquipmentData.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponL3": character.setLeftHand3(EquipmentData.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponR1": character.setRightHand1(EquipmentData.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponR2": character.setRightHand2(EquipmentData.getWeapon(index, Weapon.REGULAR));
                break;
            case "sWeaponR3": character.setRightHand3(EquipmentData.getWeapon(index, Weapon.REGULAR));
                break;
            case "sHelm" :  character.setHelm(EquipmentData.getArmor(index, Armor.TYPE_HELM));
                break;
            case "sChest" :  character.setChest(EquipmentData.getArmor(index, Armor.TYPE_CHESTPIECE));
                break;
            case "sGauntlets" :  character.setGauntlets(EquipmentData.getArmor(index, Armor.TYPE_GAUNTLETS));
                break;
            case "sLeggings" :  character.setLeggings(EquipmentData.getArmor(index, Armor.TYPE_LEGGINGS));
                break;
            case "sRing1" :  character.setRing1(EquipmentData.getRing(index));
                break;
            case "sRing2" :  character.setRing2(EquipmentData.getRing(index));
                break;
            case "sRing3" :  character.setRing3(EquipmentData.getRing(index));
                break;
            case "sRing4" :  character.setRing4(EquipmentData.getRing(index));
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
        try{
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            findViewById(R.id.pager).requestFocus();
        }
        catch (NullPointerException e){
        }
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
