package eu.ase.ro.damapp.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.damapp.AddPlayerActivity;
import eu.ase.ro.damapp.R;
import eu.ase.ro.damapp.util.Player;
import eu.ase.ro.damapp.util.PlayerAdapter;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    public static final int REQUEST_CODE_MODIFY_PLAYER = 300;
    public static final String PLAYERS_KEY = "playersKey";
    public static final String PLAYER_KEY = "playerKey";
    ListView lvPlayers;
    List<Player> players = new ArrayList<>();
    private int modifiedPlayerPosition;

    public Home() {
        // Required empty public constructor
    }

    public void notifyInternal() {
        // notificare Adapter ListView sursa de date(players) s-a modificat
        PlayerAdapter playerAdapter = (PlayerAdapter)lvPlayers.getAdapter();
        playerAdapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MODIFY_PLAYER && resultCode == RESULT_OK) {
            Player modifiedPlayer = data.getParcelableExtra(AddPlayerActivity.ADD_PLAYER_KEY);
            if (modifiedPlayer != null) {
                players.set(modifiedPlayerPosition, modifiedPlayer);
            }

            notifyInternal();
        }
    }

    private void initComponents(View view) {
        lvPlayers = view.findViewById(R.id.home_lv_players);

        // adaugare listener
        lvPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // deschide un addPlayer si da-i playerul selectat
                Intent intent = new Intent(view.getContext(), AddPlayerActivity.class);

                // adauga playerul la intent
                Bundle bundle = new Bundle();
                bundle.putParcelable(PLAYER_KEY, players.get(position));
                intent.putExtras(bundle);

                modifiedPlayerPosition = position;

                startActivityForResult(intent, REQUEST_CODE_MODIFY_PLAYER);
            }
        });

        // preluare lista jucatori
        if (getArguments() != null) {
            players = getArguments().getParcelableArrayList(PLAYERS_KEY);
        }

        // creare adapter
        if (getContext() != null) {
//            ArrayAdapter<Player> adapter = new ArrayAdapter<>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, players);
//            lvPlayers.setAdapter(adapter);
            PlayerAdapter playerAdapter = new PlayerAdapter(getContext(),
                    R.layout.lv_row_view, players, getLayoutInflater());
            lvPlayers.setAdapter(playerAdapter);
        }
    }

}
