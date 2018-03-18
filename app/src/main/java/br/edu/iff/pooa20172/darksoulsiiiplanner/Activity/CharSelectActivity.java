package br.edu.iff.pooa20172.darksoulsiiiplanner.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Adapter.CharacterAdapter;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Persistence.CharacterDB;
import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Classe;
import br.edu.iff.pooa20172.darksoulsiiiplanner.R;
import io.realm.Realm;

public class CharSelectActivity extends AppCompatActivity {

    private ArrayList<Character> chars;
    private Realm realm;
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CStoolbar);
        this.setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

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
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CharSelectActivity.this);
                builder.setTitle("Delete Character");
                builder.setMessage("Do you wish to delete the character \"" + chars.get(position).getName() +"\"?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                CharacterDB chara = realm.where(CharacterDB.class).equalTo("id", chars.get(position).getId()).findFirst();
                                realm.beginTransaction();
                                chara.deleteFromRealm();
                                realm.commitTransaction();
                                chars.remove(position);
                                listAdapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                            }
                        });

                builder.create().show();
                return true;
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
        List<CharacterDB> charsdb;
        charsdb = (List)realm.where(CharacterDB.class).findAll();
        for(CharacterDB chardb : charsdb){
            chars.add(new Character((chardb)));
        }

        return chars;
    }
}
