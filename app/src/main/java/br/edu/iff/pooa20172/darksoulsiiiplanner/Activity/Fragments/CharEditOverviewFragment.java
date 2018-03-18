package br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;
import br.edu.iff.pooa20172.darksoulsiiiplanner.R;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.SpecialEffect;

public class CharEditOverviewFragment extends Fragment {

    CharEditFragmentListener fListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_char_edit_overview, container, false);
        updateData(rootView);

        return rootView;
    }

    private void updateData(View view){
        Character c = fListener.getCharacter();
        TextView label;

        double bonus = c.getBonusMultipliers(SpecialEffect.HP_MULTIPLIER);
        label = view.findViewById(R.id.tvHP);
        label.setText(String.format("%.0f (%.0f)", c.calculateHP()*bonus, Math.floor(c.calculateHP()*bonus*1.30)));

        bonus = c.getBonusMultipliers(SpecialEffect.STAMINA_MULTIPLIER);
        label = view.findViewById(R.id.tvStamina);
        label.setText(String.format("%.0f", c.calculateStamina()*bonus));

        label = view.findViewById(R.id.tvFP);
        label.setText(String.format("%d", c.calculateFP()));

        bonus = c.getBonusMultipliers(SpecialEffect.EQUIPMENT_LOAD_MULTIPLIER);
        double data = c.getHelm().getWeight() + c.getChest().getWeight() + c.getGauntlets().getWeight() + c.getLeggings().getWeight()
                + c.getRightHand1().getWeight()+ c.getLeftHand1().getWeight()
                + c.getRightHand2().getWeight()+ c.getLeftHand2().getWeight()
                + c.getRightHand3().getWeight() + c.getLeftHand3().getWeight()
                + c.getRing1().getWeight() + c.getRing2().getWeight() + c.getRing3().getWeight() + c.getRing4().getWeight();
        label = view.findViewById(R.id.tvEquipLoad);
        label.setText(String.format(Locale.US,"%.1f/%.1f", data, c.calculateEquipLoad()*bonus));

        bonus = c.getBonusStat(SpecialEffect.POISE);
        data = c.getHelm().getPoise() + c.getChest().getPoise() + c.getGauntlets().getPoise() + c.getLeggings().getPoise();
        label = view.findViewById(R.id.tvPoise);
        label.setText(String.format(Locale.US,"%.1f", data+bonus));

        bonus = bonus = c.getBonusStat(SpecialEffect.ITEM_DISCOVERY);
        label = view.findViewById(R.id.tvItemDisc);
        label.setText(String.format("%.0f", c.calculateItemDisc() + bonus));

        bonus = bonus = c.getBonusStat(SpecialEffect.ATTUNEMENT_SLOTS);
        label = view.findViewById(R.id.tvAttSlots);
        label.setText(String.format("%.0f", c.calculateAttSlots() + bonus));

        bonus = 1;
        if(c.getHelm().getIndex() != 0){bonus*=1.1;}
        if(c.getChest().getIndex() != 0){bonus*=1.2;}
        if(c.getGauntlets().getIndex() != 0){bonus*=1.06;}
        if(c.getLeggings().getIndex() != 0){bonus*=1.14;}

        String text = String.format("%.0f", c.calculatePhysicalDefenses() * bonus);
        label = view.findViewById(R.id.tvPhysDef);
        label.setText(text);
        label = view.findViewById(R.id.tvStrikeDef);
        label.setText(text);
        label = view.findViewById(R.id.tvSlashDef);
        label.setText(text);
        label = view.findViewById(R.id.tvThrustDef);
        label.setText(text);

        label = view.findViewById(R.id.tvMagicDef);
        label.setText(String.format("%.0f", c.calculateMagicDefense() * bonus));
        label = view.findViewById(R.id.tvFireDef);
        label.setText(String.format("%.0f", c.calculateFireDefense() * bonus));
        label = view.findViewById(R.id.tvLightDef);
        label.setText(String.format("%.0f", c.calculateLightningDefense() * bonus));
        label = view.findViewById(R.id.tvDarkDef);
        label.setText(String.format("%.0f", c.calculateDarkDefense() * bonus));


        label = view.findViewById(R.id.tvPhysAbs);
        data = 1 * (1 - c.getHelm().getPhysicalAbsorption() / 100) * (1 - c.getChest().getPhysicalAbsorption() / 100) * (1 - c.getGauntlets().getPhysicalAbsorption() / 100) * (1 - c.getLeggings().getPhysicalAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.PHYSICAL_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));

        label = view.findViewById(R.id.tvStrikeAbs);
        data = 1 * (1 - c.getHelm().getStrikeAbsorption() / 100) * (1 - c.getChest().getStrikeAbsorption() / 100) * (1 - c.getGauntlets().getStrikeAbsorption() / 100) * (1 - c.getLeggings().getStrikeAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.STRIKE_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));

        label = view.findViewById(R.id.tvSlashAbs);
        data = 1 * (1 - c.getHelm().getSlashAbsorption() / 100) * (1 - c.getChest().getSlashAbsorption() / 100) * (1 - c.getGauntlets().getSlashAbsorption() / 100) * (1 - c.getLeggings().getSlashAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.SLASH_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));

        label = view.findViewById(R.id.tvThrustAbs);
        data = 1 * (1 - c.getHelm().getThrustAbsorption() / 100) * (1 - c.getChest().getThrustAbsorption() / 100) * (1 - c.getGauntlets().getThrustAbsorption() / 100) * (1 - c.getLeggings().getThrustAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.THRUST_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));

        label = view.findViewById(R.id.tvMagicAbs);
        data = 1 * (1 - c.getHelm().getMagicAbsorption() / 100) * (1 - c.getChest().getMagicAbsorption() / 100) * (1 - c.getGauntlets().getMagicAbsorption() / 100) * (1 - c.getLeggings().getMagicAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.MAGIC_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));


        label = view.findViewById(R.id.tvFireAbs);
        data = 1 * (1 - c.getHelm().getFireAbsorption() / 100) * (1 - c.getChest().getFireAbsorption() / 100) * (1 - c.getGauntlets().getFireAbsorption() / 100) * (1 - c.getLeggings().getFireAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.FIRE_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));


        label = view.findViewById(R.id.tvLightAbs);
        data = 1 * (1 - c.getHelm().getLightningAbsorption() / 100) * (1 - c.getChest().getLightningAbsorption() / 100) * (1 - c.getGauntlets().getLightningAbsorption() / 100) * (1 - c.getLeggings().getLightningAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.LIGHTNING_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));


        label = view.findViewById(R.id.tvDarkAbs);
        data = 1 * (1 - c.getHelm().getDarkAbsorption() / 100) * (1 - c.getChest().getDarkAbsorption() / 100) * (1 - c.getGauntlets().getDarkAbsorption() / 100) * (1 - c.getLeggings().getDarkAbsorption() / 100);
        bonus = c.getBonusMultipliers(SpecialEffect.DARK_ABSORPTION);
        label.setText(String.format(Locale.US, "%.3f%%", 100 - data * bonus*100));

        label = view.findViewById(R.id.tvBleedRes);
        data = c.calculateBleedResistance() + c.getHelm().getBleedResistance() + c.getChest().getBleedResistance() + c.getGauntlets().getBleedResistance() + c.getLeggings().getBleedResistance();
        bonus = c.getBonusMultipliers(SpecialEffect.BLEED_RESISTANCE);
        label.setText(String.format("%.0f", data + bonus));

        label = view.findViewById(R.id.tvPoisonRes);
        data = c.calculatePoisonResistance() + c.getHelm().getPoisonResistance() + c.getChest().getPoisonResistance() + c.getGauntlets().getPoisonResistance() + c.getLeggings().getPoisonResistance();
        bonus = c.getBonusMultipliers(SpecialEffect.POISON_RESISTANCE);
        label.setText(String.format("%.0f", data + bonus));

        label = view.findViewById(R.id.tvFrostRes);
        data = c.calculateFrostResistance() + c.getHelm().getFrostResistance() + c.getChest().getFrostResistance() + c.getGauntlets().getFrostResistance() + c.getLeggings().getFrostResistance();
        bonus = c.getBonusMultipliers(SpecialEffect.FROST_RESISTANCE);
        label.setText(String.format("%.0f", data + bonus));

        label = view.findViewById(R.id.tvCurseRes);
        data = c.calculateCurseResistance() + c.getHelm().getCurseResistance() + c.getChest().getCurseResistance() + c.getGauntlets().getCurseResistance() + c.getLeggings().getCurseResistance();
        bonus = c.getBonusMultipliers(SpecialEffect.CURSE_RESISTANCE);
        label.setText(String.format("%.0f", data + bonus));

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
}