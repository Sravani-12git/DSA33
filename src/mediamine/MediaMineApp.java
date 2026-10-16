package mediamine;

import java.util.*;

public class MediaMineApp {

    private static Scanner sc =
            new Scanner(System.in);

    public static void main(String[] args) {

        MediaLibrary library =
                new MediaLibrary();

        AnalyticsEngine analytics =
                new AnalyticsEngine(library);

        while (true) {

            showHeader();

            System.out.println("1. Search Media");
            System.out.println("2. Detect Multiple Topics");
            System.out.println("3. Find Similar Content");
            System.out.println("4. Compare Transcripts");
            System.out.println("5. Correct Search Query");
            System.out.println("6. Select Content Categories");
            System.out.println("7. Optimize Processing Pipeline");
            System.out.println("8. Analytics Dashboard");
            System.out.println("9. View Media Library");
            System.out.println("0. Exit");

            System.out.print("\nEnter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    searchMedia(analytics);
                    break;

                case 2:
                    detectTopics(analytics);
                    break;

                case 3:
                    findSimilarContent(library);
                    break;

                case 4:
                    compareTranscripts(library);
                    break;

                case 5:
                    correctQuery();
                    break;

                case 6:
                    selectCategories();
                    break;

                case 7:
                    optimizePipeline();
                    break;

                case 8:
                    analytics.showDashboard();
                    break;

                case 9:
                    library.displayAll();
                    break;

                case 0:

                    System.out.println(
                            "\nShutting down MediaMine...");

                    System.out.println(
                            "Analytics session completed.");

                    return;

                default:
                    System.out.println(
                            "\nInvalid option.");
            }

            pause();
        }
    }

    private static void showHeader() {

        System.out.println("\n\n");
        System.out.println(
                "============================================================");

        System.out.println(
                "                    MEDIAMINE");

        System.out.println(
                "          Intelligent Media Analytics Platform");

        System.out.println(
                "============================================================");
    }

    // ================= 1 =================

    private static void searchMedia(
            AnalyticsEngine analytics) {

        sc.nextLine();

        System.out.print(
                "\nEnter keyword to search: ");

        String keyword = sc.nextLine();

        analytics.searchKeyword(keyword);
    }

    // ================= 2 =================

    private static void detectTopics(
            AnalyticsEngine analytics) {

        sc.nextLine();

        System.out.print(
                "\nEnter media text/transcript: ");

        String text = sc.nextLine();

        analytics.detectTopics(text);
    }

    // ================= 3 =================

    private static void findSimilarContent(
            MediaLibrary library) {

        sc.nextLine();

        System.out.print(
                "\nEnter search phrase: ");

        String query = sc.nextLine();

        System.out.println(
                "\nSearching suffix index...");

        boolean found = false;

        for (Media media :
                library.getMediaList()) {

            if (SuffixStructures.containsSubstring(
                    media.getTranscript(),
                    query)) {

                System.out.println(
                        "\nSimilar content found:");

                System.out.println(
                        media);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "\nNo similar content found.");
        }
    }

    // ================= 4 =================

    private static void compareTranscripts(
            MediaLibrary library) {

        System.out.print(
                "\nEnter first Media ID: ");

        int id1 = readInt();

        System.out.print(
                "Enter second Media ID: ");

        int id2 = readInt();

        Media m1 = library.findById(id1);
        Media m2 = library.findById(id2);

        if (m1 == null || m2 == null) {

            System.out.println(
                    "\nInvalid media ID.");

            return;
        }

        String common =
                SuffixStructures.longestCommonSubstring(
                        m1.getTranscript(),
                        m2.getTranscript());

        System.out.println(
                "\nCommon phrase detected:");

        if (common.isEmpty()) {

            System.out.println(
                    "No significant common phrase.");

        } else {

            System.out.println(
                    "\"" + common + "\"");
        }

        int similarityDistance =
                DynamicProgramming.levenshtein(
                        m1.getTranscript(),
                        m2.getTranscript());

        System.out.println(
                "\nLevenshtein Distance: "
                + similarityDistance);
    }

    // ================= 5 =================

    private static void correctQuery() {

        sc.nextLine();

        System.out.print(
                "\nEnter search query: ");

        String query = sc.nextLine();

        String[] suggestions = {
                "artificial intelligence",
                "cybersecurity",
                "education",
                "climate change",
                "space exploration",
                "financial outlook"
        };

        String best = "";
        int bestDistance = Integer.MAX_VALUE;

        for (String suggestion :
                suggestions) {

            int distance =
                    DynamicProgramming
                            .damerauLevenshtein(
                                    query.toLowerCase(),
                                    suggestion);

            if (distance < bestDistance) {

                bestDistance = distance;
                best = suggestion;
            }
        }

        System.out.println(
                "\nSuggested Query: " + best);

        System.out.println(
                "Edit Distance: " + bestDistance);
    }

    // ================= 6 =================

    private static void selectCategories() {

        System.out.println(
                "\n========== CONTENT CATEGORY SELECTION ==========");

        String[] categories = {
                "Technology",
                "Sports",
                "Science",
                "Education",
                "Finance"
        };

        int[] importance = {
                90, 70, 80, 85, 75
        };

        System.out.println(
                "Available Categories:");

        for (int i = 0;
             i < categories.length;
             i++) {

            System.out.println(
                    (i + 1) + ". "
                    + categories[i]
                    + " (priority "
                    + importance[i] + ")");
        }

        System.out.print(
                "\nEnter maximum priority budget: ");

        int budget = readInt();

        int result =
                DynamicProgramming
                        .bestCategorySelection(
                                importance,
                                budget);

        System.out.println(
                "\nMaximum achievable priority: "
                + result);
    }

    // ================= 7 =================

    private static void optimizePipeline() {

        System.out.println(
                "\n========== MEDIA PROCESSING OPTIMIZER ==========");

        System.out.println(
                "Pipeline:");

        System.out.println(
                "1. Caption Processing");

        System.out.println(
                "2. Transcript Analysis");

        System.out.println(
                "3. Keyword Indexing");

        System.out.println(
                "4. Report Generation");

        int[] dimensions = {
                10, 30, 5, 60, 20
        };

        int cost =
                DynamicProgramming
                        .matrixChainCost(dimensions);

        System.out.println(
                "\nMinimum processing cost: "
                + cost);

        System.out.println(
                "Matrix-chain optimization completed.");
    }

    // ================= INPUT =================

    private static int readInt() {

        while (!sc.hasNextInt()) {

            System.out.print(
                    "Enter a valid number: ");

            sc.next();
        }

        return sc.nextInt();
    }

    private static void pause() {

        System.out.println(
                "\nPress ENTER to continue...");

        sc.nextLine();
        sc.nextLine();
    }
}