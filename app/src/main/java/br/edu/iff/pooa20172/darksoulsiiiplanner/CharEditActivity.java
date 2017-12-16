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
        pager.setOffscreenPageLimit(0);
        pagerAdapter = new CharEditPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tlTabs);
        tabLayout.setupWithViewPager(pager);

        character = new Character("Arty", 2, Classe.PYROMANCER);

    }

    public Character getCharacter(){
        return character;
    }

    public void onEditTextChanged(String idName, String value){
        if(TextUtils.isEmpty(value)){
            value = "0";
        }

        switch (idName){
            case "etName" : character.setName(value);
                break;
            case "etVIG": character.setVigor(Integer.parseInt(value));
                break;
            case "etATT": character.setAttunement(Integer.parseInt(value));
                break;
            case "etEND": character.setEndurance(Integer.parseInt(value));
                break;
            case "etVIT": character.setVitality(Integer.parseInt(value));
                break;
            case "etSTR": character.setStrength(Integer.parseInt(value));
                break;
            case "etDEX": character.setDexterity(Integer.parseInt(value));
                break;
            case "etINT": character.setIntelligence(Integer.parseInt(value));
                break;
            case "etFTH": character.setFaith(Integer.parseInt(value));
                break;
            case "etLCK": character.setLuck(Integer.parseInt(value));
                break;
        }
    }

    public void onSpinnerChanged(String idName, int index){
        if(index < 0){
            return;
        }

        switch(idName){
            case "sClasse": character.setClasse(index);
            //    break;
            case "sCovenant": character.setCovenant(index);
                break;
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
