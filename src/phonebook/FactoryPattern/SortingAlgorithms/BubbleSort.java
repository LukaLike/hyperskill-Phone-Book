package phonebook.FactoryPattern.SortingAlgorithms;

import phonebook.Main.Contact;
import phonebook.FactoryPattern.SortingAlgorithm;
import phonebook.Main.Searching;

import java.util.Collections;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {

    private final List<Contact> contactList;
    private final List<Contact> people;
    private long start;

    public BubbleSort(List<Contact> contactList, List<Contact> people) {
        this.contactList = contactList;
        this.people = people;
    }

    public boolean bubble(long timeLimit) {
        for (int i = 0; i < contactList.size() - 1; i++) {
            for (int j = 0; j < contactList.size() - i - 1; j++) {
                if (contactList.get(j).compareTo(contactList.get(j + 1)) > 0) {
                    Collections.swap(contactList, j, j + 1);
                }

                // if sorting takes a very long time return false
                if (System.currentTimeMillis() - start > timeLimit) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void execute() {
        System.out.println("Start searching (bubble sort + jump search)...");

        start = System.currentTimeMillis();
        boolean success = bubble(LinearSearch.searchTime * 10);
        long sortingTime = System.currentTimeMillis() - start;

        long searchingTime = System.currentTimeMillis();
        int found = 0;
        if (success) {
            for (Contact person : people) {
                found += Searching.jump(contactList, person) ? 1 : 0;
            }
        } else {
            new LinearSearch(contactList, people).linearSearch();
            found = LinearSearch.matchCounter;
        }

        SortingAlgorithm.printFullTime(found, people.size(), System.currentTimeMillis() - start,
                sortingTime, System.currentTimeMillis() - searchingTime, success);
    }
}
