package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CharSelectActivity extends AppCompatActivity {

    private ArrayList<Character> chars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.DKGRAY);
        this.setSupportActionBar(toolbar);

        ListView lista = (ListView) findViewById(R.id.lvCharacters);
        chars = populateList();
        ArrayAdapter listAdapter = new CharacterAdapter(this, chars);
        lista.setAdapter(listAdapter);

    }

    private ArrayList<Character> populateList() {
        chars = new ArrayList<Character>();
        chars.add(new Character("Aegon Dalenthar", 80, 2));
        chars.add(new Character("Izabella Dalenthar", 78, 0));
        chars.add(new Character("Cecile Erathell", 120, 5));
        chars.add(new Character("Claire Erathell", 8, 6));
        return chars;
    }
}
