package ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.constants.PetType;
import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.data.PetRepository;

public class AddPetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);

        Button btnBack = findViewById(R.id.btn_back);
        Button btnSave = findViewById(R.id.btn_save_pet);
        TextInputEditText etName = findViewById(R.id.et_name);
        TextInputEditText etBreed = findViewById(R.id.et_breed);
        TextInputEditText etAge = findViewById(R.id.et_age);
        TextInputEditText etDescription = findViewById(R.id.et_description);
        Spinner spinnerType = findViewById(R.id.spinner_type);

        List<String> types = new ArrayList<>();
        for (PetType pt : PetType.values()) {
            types.add(pt.type);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String name = etName.getText() != null ? etName.getText().toString().trim() : "";
            String breed = etBreed.getText() != null ? etBreed.getText().toString().trim() : "";
            String age = etAge.getText() != null ? etAge.getText().toString().trim() : "";
            String description = etDescription.getText() != null ? etDescription.getText().toString().trim() : "";
            String type = spinnerType.getSelectedItem().toString();

            if (name.isEmpty() || breed.isEmpty() || age.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            PetRepository.getInstance().addPet(name, breed, age, type, description);
            Toast.makeText(this, "¡Mascota agregada con éxito!", Toast.LENGTH_SHORT).show();
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean dispatchTouchEvent(android.view.MotionEvent ev) {
        if (ev.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            android.view.View v = getCurrentFocus();
            if (v instanceof android.widget.EditText) {
                android.graphics.Rect outRect = new android.graphics.Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) ev.getRawX(), (int) ev.getRawY())) {
                    v.clearFocus();
                    android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
