package ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.data.PetRepository;
import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.models.Pet;

public class PetListActivity extends AppCompatActivity {

    private LinearLayout layoutPetContainer;
    private PetRepository petRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_list);
        
        petRepository = PetRepository.getInstance();
        layoutPetContainer = findViewById(R.id.layout_pet_container);

        setupFilters();
        
        renderPets(petRepository.getAllPets());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupFilters() {
        Button btnAll = findViewById(R.id.btn_filter_all);
        Button btnDogs = findViewById(R.id.btn_filter_dogs);
        Button btnCats = findViewById(R.id.btn_filter_cats);

        btnAll.setOnClickListener(v -> renderPets(petRepository.getAllPets()));
        btnDogs.setOnClickListener(v -> renderPets(petRepository.getPetsByType("Dog")));
        btnCats.setOnClickListener(v -> renderPets(petRepository.getPetsByType("Cat")));
    }

    private void renderPets(List<Pet> pets) {
        layoutPetContainer.removeAllViews();

        for (Pet pet : pets) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.HORIZONTAL);
            
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, dpToPx(16));
            card.setLayoutParams(cardParams);
            
            card.setBackgroundColor(Color.WHITE);
            card.setElevation(dpToPx(4));
            card.setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));
            
            ImageView image = new ImageView(this);
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(dpToPx(80), dpToPx(80));
            image.setLayoutParams(imgParams);
            image.setImageResource(pet.getImageResId());
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            card.addView(image);
            
            LinearLayout textContainer = new LinearLayout(this);
            textContainer.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams textContainerParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
            );
            textContainerParams.setMargins(dpToPx(16), 0, 0, 0);
            textContainer.setLayoutParams(textContainerParams);
            textContainer.setGravity(Gravity.CENTER_VERTICAL);
            
            TextView nameText = new TextView(this);
            nameText.setText(pet.getName());
            nameText.setTextSize(18f);
            nameText.setTypeface(null, Typeface.BOLD);
            nameText.setTextColor(Color.BLACK);
            textContainer.addView(nameText);
            
            TextView breedText = new TextView(this);
            breedText.setText(pet.getBreed());
            breedText.setTextSize(14f);
            breedText.setTextColor(Color.DKGRAY);
            textContainer.addView(breedText);
            
            card.addView(textContainer);
            
            TextView statusText = new TextView(this);
            statusText.setText(pet.isAdopted() ? getString(R.string.status_adopted) : getString(R.string.status_available));
            statusText.setTextSize(12f);
            statusText.setTypeface(null, Typeface.BOLD);
            statusText.setTextColor(pet.isAdopted() ? Color.RED : Color.parseColor("#388E3C"));
            statusText.setPadding(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4));
            
            LinearLayout.LayoutParams statusParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            statusParams.gravity = Gravity.CENTER_VERTICAL;
            statusText.setLayoutParams(statusParams);
            
            card.addView(statusText);

            card.setOnClickListener(v -> {
                Intent intent = new Intent(PetListActivity.this, PetDetailActivity.class);
                intent.putExtra("pet_id", pet.getId());
                startActivity(intent);
            });

            layoutPetContainer.addView(card);
        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}