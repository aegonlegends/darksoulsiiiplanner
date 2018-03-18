package br.edu.iff.pooa20172.darksoulsiiiplanner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import br.edu.iff.pooa20172.darksoulsiiiplanner.Model.Character;
import br.edu.iff.pooa20172.darksoulsiiiplanner.R;

public class CharacterAdapter extends ArrayAdapter<Character> {
    private final Context context;
    private final ArrayList<Character> elementos;

    public CharacterAdapter(Context context, ArrayList<Character> elementos) {
        super(context, R.layout.item_character, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] covenants = context.getResources().getStringArray(R.array.covenantNames);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_character, parent, false);
        TextView tvName = (TextView) rowView.findViewById(R.id.name);
        TextView tvLevel = (TextView) rowView.findViewById(R.id.level);
        TextView tvCovenant = (TextView) rowView.findViewById(R.id.covenant);
        tvName.setText(elementos.get(position).getName());
        tvLevel.setText(String.format(Locale.ENGLISH, "Level %d", elementos.get(position).getLevel()));
        tvCovenant.setText(covenants[elementos.get(position).getCovenant()]);

        return rowView;
    }
}
