package eu.ase.ro.damapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.android.material.textfield.TextInputEditText;

public class AddPlayerActivity extends AppCompatActivity {
    private TextInputEditText etName;
    private TextInputEditText etBirthday;
    private TextInputEditText etNumber;
    private Spinner spnPosition;
    private RadioGroup rgFavHand;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        initComponents();
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
    }
}
