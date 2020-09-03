package phonebook.FactoryPattern.SortingAlgorithms;

import phonebook.Main.Contact;
import phonebook.FactoryPattern.SortingAlgorithm;

import java.util.List;

public class LinearSearch implements SortingAlgorithm {

    private final List<Contact> contactList;
    private final List<Contact> people;

    static int matchCounter;
    static long searchTime;

    public LinearSearch(List<Contact> contactList, List<Contact> people) {
        this.contactList = contactList;
        this.people = people;
    }

    @Override
    public void execute() {
        System.out.println("Start searching (linear search)...");
        linearSearch();
        System.out.printf("Found %d / %d entries. Time taken: %s\n\n", matchCounter, people.size(),
                SortingAlgorithm.millisecondsToTime(searchTime));
    }

    public void linearSearch() {
        matchCounter = 0;

        long start = System.currentTimeMillis();

        for (Contact person : people) {
            for (Contact contact : contactList) {
                if (contact.getFullName().equals(person.getFullName())) {
                    matchCounter++;
                    break;
                }
            }
        }

        searchTime = System.currentTimeMillis() - start;
    }
}
