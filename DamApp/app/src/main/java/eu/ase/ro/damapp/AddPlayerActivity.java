package eu.ase.ro.damapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.ase.ro.damapp.util.Player;

public class AddPlayerActivity extends AppCompatActivity {
    public static final String ADD_PLAYER_KEY = "eu.ase.ro.damapp.extra.PLAYER";
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    private TextInputEditText etName;
    private TextInputEditText etBirthday;
    private TextInputEditText etNumber;
    private Spinner spnPosition;
    private RadioGroup rgFavHand;
    private Button btnSave;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        initComponents();

        intent = getIntent();
    }

    private void initComponents() {
        etName = findViewById(R.id.add_player_et_name);
        etBirthday = findViewById(R.id.add_player_et_birthday);
        etNumber = findViewById(R.id.add_player_et_number);
        spnPosition = findViewById(R.id.add_player_spn_positions);
        rgFavHand = findViewById(R.id.add_player_rg_fav_hand);
        btnSave = findViewById(R.id.add_player_btn_save);

        // initializare spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_player_positions_values, R.layout.support_simple_spinner_dropdown_item);
        spnPosition.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validare
                validate();
                // construire obiect
                Player player = createPlayerFromView();
                Toast.makeText(getApplicationContext(), player.toString(), Toast.LENGTH_LONG).show();
                // transfer
                // -- punem pe intent informatia
                intent.putExtra(ADD_PLAYER_KEY, player);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Player createPlayerFromView() {
        String name = etName.getText().toString();
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat(DATE_FORMAT, Locale.US).parse(etBirthday.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer number = Integer.parseInt(etNumber.getText().toString());
        String position = spnPosition.getSelectedItem().toString();
        RadioButton rbChecked = findViewById(rgFavHand.getCheckedRadioButtonId());
        String favHand = rbChecked.getText().toString();


        return new Player(name, birthday, number, position, favHand);
    }

    private boolean validate() {
        if (etName.getText() == null || etName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_player_name_error_message, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etNumber.getText() == null || etNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_player_number_error_message, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etBirthday.getText() == null || etBirthday.getText().toString().trim().isEmpty() || !validateDate(etBirthday.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.add_player_date_error_message, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validateDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        try {
            format.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
