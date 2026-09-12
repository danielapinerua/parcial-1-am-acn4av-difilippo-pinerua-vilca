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

        petList.add(new Pet(
                1,
                "Max",
                "Golden Retriever",
                "5 meses",
                "Perro",
                "Muy amigable y juguetón. Le encanta correr y jugar.",
                false,
                R.drawable.max
        ));

        petList.add(new Pet(
                2,
                "Daphne",
                "Sin raza",
                "5 años",
                "Gato",
                "Tranquila y cariñosa. Le encanta dormir al sol.",
                false,
                R.drawable.daphne
        ));

        petList.add(new Pet(
                3,
                "Rocky",
                "Bulldog",
                "3 años",
                "Perro",
                "Es fuerte pero muy cariñoso y se lleva muy bien con los niños.",
                true,
                R.drawable.rocky
        ));

        petList.add(new Pet(
                4,
                "Paris",
                "Angora turco",
                "4 meses",
                "Gato",
                "Muy cariñosa y juguetona. Necesita cuidados frecuentes de su pelaje.",
                false,
                R.drawable.paris
        ));

        petList.add(new Pet(
                5,
                "Charlie",
                "Sin raza",
                "2 meses",
                "Perro",
                "Muy activo y juguetón.",
                false,
                R.drawable.charlie
        ));

        petList.add(new Pet(
                6,
                "Mora",
                "Sin raza",
                "2 años",
                "Gato",
                "Muy tranquila y cariñosa. Le encanta recibir mimos y dormir en lugares calentitos.",
                false,
                R.drawable.mora
        ));

        petList.add(new Pet(
                7,
                "Toby",
                "Sin raza",
                "1 año",
                "Perro",
                "Muy juguetón y cariñoso. Le encanta salir a pasear y jugar con otros perros.",
                false,
                R.drawable.toby
        ));
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