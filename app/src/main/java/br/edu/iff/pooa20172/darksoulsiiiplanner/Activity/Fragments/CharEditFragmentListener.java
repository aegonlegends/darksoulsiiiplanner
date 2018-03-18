package br.edu.iff.pooa20172.darksoulsiiiplanner.Activity.Fragments;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;

public interface CharEditFragmentListener {
    public void onEditTextChanged(String idName, String value);
    public void onSpinnerChanged(String idName, int index);
    public void onCheckboxChanged(String idName, boolean checked);
    public void clearFocus();
    public Character getCharacter();
}
