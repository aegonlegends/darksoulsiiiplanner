package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class CharSelectActivity extends AppCompatActivity {

    private ArrayList<Character> chars;
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CStoolbar);
        this.setSupportActionBar(toolbar);

        ListView lista = (ListView) findViewById(R.id.lvCharacters);
        chars = populateList();
        listAdapter = new CharacterAdapter(this, chars);
        lista.setAdapter(listAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                callEditChar(i);
            }
        });
    }

    private void callEditChar(int i){
        Intent intent = new Intent(CharSelectActivity.this, CharEditActivity.class);
        Bundle bundle = new Bundle();
        if(i>=0) {
            bundle.putSerializable("character", chars.get(i));
        }
        else{
            bundle.putSerializable("character", new Character("", 0, Classe.KNIGHT));
        }
        bundle.putInt("charIndex", i);

        intent.putExtras(bundle);

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            Bundle data = intent.getExtras();
            Character chara = (Character) data.getSerializable("character");
            int i = data.getInt("charIndex");
            if(i >= 0){
                chars.set(i, chara);
            }
            else{
                chars.add(chara);
            }

            listAdapter.notifyDataSetChanged();
        }
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
            callEditChar(-1);
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Character> populateList() {
        chars = new ArrayList<Character>();
        chars.add(new Character("Aegon Dalenthar", 2, Classe.KNIGHT));
        chars.add(new Character("Izabella Dalenthar", 0, Classe.MERCENARY));
        chars.add(new Character("Cecile Erathell", 5, Classe.CLERIC));
        chars.add(new Character("Claire Erathell", 6, Classe.SORCERER));
        return chars;
    }
}
