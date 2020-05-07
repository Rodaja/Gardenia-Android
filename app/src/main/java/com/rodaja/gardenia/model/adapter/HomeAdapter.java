package com.rodaja.gardenia.model.adapter;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.view.multimedia.Image;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<FlowerPot> macetas;
    private LayoutInflater mInflater;
//  private ItemClickListener mClickListener;


    public HomeAdapter(Context context, List<FlowerPot> macetas) {
        this.mInflater = LayoutInflater.from(context);
        this.macetas = macetas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext();
        int layiytIdParaListItem = R.layout.activity_home_array_adapter;
        mInflater = LayoutInflater.from(mcontext);
        boolean attachToParentRapido = false;
        View view = mInflater.inflate(layiytIdParaListItem, parent, attachToParentRapido);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.iv_foto_bandera.setImageURI(Uri.parse(paises.get(position).getFoto_bandera()));
        holder.nombre_maceta.setText(macetas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return macetas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // ImageView iv_foto_bandera;
        TextView nombre_maceta;
        ImageView imagen_maceta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // iv_foto_bandera = itemView.findViewById(R.id.banderaPais);
            imagen_maceta = itemView.findViewById(R.id.ivMenuIconLeft);
            nombre_maceta = itemView.findViewById(R.id.tvNombreMaceta);
        }

        void bind(int listaIndex) {
            // Image.setImageURL(context,paises.get(listaIndex).getFoto_bandera(),iv_foto_bandera);
            Image.setImage(context, R.drawable.detalles_principal, imagen_maceta);
            nombre_maceta.setText(listaIndex);
        }
    }
}
