package com.rodaja.gardenia.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rodaja.gardenia.R;
import com.rodaja.gardenia.model.adapter.WifiAdapter;
import com.rodaja.gardenia.model.configuration.Permissions;
import com.rodaja.gardenia.model.entity.User;
import com.rodaja.gardenia.view.menu.gallery.GalleryViewModel;
import com.rodaja.gardenia.view.multimedia.Image;

import java.util.ArrayList;
import java.util.List;

import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE;
import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_APP_DISALLOWED;
import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_INTERNAL;
import static android.net.wifi.WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS;

public class AddFlowerPot extends Fragment {

    private Context context;
    private View view;

    private Button btnConfirm;

    private WifiManager wifi;
    private WifiAdapter adapter;
    private ArrayList<String> listWifi = new ArrayList<>();
    private ListView listView;
    private List<ScanResult> results;

    private ImageView ivGifAddFlowerpot;
    private User user;

    private SwipeRefreshLayout swipeRefreshLayout;

    /*
    BroadcastReceiver wifiReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifi.getScanResults();
            unregisterReceiver(this);
            listWifi.clear();
            for (ScanResult scanResult : results) {
                listWifi.add(scanResult.SSID);
                adapter.notifyDataSetChanged();
            }
        }
    };

     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_add_flower_pot, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        view = getView();
        context = getContext();
        inicializar();

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            contexto = this;

            Image.setGif(contexto, R.drawable.gif_add_flowerpot, ivGifAddFlowerpot);

        } else {

            inicializarMenu();
            contexto = this;

            getWifiList();

            swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        ivMenuIconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(Home.class, user);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewView(AddFlowerPotWebView.class, user);
            }
        });

         */

    }
/*
    private void getWifiList() {
        if (Permissions.checkPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION) != true) {
            Permissions.askForPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Permissions.REQUEST_ACCESS_FINE_LOCATION);
            getWifiList();
        } else {
            wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            checkWifiEnabled();
            setAdapter();
            scanWifi();
        }

    }

    private void setAdapter() {
        adapter = new WifiAdapter(listWifi, contexto);
        listView.setAdapter(adapter);
    }

    private void checkWifiEnabled() {
        if (!wifi.isWifiEnabled()) {
            Toast.makeText(contexto, R.string.add_flowerpot_no_wifi, Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }
    }
 */
    private void inicializar() {
        btnConfirm = view.findViewById(R.id.btn_confirmar_agregar);
        ivGifAddFlowerpot = view.findViewById(R.id.ivGifAddFlowerpot);
        listView = view.findViewById(R.id.listView);
        swipeRefreshLayout = view.findViewById(R.id.swipeAddMaceta);
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
    private void scanWifi() {
        listWifi.clear();
        registerReceiver(wifiReciver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifi.startScan();

        Toast.makeText(this, R.string.add_flowerpot_scan_wifi, Toast.LENGTH_LONG).show();
    }


    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            scanWifi();
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    private void connectToWifi(String ssid) {
        Log.d("connect wifi", "ha entrado en connect wifi");
        WifiConfiguration wifiConfig = new WifiConfiguration();

        WifiInfo wifiInfo = wifi.getConnectionInfo();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

            WifiNetworkSuggestion.Builder builder = new WifiNetworkSuggestion.Builder()
                    .setSsid(ssid);
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


        } else {

            wifiConfig.SSID = "\"" + ssid + "\"";

            Log.d("SSID", wifiConfig.SSID);

            wifiConfig.status = WifiConfiguration.Status.DISABLED;
            wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

            wifi.getConfiguredNetworks().clear();
            int netId = wifi.addNetwork(wifiConfig);

            wifi.disconnect();
            wifi.enableNetwork(netId, true);
            wifi.reconnect();
        }
    }

 */
}
