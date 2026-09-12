package ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.data.PetRepository;
import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.models.Pet;

public class PetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_detail);

        Intent intent = getIntent();
        int petId = intent.getIntExtra("pet_id", -1);

        PetRepository petRepository = PetRepository.getInstance();
        Pet pet = petRepository.getPetById(petId);
        ImageView petImage = findViewById(R.id.pet_image);
        TextView petName = findViewById(R.id.pet_name);
        TextView petBreed = findViewById(R.id.pet_breed);
        TextView petAge = findViewById(R.id.pet_age);
        TextView petDescription = findViewById(R.id.pet_description);
        TextView petStatus = findViewById(R.id.pet_status);
        Button btnAdopt = findViewById(R.id.btn_adopt);

        petImage.setImageResource(pet.getImageResId());
        petName.setText(pet.getName());
        petBreed.setText(pet.getBreed());
        petAge.setText(pet.getAge());
        petDescription.setText(pet.getDescription());

        if (pet.isAdopted()) {
            petStatus.setText(R.string.status_adopted);
            btnAdopt.setEnabled(false);
        } else {
            petStatus.setText(R.string.status_available);
        }

        btnAdopt.setOnClickListener(v -> {
            petRepository.adoptPet(petId);

            Intent confirmIntent = new Intent(PetDetailActivity.this, ConfirmationActivity.class);
            confirmIntent.putExtra("pet_id", petId);
            startActivity(confirmIntent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}