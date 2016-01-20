package br.com.karloskelvin.crudrealm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import br.com.karloskelvin.crudrealm.model.Contato;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by Karlos Kelvin on 06/01/16.
 * Desenvolvedor de Sistemas - UFCA
 * Analista e Desenvolvedor de Sistemas.
 */
public class ContatosAdapter extends RealmBaseAdapter<Contato> implements ListAdapter {

    private static class MyViewHolder{
        TextView nome;
    }

    public ContatosAdapter(Context context, RealmResults<Contato> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            viewHolder = new MyViewHolder();
            viewHolder.nome = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        Contato item = realmResults.get(position);
        viewHolder.nome.setText(item.getNome());

        return convertView;
    }

}
