package mediamine;

import java.util.ArrayList;
import java.util.List;

public class MediaLibrary {

    private List<Media> mediaList;

    public MediaLibrary() {

        mediaList = new ArrayList<>();

        loadSampleData();
    }

    private void loadSampleData() {

        mediaList.add(new Media(
                1001,
                "Global Technology Summit 2026",
                "VIDEO",
                "Technology",
                "TechWorld",
                "Artificial intelligence is transforming healthcare education finance and media industries",
                "AI transforming industries",
                125000,
                42
        ));

        mediaList.add(new Media(
                1002,
                "Climate Change and Global Policy",
                "ARTICLE",
                "Environment",
                "Global News",
                "Global leaders discussed climate change renewable energy carbon emissions and environmental policy",
                "Climate policy discussion",
                87000,
                8
        ));

        mediaList.add(new Media(
                1003,
                "Champions League Match Analysis",
                "VIDEO",
                "Sports",
                "Sports Network",
                "The team controlled possession and created several opportunities during the second half",
                "Match analysis and highlights",
                245000,
                18
        ));

        mediaList.add(new Media(
                1004,
                "AI in Modern Education",
                "ARTICLE",
                "Education",
                "Education Daily",
                "Artificial intelligence is helping students with personalized learning intelligent tutoring and automated assessment",
                "AI powered education",
                112000,
                10
        ));

        mediaList.add(new Media(
                1005,
                "Economic Outlook 2026",
                "REPORT",
                "Finance",
                "Financial Times",
                "The global economy continues to experience changes in inflation interest rates investment and employment",
                "Economic outlook report",
                94000,
                15
        ));

        mediaList.add(new Media(
                1006,
                "Cybersecurity Threats",
                "VIDEO",
                "Technology",
                "CyberSecure",
                "Organizations are facing increasing cybersecurity threats including phishing ransomware and data breaches",
                "Cybersecurity threats explained",
                156000,
                25
        ));

        mediaList.add(new Media(
                1007,
                "Space Exploration Mission",
                "VIDEO",
                "Science",
                "Science Today",
                "Scientists successfully launched a new space exploration mission to study distant planets",
                "New space mission launched",
                198000,
                31
        ));

        mediaList.add(new Media(
                1008,
                "Election Policy Discussion",
                "ARTICLE",
                "Politics",
                "National News",
                "Political leaders discussed economic development education healthcare and infrastructure policies",
                "Policy discussion",
                301000,
                12
        ));
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public Media findById(int id) {

        for (Media media : mediaList) {

            if (media.getId() == id) {
                return media;
            }
        }

        return null;
    }

    public void displayAll() {

        System.out.println("\n================ MEDIA LIBRARY ================\n");

        for (Media media : mediaList) {
            System.out.println(media);
        }

        System.out.println("\n================================================");
    }
}