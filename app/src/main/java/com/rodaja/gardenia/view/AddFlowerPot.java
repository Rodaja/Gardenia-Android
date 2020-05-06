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
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.net.wifi.WifiNetworkSuggestion;
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

import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE;
import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_APP_DISALLOWED;
import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_INTERNAL;
import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS;

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
            listWifi.clear();
            for (ScanResult scanResult : results){
                listWifi.add(scanResult.SSID);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower_pot);

        inicializarMenu();
        contexto = this;

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        checkWifiEnabled();

        setAdapter();

        scanWifi();

        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View elemento, int i, long l) {

                TextView texto = elemento.findViewById(R.id.tvWifi);
                String ssid = texto.getText().toString();

                Log.d("List", "Se ha hecho click");
                Log.d("SSID", ssid);

                connectToWifi(ssid);
            }
        });

    }

    private void setAdapter() {
        adapter = new AddMacetaAdapter(listWifi, contexto);
        listView.setAdapter(adapter);
    }

    private void checkWifiEnabled() {
        if (!wifi.isWifiEnabled()){
            Toast.makeText(this, R.string.wifi_desactivada, Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }
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

        /*
        results = wifi.getScanResults();

        for (ScanResult scanResult : results){
            listWifi.add(scanResult.SSID);
            adapter.notifyDataSetChanged();
        }

         */

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

    private void connectToWifi(String ssid) {
        Log.d("connect wifi", "ha entrado en connect wifi");
        WifiConfiguration wifiConfig = new WifiConfiguration();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

            WifiNetworkSuggestion.Builder builder = new WifiNetworkSuggestion.Builder()
                    .setSsid(ssid)
                    .setWpa2Passphrase("9B1A406D362D963500AF");
            WifiNetworkSuggestion suggestion = builder.build();

            ArrayList<WifiNetworkSuggestion> list = new ArrayList<>();
            list.add(suggestion);


            wifi.removeNetworkSuggestions(list);
            int status = wifi.addNetworkSuggestions(list);

            if (status == STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
                Log.d("ESTATUS", "Conectado");
            } else if (status == STATUS_NETWORK_SUGGESTIONS_ERROR_INTERNAL) {
                Log.d("ESTATUS", "Error interno");
            } else if (status == STATUS_NETWORK_SUGGESTIONS_ERROR_APP_DISALLOWED) {
                Log.d("ESTATUS", "No Permisos");
            } else if (status == STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE) {
                Log.d("ESTATUS", "Duplicado");
            }





        /*wifiConfig.SSID = "\"" + ssid + "\"";

        List<WifiConfiguration> networks = wifi.getConfiguredNetworks();
        for(WifiConfiguration c : networks) {
            Log.d("Wifi configured", c.SSID);
        }

        Log.d("SSID", wifiConfig.SSID);

        wifiConfig.status = WifiConfiguration.Status.DISABLED;

        wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wifiConfig.allowedAuthAlgorithms.clear();
        wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

        int netId = wifi.addNetwork(wifiConfig);
        wifi.enableNetwork(netId, true);


        /*
        List<WifiConfiguration> list = wifi.getConfiguredNetworks();
        Log.d("List size", String.valueOf(list.size()));

        for (WifiConfiguration i : list) {
            Log.d("listado wifi", i.SSID);
            if (i.SSID != null && i.SSID.equals("\"" + ssid + "\"")) {
                Log.d("wifi javo", "ha entrado");

                break;
            }
        }

         */

        }
    }
}
