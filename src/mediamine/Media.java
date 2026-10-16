package mediamine;

public class Media {

    private int id;
    private String title;
    private String type;
    private String category;
    private String source;
    private String transcript;
    private String caption;
    private int views;
    private int duration;

    public Media(int id, String title, String type, String category,
                 String source, String transcript, String caption,
                 int views, int duration) {

        this.id = id;
        this.title = title;
        this.type = type;
        this.category = category;
        this.source = source;
        this.transcript = transcript;
        this.caption = caption;
        this.views = views;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getSource() {
        return source;
    }

    public String getTranscript() {
        return transcript;
    }

    public String getCaption() {
        return caption;
    }

    public int getViews() {
        return views;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {

        return String.format(
            "[%d] %-35s | %-8s | %-15s | Views: %-7d | Source: %s",
            id, title, type, category, views, source
        );
    }
}