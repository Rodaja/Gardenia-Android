package com.rodaja.gardenia.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.entity.Pais;
import com.rodaja.gardenia.view.Country;
import com.rodaja.gardenia.view.multimedia.Utils;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private Context contexto;
    private List<Pais> paises;
    private LayoutInflater mInflater;
    private RequestQueue request;
    private String pais_seleccionado;


    public CountryAdapter(Context context , List<Pais> paises) {
        contexto = context;
        this.mInflater = LayoutInflater.from(context);
        this.paises = paises;
        request = Volley.newRequestQueue(context);
    }




    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_foto_bandera;
        TextView nombre_pais;
        TextView siglas_pais;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto_bandera = (ImageView) itemView.findViewById(R.id.banderaPais);
            nombre_pais = itemView.findViewById(R.id.nombrePais);
            siglas_pais = itemView.findViewById(R.id.siglasPais);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext();
        int layiytIdParaListItem = R.layout.activity_country_adaptador_paises;
        mInflater = LayoutInflater.from(mcontext);
        boolean attachToParentRapido = false;
        View view = mInflater.inflate(layiytIdParaListItem,parent,attachToParentRapido);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, final int position) {
        Utils.fetchSvg(holder.iv_foto_bandera.getContext(), paises.get(position).getFoto_bandera(), holder.iv_foto_bandera);
        holder.siglas_pais.setText(paises.get(position).getSiglas_pais());
        holder.nombre_pais.setText(paises.get(position).getNombre_pais());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto,"Has seleccionado: " + paises.get(position).getNombre_pais(),Toast.LENGTH_SHORT).show();
                Country.setPais_seleccionado(paises.get(position).getNombre_pais());
            }
        });

    }



    @Override
    public int getItemCount() {
        return paises.size();
    }

    public  String getPais_selecionado(String pais_seleccionado){
        return pais_seleccionado;
    }
}


