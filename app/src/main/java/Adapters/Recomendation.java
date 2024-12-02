package Adapters;

public class Recomendation {
    private final String title;
    private final String description;
    private final int imageResId;

    public Recomendation(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }
}
