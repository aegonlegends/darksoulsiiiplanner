package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CharSelectActivity extends AppCompatActivity {

    private ArrayList<Character> chars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CStoolbar);
        toolbar.setBackgroundColor(Color.GRAY);
        this.setSupportActionBar(toolbar);

        ListView lista = (ListView) findViewById(R.id.lvCharacters);
        chars = populateList();
        ArrayAdapter listAdapter = new CharacterAdapter(this, chars);
        lista.setAdapter(listAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_char_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.btNew) {
            Intent intent = new Intent(CharSelectActivity.this, CharEditActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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
