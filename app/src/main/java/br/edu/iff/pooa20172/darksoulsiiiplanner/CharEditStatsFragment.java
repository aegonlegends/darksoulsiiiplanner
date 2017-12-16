package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CharEditStatsFragment extends Fragment {
    CharEditFragmentListener fListener;
    boolean updatingViews = false;
    int [] editTextIds = new int[]{R.id.etName, R.id.etVIG, R.id.etATT, R.id.etEND, R.id.etVIT, R.id.etSTR, R.id.etDEX, R.id.etINT, R.id.etFTH, R.id.etLCK},
    spinnerIds = new int[]{R.id.sClass, R.id.sCovenant},
    textViewIds = new int[]{R.id.tvTotalVIG, R.id.tvTotalATT, R.id.tvTotalEND, R.id.tvTotalVIT, R.id.tvTotalSTR, R.id.tvTotalDEX, R.id.tvTotalINT, R.id.tvTotalFTH, R.id.tvTotalLCK};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_char_edit_stats, container, false);

        for (int id : editTextIds) {
            EditText textfield = rootView.findViewById(id);
            textfield.setTransformationMethod(null);
            textfield.setOnFocusChangeListener(new FocusChangedListener());
        }

        for (int id : spinnerIds) {
            Spinner selectbox = rootView.findViewById(id);
            selectbox.setOnItemSelectedListener(new ItemSelectedListener());
        }

        updateData(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView(){
        EditText textfield = getView().findViewById(R.id.etName);

        super.onDestroyView();
    }

    private void updateData(View view) {
        Character c = fListener.getCharacter();

        int[] stats = new int[] {c.getVigor(), c.getAttunement(), c.getEndurance(), c.getVitality(),
                c.getStrength(), c.getDexterity(), c.getIntelligence(), c.getFaith(), c.getLuck()};

        int[] spinnerIndexes = new int[] {
                c.getClasse().getIndex(),
                c.getCovenant()};

        int[] bonusStats = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; // Placeholder until Armor implementation is ready

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

        for (int i = 0; i < textViewIds.length; i++){
            TextView label = view.findViewById(textViewIds[i]);
            String stat = String.format("%d", stats[i] + bonusStats[i]);

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