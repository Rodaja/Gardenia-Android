package com.rodaja.gardenia.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.adapter.AddMacetaAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddFlowerPot extends AppCompatActivity {

    private Context contexto;
    //Atributos Menu
    private TextView tvTitulo;
    private ImageView ivMenuIconLeft;
    private ImageView ivMenuIconRight;
    private Button btnConfirm;

    private WifiManager wifi;
    private AddMacetaAdapter adapter;
    private ArrayList<String> listWifi = new ArrayList<>();
    private ListView listView;
    private List<ScanResult> results;

    private SwipeRefreshLayout swipeRefreshLayout;

    BroadcastReceiver wifiReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifi.getScanResults();
            unregisterReceiver(this);

            for (ScanResult scanResult : results){
                listWifi.add(scanResult.SSID);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower_pot);

        inicializarMenu();
        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(Home.class);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(AddFlowerPotWebView.class);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);



        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wifi.isWifiEnabled()){
            Toast.makeText(this, R.string.wifi_desactivada, Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }

        scanWifi();
        adapter = new AddMacetaAdapter(listWifi, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View elemento, int i, long l) {

                Toast.makeText(contexto, i , Toast.LENGTH_LONG);
                TextView texto = elemento.findViewById(R.id.tvWifi);
                String ssid = texto.getText().toString();

                Toast.makeText(contexto, "Has seleccionado la wifi " + ssid , Toast.LENGTH_LONG);
            }
        });

    }

    private void inicializarMenu() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.menu);

        tvTitulo  = findViewById(R.id.tvMenuTitulo);
        ivMenuIconLeft = findViewById(R.id.ivMenuIconLeft);
        ivMenuIconRight = findViewById(R.id.ivMenuIconRight);
        btnConfirm = findViewById(R.id.btn_confirmar_agregar);


        tvTitulo.setText(R.string.conectar_maceta);
        ivMenuIconRight.setImageResource(0);

        listView = findViewById(R.id.listView);
        swipeRefreshLayout = findViewById(R.id.swipeAddMaceta);
    }

    private void goToNewView(Class goToView){
        Intent in = new Intent(this, goToView);
        startActivity(in);
    }

    private void scanWifi(){
        listWifi.clear();
        registerReceiver(wifiReciver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifi.startScan();

        results = wifi.getScanResults();

        for (ScanResult scanResult : results){
            listWifi.add(scanResult.SSID);

        }

        Toast.makeText(this, R.string.escaneo_wifi, Toast.LENGTH_LONG).show();
    }


    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            scanWifi();
            snackBar();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    private void snackBar(){
        final ConstraintLayout constraintLayout = findViewById(R.id.constLAddMaceta);
        Snackbar snackbar = Snackbar
                .make(constraintLayout, R.string.wifi_actualizada, Snackbar.LENGTH_LONG);
        View mView = snackbar.getView();
        // get textview inside snackbar view
        TextView mTextView = (TextView) mView.findViewById(R.id.snackbar_text);
        // set text to center
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            mTextView.setGravity(Gravity.CENTER_HORIZONTAL);

        snackbar.show();
    }

}
