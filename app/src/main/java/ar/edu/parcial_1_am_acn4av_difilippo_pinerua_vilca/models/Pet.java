package ar.edu.parcial_1_am_acn4av_difilippo_pinerua_vilca.models;

public class Pet {
    private int id;
    private String name;
    private String breed;
    private String age;
    private String type;
    private String description;
    private boolean isAdopted;
    private int imageResId;

    public Pet(int id, String name, String breed, String age, String type, String description, boolean isAdopted, int imageResId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.type = type;
        this.description = description;
        this.isAdopted = isAdopted;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getAge() { return age; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public boolean isAdopted() { return isAdopted; }
    public int getImageResId() { return imageResId; }

    public void setAdopted(boolean adopted) { isAdopted = adopted; }
}