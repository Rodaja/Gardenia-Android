package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.adapter.HomeAdapter;
import com.rodaja.gardenia.model.entity.FlowerPot;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.model.navegation.Navegation;

import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private Context context;
    private View view;
    private HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        view = getView();
        context = getContext();
        inicializar();


        //final List<FlowerPot> listaMacetas = user.getListFlowerPots();
        /*
        recyclerView.setLayoutManager(linearLayout);
        adapter = new HomeAdapter(context, listaMacetas);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Hacer metodo
                //Navegation.goToView(context, Details.class, recyclerView.getChildAdapterPosition(v));
            }
        });

         */

    }

    private void inicializar() {

        recyclerView = (RecyclerView) view.findViewById(R.id.rvHome);
        linearLayout = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayout);
    }

    /*
    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, Home.class);
        in.putExtra("user", user);
        startActivity(in);
    }

     */

    /*
    //TODO: Revisar
    private void goDetails(View view, Class goToView, int numeroMaceta, User user) {
        Intent in = new Intent(this, goToView);
        in.putExtra("numeroMaceta", numeroMaceta);
        in.putExtra("user", user);
        startActivity(in);
    }

     */


}
