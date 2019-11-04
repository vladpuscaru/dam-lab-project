package eu.ase.ro.damapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.ase.ro.damapp.AddPlayerActivity;
import eu.ase.ro.damapp.R;


public class PlayerAdapter extends ArrayAdapter<Player> {
    private Context context;
    private int resource;
    private List<Player> players;
    private LayoutInflater inflater;

    public PlayerAdapter(@NonNull Context context, int resource, List<Player> players, LayoutInflater inflater) {
        super(context, resource, players);
        this.context = context;
        this.resource = resource;
        this.players = players;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // instantiere view
        View view = inflater.inflate(resource, parent, false);

        // preluare obiect Player
        Player player = players.get(position);
        if (player != null) {
            // adaugare informatii pe ecran
            addName(view, player.getName());
            addBirthday(view, player.getBirthday());
            addNumber(view, player.getNumber());
            addPosition(view, player.getPosition());
            addFavHand(view, player.getFavHand());
        }

        return view;
    }

    private void addName(View view, String name) {
        TextView textView = view.findViewById(R.id.lv_players_row_tv_name);
        if (name != null && !name.trim().isEmpty()) {
            textView.setText(name);
        } else {
            textView.setText(R.string.adapter_player_no_content);
        }
    }

    private void addBirthday(View view, Date birthday) {
        TextView textView = view.findViewById(R.id.lv_players_row_tv_birthday);
        if (birthday != null) {
            textView.setText(new SimpleDateFormat(AddPlayerActivity.DATE_FORMAT, Locale.US).format(birthday));
        } else {
            textView.setText(R.string.adapter_player_no_content);
        }
    }

    private void addNumber(View view, Integer number) {
        TextView textView = view.findViewById(R.id.lv_players_row_tv_number);
        if (number != null) {
            textView.setText(String.valueOf(number));
        } else {
            textView.setText(R.string.adapter_player_no_content);
        }
    }

    private void addPosition(View view, String position) {
        TextView textView = view.findViewById(R.id.lv_players_row_tv_position);
        if (position != null && !position.trim().isEmpty()) {
            textView.setText(position);
        } else {
            textView.setText(R.string.adapter_player_no_content);
        }
    }

    private void addFavHand(View view, String favHand) {
        TextView textView = view.findViewById(R.id.lv_players_row_tv_position);
        if (favHand != null && !favHand.trim().isEmpty()) {
            textView.setText(favHand);
        } else {
            textView.setText(R.string.adapter_player_no_content);
        }
    }

}
