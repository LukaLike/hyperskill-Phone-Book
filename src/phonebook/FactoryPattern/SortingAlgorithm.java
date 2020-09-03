package phonebook.FactoryPattern;

public interface SortingAlgorithm {
    void execute();

    static void printFullTime(int found, int total, long timeElapsed, long sortingTime, long searchingTime, boolean success) {
        printTime(found, total, timeElapsed);
        if (success) System.out.printf("Sorting time: %s\n", millisecondsToTime(sortingTime));
        else System.out.printf("Sorting time: %s - STOPPED, moved to linear search\n", millisecondsToTime(sortingTime));
        System.out.printf("Searching time: %s\n\n", millisecondsToTime(searchingTime));
    }

    static void printTime(int found, int total, long timeElapsed) {
        System.out.printf("Found %d / %d entries. Time taken: %s\n",
                found, total, millisecondsToTime(timeElapsed));
    }

    static String millisecondsToTime(long timeElapsed) {
        long minutes = (timeElapsed / 1000) / 60;
        long seconds = (timeElapsed / 1000) % 60;
        long milliseconds = timeElapsed % 1000;

        return String.format("%d min. %d sec. %d ms.", minutes, seconds, milliseconds);
    }
}
