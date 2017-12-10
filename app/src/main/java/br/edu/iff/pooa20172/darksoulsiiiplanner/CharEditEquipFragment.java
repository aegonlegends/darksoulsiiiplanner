package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CharEditEquipFragment extends Fragment {

    ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_char_edit_equip, container, false);

        updateMaxUpgrade(R.id.sWeaponL1lvl, 10);
        updateMaxUpgrade(R.id.sWeaponR1lvl, 10);
        updateMaxUpgrade(R.id.sWeaponL2lvl, 10);
        updateMaxUpgrade(R.id.sWeaponR2lvl, 10);
        updateMaxUpgrade(R.id.sWeaponL3lvl, 10);
        updateMaxUpgrade(R.id.sWeaponR3lvl, 10);

        return rootView;
    }

    private void updateMaxUpgrade(int id, int maxUpgrade){
        Spinner upgradeSpinner = rootView.findViewById(id);
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) upgradeSpinner.getAdapter();
        if(adapter == null){
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
            upgradeSpinner.setAdapter(adapter);
        }
        adapter.clear();
        int i;
        for(i=0; i <= maxUpgrade; i++){
            adapter.add("+" + i);
        }

    }
}