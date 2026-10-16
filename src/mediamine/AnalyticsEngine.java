package mediamine;

import java.util.*;

public class AnalyticsEngine {

    private MediaLibrary library;

    public AnalyticsEngine(MediaLibrary library) {
        this.library = library;
    }

    public void showDashboard() {

        List<Media> media = library.getMediaList();

        int totalViews = 0;
        int totalDuration = 0;

        Map<String, Integer> categories = new HashMap<>();
        Map<String, Integer> types = new HashMap<>();

        for (Media m : media) {

            totalViews += m.getViews();
            totalDuration += m.getDuration();

            categories.put(
                    m.getCategory(),
                    categories.getOrDefault(
                            m.getCategory(), 0) + 1);

            types.put(
                    m.getType(),
                    types.getOrDefault(
                            m.getType(), 0) + 1);
        }

        System.out.println("\n================================================");
        System.out.println("             MEDIAMINE ANALYTICS");
        System.out.println("================================================");

        System.out.println("Total Media Records : " + media.size());
        System.out.println("Total Views         : " + totalViews);
        System.out.println("Total Duration      : " + totalDuration + " mins");

        System.out.println("\nMedia By Category:");

        for (String category : categories.keySet()) {

            System.out.println(
                    "  " + category +
                    " -> " + categories.get(category));
        }

        System.out.println("\nMedia By Type:");

        for (String type : types.keySet()) {

            System.out.println(
                    "  " + type +
                    " -> " + types.get(type));
        }

        System.out.println("================================================");
    }

    public void searchKeyword(String keyword) {

        System.out.println("\nSearching MediaMine index for: "
                + keyword);

        boolean found = false;

        for (Media media : library.getMediaList()) {

            List<Integer> positions =
                    StringAlgorithms.kmpSearch(
                            media.getTranscript(),
                            keyword);

            if (!positions.isEmpty()) {

                found = true;

                System.out.println("\nMATCH FOUND");

                System.out.println("ID       : "
                        + media.getId());

                System.out.println("Title    : "
                        + media.getTitle());

                System.out.println("Category : "
                        + media.getCategory());

                System.out.println("Location : transcript");

                System.out.println("Position : "
                        + positions);
            }
        }

        if (!found) {
            System.out.println("\nNo matching media found.");
        }
    }

    public void detectTopics(String text) {

        String[] topics = {
                "artificial intelligence",
                "climate",
                "education",
                "finance",
                "sports",
                "cybersecurity",
                "space",
                "healthcare"
        };

        List<String> detected =
                StringAlgorithms.ahoCorasick(
                        text,
                        topics);

        System.out.println("\nDetected Topics:");

        if (detected.isEmpty()) {

            System.out.println("No known topics detected.");

        } else {

            for (String topic : detected) {
                System.out.println("  ✓ " + topic);
            }
        }
    }
}