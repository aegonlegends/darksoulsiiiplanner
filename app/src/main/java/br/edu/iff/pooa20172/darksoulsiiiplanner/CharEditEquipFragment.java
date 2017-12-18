package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class CharEditEquipFragment extends Fragment {

    CharEditFragmentListener fListener;
    boolean updatingViews;

    int[] spinnerIds = new int[] {R.id.sHelm, R.id.sChest, R.id.sGauntlets, R.id.sLeggings, R.id.sRing1, R.id.sRing2, R.id.sRing3, R.id.sRing4,
            R.id.sWeaponL1, R.id.sWeaponR1, R.id.sWeaponL2, R.id.sWeaponR2, R.id.sWeaponL3, R.id.sWeaponR3},

    textViewIds = new int[] {R.id.topbar_tvEquipLoad, R.id.topbar_tvEquipLoadPercent, R.id.tvWeaponL1, R.id.tvWeaponR1, R.id.tvWeaponL2, R.id.tvWeaponR2, R.id.tvWeaponL3, R.id.tvWeaponR3};

    int[] attunementSpinners = new int[] {R.id.sSpell1, R.id.sSpell2, R.id.sSpell3, R.id.sSpell4, R.id.sSpell5, R.id.sSpell6, R.id.sSpell7,
            R.id.sSpell8, R.id.sSpell9, R.id.sSpell10, R.id.sSpell11, R.id.sSpell12, R.id.sSpell13, R.id.sSpell14};
    int visibleAttunementSpinners = 0;

    int cbTwoHandedId = R.id.cbTwoHanded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updatingViews = true;

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_char_edit_equip, container, false);

        WeaponStatsClickListener clickListener = new WeaponStatsClickListener();
        for (int id : textViewIds) {
            TextView label = rootView.findViewById(id);
            label.setOnClickListener(clickListener);
        }

        ItemSelectedListener sListener = new ItemSelectedListener();

        for (int id : spinnerIds) {
            Spinner selectbox = rootView.findViewById(id);
            selectbox.setOnItemSelectedListener(sListener);
        }

        sListener = new SpellSelectedListener();

        for (int id : attunementSpinners) {
            Spinner selectbox = rootView.findViewById(id);
            selectbox.setOnItemSelectedListener(sListener);
        }

        ((CheckBox)rootView.findViewById(R.id.cbTwoHanded)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!updatingViews) {
                    fListener.onCheckboxChanged(getResources().getResourceEntryName(R.id.cbTwoHanded), ((CheckBox) v).isChecked());
                    updateData();
                }
            }
        });

        return rootView;
    }

    private void updateData(View view){
        Character c = fListener.getCharacter();
        int[] indexes = new int[] {c.getHelm().getIndex(), c.getChest().getIndex(), c.getGauntlets().getIndex(), c.getLeggings().getIndex(),
                c.getRing1().getIndex(), c.getRing2().getIndex(), c.getRing3().getIndex(), c.getRing4().getIndex(),
                c.getLeftHand1().getIndex(), c.getRightHand1().getIndex(), c.getLeftHand2().getIndex(), c.getRightHand2().getIndex(), c.getLeftHand3().getIndex(), c.getRightHand3().getIndex(),
        };

        int i;

        updatingViews = true;
        for(i = 0; i < spinnerIds.length; i++){
            Spinner spinner = view.findViewById(spinnerIds[i]);
            if(spinner.getSelectedItemPosition() != indexes[i]) {
                spinner.setSelection(indexes[i]);
            }
        }

        TextView label = view.findViewById(textViewIds[0]);
        double weight = c.getHelm().getWeight() + c.getChest().getWeight() + c.getGauntlets().getWeight() + c.getLeggings().getWeight()
                + c.getRightHand1().getWeight()+ c.getLeftHand1().getWeight()
                + c.getRightHand2().getWeight()+ c.getLeftHand2().getWeight()
                + c.getRightHand3().getWeight() + c.getLeftHand3().getWeight()
                + c.getRing1().getWeight() + c.getRing2().getWeight() + c.getRing3().getWeight() + c.getRing4().getWeight();

        double bonusEquipLoad = c.getBonusMultipliers(SpecialEffect.EQUIPMENT_LOAD_MULTIPLIER);

        label.setText(String.format(Locale.US,"%.1f/%.1f", weight, c.calculateEquipLoad() * bonusEquipLoad));

        weight = weight/(c.calculateEquipLoad() * bonusEquipLoad);

        label = view.findViewById(textViewIds[1]);
        label.setText(String.format(Locale.US,"%.1f%%", weight*100));
        if(weight*100 < 70){
            label.setTextColor(ContextCompat.getColor(getContext(), R.color.correctGreen));
        }
        else{
            label.setTextColor(ContextCompat.getColor(getContext(), R.color.overcumberedRed));
        }

        for(i = 2; i < textViewIds.length; i++){
            label = view.findViewById(textViewIds[i]);
            Weapon wp;
            switch(textViewIds[i]){
                case R.id.tvWeaponL1: wp = c.getLeftHand1();
                    label.setText("Left 1 ");
                    break;
                case R.id.tvWeaponL2: wp = c.getLeftHand2();
                    label.setText("Left 2 ");
                    break;
                case R.id.tvWeaponL3: wp = c.getLeftHand3();
                    label.setText("Left 3 ");
                    break;
                case R.id.tvWeaponR1: wp = c.getRightHand1();
                    label.setText("Right 1 ");
                    break;
                case R.id.tvWeaponR2: wp = c.getRightHand2();
                    label.setText("Right 2 ");
                    break;
                case R.id.tvWeaponR3: wp = c.getRightHand3();
                    label.setText("Right 3 ");
                    break;
                default:
                    continue;
            }
            if(wp.index == 0){
                continue;
            }

            if(wp.getStrengthRequirement() > c.getStrength() + c.getBonusStat(SpecialEffect.STRENGTH) ||
                    wp.getDexterityRequirement() > c.getDexterity() + c.getBonusStat(SpecialEffect.DEXTERITY) ||
                    wp.getIntelligenceRequirement() > c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE) ||
                    wp.getFaithRequirement() > c.getFaith() + c.getBonusStat(SpecialEffect.FAITH)){
                label.setText(Html.fromHtml(String.format("%s <font color='red'> %d/%d/%d/%d</font>", label.getText().toString(), wp.getStrengthRequirement(),wp.getDexterityRequirement(),wp.getIntelligenceRequirement(),wp.getFaithRequirement())));
                continue;
            }
            if(wp.isCatalyst()){
                label.setText(label.getText().toString() + " - Catalyst");
                continue;
            }

            if(i%2 == 0){ // Left Hand, shield stats
                label.setText(Html.fromHtml(String.format(Locale.US,"%s (Stability: %d)",
                        label.getText().toString(), wp.getStability())));
            }
            else{
                double phys = wp.getPhysicalBaseAttack() + wp.calculateBonusPhysicalAttack(c.getStrength() + c.getBonusStat(SpecialEffect.STRENGTH), c.getDexterity() + c.getBonusStat(SpecialEffect.DEXTERITY), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH), c.getLuck() + c.getBonusStat(SpecialEffect.LUCK));
                double magic = wp.getMagicBaseAttack() + wp.calculateBonusMagicAttack(c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));
                double fire = wp.getFireBaseAttack() + wp.calculateBonusFireAttack(c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));
                double lightning = wp.getLightningBaseAttack() + wp.calculateBonusLightningAttack(c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));
                double dark = wp.getDarkBaseAttack() + wp.calculateBonusDarkAttack(c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));

                label.setText(Html.fromHtml(String.format(Locale.US,"%s (AR: %.1f)",
                        label.getText().toString(), phys+magic+fire+lightning+dark ) ));
            }
        }

        CheckBox check = view.findViewById(cbTwoHandedId);
        if(check.isChecked() != c.isTwoHanded()){
            check.setChecked(c.isTwoHanded());
        }

        int bonusAttSlots = c.getHelm().getSpecialEffects().getAttunementSlot()
                * c.getChest().getSpecialEffects().getAttunementSlot()
                * c.getGauntlets().getSpecialEffects().getAttunementSlot()
                * c.getLeggings().getSpecialEffects().getAttunementSlot()
                * c.getRing1().getSpecialEffects().getAttunementSlot()
                * c.getRing2().getSpecialEffects().getAttunementSlot()
                * c.getRing3().getSpecialEffects().getAttunementSlot()
                * c.getRing4().getSpecialEffects().getAttunementSlot()
                * c.getLeftHand1().getSpecialEffects().getAttunementSlot()
                * c.getLeftHand2().getSpecialEffects().getAttunementSlot()
                * c.getLeftHand3().getSpecialEffects().getAttunementSlot()
                * c.getRightHand1().getSpecialEffects().getAttunementSlot()
                * c.getRightHand2().getSpecialEffects().getAttunementSlot()
                * c.getRightHand3().getSpecialEffects().getAttunementSlot();

        if(visibleAttunementSpinners != c.calculateAttSlots() + bonusAttSlots){
            visibleAttunementSpinners = c.calculateAttSlots() + bonusAttSlots;
            for(i=0; i < c.getSpells().length; i++){
                Spinner sSpell = view.findViewById(attunementSpinners[i]);
                if(i < visibleAttunementSpinners){
                    sSpell.setVisibility(View.VISIBLE);
                    if(sSpell.getSelectedItemPosition() != c.getSpells()[i]){
                        sSpell.setSelection(c.getSpells()[i]);
                    }
                }
                else{
                    sSpell.setVisibility(View.GONE);
                    sSpell.setSelection(0);
                    c.getSpells()[i] = 0;
                }
            }
        }

        updatingViews = false;
    }

    private void updateData(){
        updateData(getView());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fListener = (CharEditFragmentListener) context;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser == false || getView() == null){
            return;
        }
        updateData();
    }
    private class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (updatingViews) {
                return;
            }
            fListener.onSpinnerChanged(getResources().getResourceEntryName(parent.getId()), position);
            updateData();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class SpellSelectedListener extends ItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (updatingViews) {
                return;
            }
            int[] spells = fListener.getCharacter().getSpells();
            int i;
            boolean found = false;
            for(i=0; i < attunementSpinners.length; i++){
                if(attunementSpinners[i] == id){
                    found = true;
                    break;
                }
            }
            if(!found){
                return;
            }
            spells[i] = position;
            updateData();
        }
    }

    private class WeaponStatsClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView label = getView().findViewById(v.getId());
            Character c = fListener.getCharacter();
            Weapon wp;
            boolean left = false;

            switch(label.getId()){
                case R.id.tvWeaponL1: wp = c.getLeftHand1();
                    left = true;
                    break;
                case R.id.tvWeaponL2: wp = c.getLeftHand2();
                    left = true;
                    break;
                case R.id.tvWeaponL3: wp = c.getLeftHand3();
                    left = true;
                    break;
                case R.id.tvWeaponR1: wp = c.getRightHand1();
                    break;
                case R.id.tvWeaponR2: wp = c.getRightHand2();
                    break;
                case R.id.tvWeaponR3: wp = c.getRightHand3();
                    break;
                default:
                    return;
            }
            if(wp.index == 0 || wp.isCatalyst()){
                return;
            }

            if(wp.getStrengthRequirement() > c.getStrength() + c.getBonusStat(SpecialEffect.STRENGTH) ||
                    wp.getDexterityRequirement() > c.getDexterity() + c.getBonusStat(SpecialEffect.DEXTERITY) ||
                    wp.getIntelligenceRequirement() > c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE) ||
                    wp.getFaithRequirement() > c.getFaith() + c.getBonusStat(SpecialEffect.FAITH)){
                Toast.makeText(getContext(),
                        String.format("Minimum Stats: %d Strength / %d Dexterity / %d Intelligence / %d Faith",
                                wp.getStrengthRequirement(),wp.getDexterityRequirement(),wp.getIntelligenceRequirement(),wp.getFaithRequirement()),
                        Toast.LENGTH_LONG).show();
                return;
            }

            if(left){ // Left Hand, shield stats
                Toast.makeText(getContext(),
                        String.format(Locale.US,"Stability: %d - Defense: (Physical: %.1f / Magic: %.1f / Fire: %.1f / Lightning: %.1f / Dark: %.1f)",
                                wp.getStability(), wp.getPhysicalBlock(), wp.getMagicBlock(), wp.getFireBlock(), wp.getLightningBlock(), wp.getDarkBlock() ),
                        Toast.LENGTH_LONG).show();
            }
            else{
                double phys = wp.getPhysicalBaseAttack() + wp.calculateBonusPhysicalAttack(c.getStrength() + c.getBonusStat(SpecialEffect.STRENGTH), c.getDexterity() + c.getBonusStat(SpecialEffect.DEXTERITY), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH), c.getLuck() + c.getBonusStat(SpecialEffect.LUCK));
                double magic = wp.getMagicBaseAttack() + wp.calculateBonusMagicAttack(c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));
                double fire = wp.getFireBaseAttack() + wp.calculateBonusFireAttack(c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));
                double lightning = wp.getLightningBaseAttack() + wp.calculateBonusLightningAttack(c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));
                double dark = wp.getDarkBaseAttack() + wp.calculateBonusDarkAttack(c.getIntelligence() + c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getFaith() + c.getBonusStat(SpecialEffect.FAITH));

                Toast.makeText(getContext(),
                        String.format(Locale.US,"Attack: %.1f (Physical: %.1f / Magic: %.1f / Fire: %.1f / Lightning: %.1f / Dark: %.1f) - Bleed: %d / Poison %d / Frost %d",
                                phys+magic+fire+lightning+dark, phys, magic, fire, lightning, dark, wp.getBleedBuildup(), wp.getPoisonBuildup(), wp.getFrostBuildup() ),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}