package br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;
import br.edu.iff.pooa20172.darksoulsiiiplanner.R;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.SpecialEffect;

public class CharEditStatsFragment extends Fragment {
    CharEditFragmentListener fListener;
    boolean updatingViews = false;
    int [] editTextIds = new int[]{R.id.etName, R.id.etVIG, R.id.etATT, R.id.etEND, R.id.etVIT, R.id.etSTR, R.id.etDEX, R.id.etINT, R.id.etFTH, R.id.etLCK},
    spinnerIds = new int[]{R.id.sClass, R.id.sCovenant},
    textViewIds = new int[]{R.id.tvLevel, R.id.tvTotalVIG, R.id.tvTotalATT, R.id.tvTotalEND, R.id.tvTotalVIT, R.id.tvTotalSTR, R.id.tvTotalDEX, R.id.tvTotalINT, R.id.tvTotalFTH, R.id.tvTotalLCK};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_char_edit_stats, container, false);

        FocusChangedListener etListener = new FocusChangedListener();
        ItemSelectedListener sListener = new ItemSelectedListener();

        for (int id : editTextIds) {
            EditText textfield = rootView.findViewById(id);
            textfield.setTransformationMethod(null);
            textfield.setOnFocusChangeListener(etListener);
        }

        for (int id : spinnerIds) {
            Spinner selectbox = rootView.findViewById(id);
            selectbox.setOnItemSelectedListener(sListener);
        }

        updateData(rootView);
        return rootView;
    }

    /*@Override
    public void onDestroyView(){
        super.onDestroyView();
    }*/

    private void updateData(View view) {
        Character c = fListener.getCharacter();

        int[] stats = new int[] {c.getVigor(), c.getAttunement(), c.getEndurance(), c.getVitality(),
                c.getStrength(), c.getDexterity(), c.getIntelligence(), c.getFaith(), c.getLuck()};

        int[] spinnerIndexes = new int[] {
                c.getClasse().getIndex(),
                c.getCovenant()};

        int[] bonusStats = new int[] {c.getBonusStat(SpecialEffect.VIGOR), c.getBonusStat(SpecialEffect.ATTUNEMENT), c.getBonusStat(SpecialEffect.ENDURANCE), c.getBonusStat(SpecialEffect.VITALITY), c.getBonusStat(SpecialEffect.STRENGTH), c.getBonusStat(SpecialEffect.DEXTERITY), c.getBonusStat(SpecialEffect.INTELLIGENCE), c.getBonusStat(SpecialEffect.FAITH), c.getBonusStat(SpecialEffect.LUCK)};

        updatingViews = true;
        for (int i = 0; i < editTextIds.length; i++){
            EditText textfield = view.findViewById(editTextIds[i]);
            String stat;
            if(i==0){
                stat = c.getName();
            }
            else{
                stat = String.format("%d", stats[i-1]);
            }
            if(!(textfield.getText().toString().equals(stat))){ // if current text is different then replace it
                textfield.setText(stat);
            }
        }

        TextView label = view.findViewById(textViewIds[0]);
        label.setText(String.format("Level %d", c.getLevel()));

        for (int i = 0; i < stats.length; i++){
            label = view.findViewById(textViewIds[i+1]);
            String stat;
            if(stats[i] + bonusStats[i] <= 99) {
                stat = String.format("%d", stats[i] + bonusStats[i]);
            }
            else{
                stat = String.format("%d", 99);
            }


            if(!(label.getText().toString().equals(stat))){ // if current text is different then replace it
                label.setText(stat);
            }
        }


        for (int i = 0; i < spinnerIds.length; i++) {
            Spinner spinner = view.findViewById(spinnerIds[i]);
            if(spinner.getSelectedItemPosition() != spinnerIndexes[i]) {
                spinner.setSelection(spinnerIndexes[i]);
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
        if(getView() == null){
           return;
        }
        fListener.clearFocus();
        updateData();
    }
    private class FocusChangedListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                fListener.onEditTextChanged(getResources().getResourceEntryName(v.getId()), ((EditText) v).getText().toString());
                updateData();
            }
        }
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
}