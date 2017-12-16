package br.edu.iff.pooa20172.darksoulsiiiplanner;

public interface CharEditFragmentListener {
    public void onEditTextChanged(String idName, String value);
    public void onSpinnerChanged(String idName, int index);
    public Character getCharacter();
}
