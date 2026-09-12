package ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.data;

import java.util.ArrayList;
import java.util.List;

import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.R;
import ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.models.Pet;

public class PetRepository {
    private static PetRepository instance;
    private List<Pet> petList;

    private PetRepository() {
        petList = new ArrayList<>();
        initDummyData();
    }

    public static PetRepository getInstance() {
        if (instance == null) {
            instance = new PetRepository();
        }
        return instance;
    }

    private void initDummyData() {
        int defaultImg = R.mipmap.ic_launcher;

        petList.add(new Pet(1, "Max", "Golden Retriever", "2 years", "Dog", "Very friendly and loves to play fetch.", false, defaultImg));
        petList.add(new Pet(2, "Luna", "Siamese", "1 year", "Cat", "Quiet and loves to sleep in the sun.", false, defaultImg));
        petList.add(new Pet(3, "Rocky", "Bulldog", "3 years", "Dog", "Strong but very gentle with kids.", true, defaultImg));
        petList.add(new Pet(4, "Bella", "Persian", "4 years", "Cat", "Requires daily grooming, very affectionate.", false, defaultImg));
        petList.add(new Pet(5, "Charlie", "Beagle", "6 months", "Dog", "High energy pup, needs training.", false, defaultImg));
    }

    public List<Pet> getAllPets() {
        return petList;
    }

    public List<Pet> getPetsByType(String type) {
        if (type.equalsIgnoreCase("All")) {
            return petList;
        }
        List<Pet> filteredList = new ArrayList<>();
        for (Pet pet : petList) {
            if (pet.getType().equalsIgnoreCase(type)) {
                filteredList.add(pet);
            }
        }
        return filteredList;
    }

    public Pet getPetById(int id) {
        for (Pet pet : petList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public void adoptPet(int id) {
        Pet pet = getPetById(id);
        if (pet != null) {
            pet.setAdopted(true);
        }
    }
}