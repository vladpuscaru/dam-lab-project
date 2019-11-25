package eu.ase.ro.damapp.fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.damapp.R;
import eu.ase.ro.damapp.network.HttpManager;
import eu.ase.ro.damapp.network.HttpResponse;
import eu.ase.ro.damapp.network.Item;
import eu.ase.ro.damapp.network.JsonParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferMarket extends Fragment {
    private static final String URL = "http://api.myjson.com/bins/16f54z";
    private static HttpResponse httpResponse;

    private Button btnGoalkeeper;
    private Button btnInter;
    private Button btnCenter;
    private Button btnWinger;
    private ListView lvResponse;
    private List<Item> selectedResponse = new ArrayList<>();



    public TransferMarket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer_market, container, false);
        initComponents(view);
        // Conexiune retea
        new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                httpResponse = JsonParser.parseJson(s);
                if (httpResponse != null) {
                    Toast.makeText(getContext(),
                            s,
                            Toast.LENGTH_LONG).show();
                }
            }
        }.execute(URL);
        return view;
    }

    private void initComponents(View view) {
        btnGoalkeeper = view.findViewById(R.id.transfer_market_btn_goalkeeper);
        btnCenter = view.findViewById(R.id.transfer_market_btn_center);
        btnWinger = view.findViewById(R.id.transfer_market_btn_winger);
        btnInter = view.findViewById(R.id.transfer_market_btn_inter);
        unselectedButtons();
        lvResponse = view.findViewById(R.id.transfer_market_lv);
        // atasare adapter
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, selectedResponse);
        lvResponse.setAdapter(adapter);

        // schimbare
        btnGoalkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (httpResponse != null && httpResponse.getGoalkeeper() != null) {
                    unselectedButtons();
                    selectButton(btnGoalkeeper);
                    // adaugare lista
                    selectResponse(httpResponse.getGoalkeeper());
                }
            }
        });
        btnInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (httpResponse != null && httpResponse.getInter() != null) {
                    unselectedButtons();
                    selectButton(btnInter);
                    // adaugare lista
                    selectResponse(httpResponse.getInter());
                }
            }
        });
        btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (httpResponse != null && httpResponse.getCenter() != null) {
                    unselectedButtons();
                    selectButton(btnCenter);
                    // adaugare lista
                    selectResponse(httpResponse.getCenter());
                }
            }
        });
        btnWinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (httpResponse != null && httpResponse.getWinger() != null) {
                    unselectedButtons();
                    selectButton(btnWinger);
                    // adaugare lista
                    selectResponse(httpResponse.getWinger());
                }
            }
        });
    }

    private void selectResponse(List<Item> list) {
        selectedResponse.clear();
        selectedResponse.addAll(list);
        ArrayAdapter<Item> adapter = (ArrayAdapter<Item>) lvResponse.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void unselectedButtons() {
        btnGoalkeeper.setBackgroundColor(Color.GRAY);
        btnWinger.setBackgroundColor(Color.GRAY);
        btnCenter.setBackgroundColor(Color.GRAY);
        btnInter.setBackgroundColor(Color.GRAY);
    }

    private void selectButton(Button button) {
        button.setBackgroundColor(Color.GREEN);
    }

}
