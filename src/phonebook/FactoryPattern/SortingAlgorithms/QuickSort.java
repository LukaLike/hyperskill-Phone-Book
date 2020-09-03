package phonebook.FactoryPattern.SortingAlgorithms;

import phonebook.Main.Contact;
import phonebook.FactoryPattern.SortingAlgorithm;
import phonebook.Main.Searching;

import java.util.Collections;
import java.util.List;

public class QuickSort implements SortingAlgorithm {

    private final List<Contact> contactList;
    private final List<Contact> people;
    private long start;

    public QuickSort(List<Contact> contactList, List<Contact> people) {
        this.contactList = contactList;
        this.people = people;
    }

    public boolean quick(int left, int right, long timeLimit) {
        if (left < right) {
            int pivotIndex = partition(left, right, start, timeLimit);

            if (pivotIndex == -1) {
                return false;
            }

            quick(left, pivotIndex - 1, timeLimit);
            quick(pivotIndex + 1, right, timeLimit);
        }
        return true;
    }

    private int partition(int left, int right, long start, long timeLimit) {
        Contact pivot = contactList.get(right);
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (contactList.get(i).compareTo(pivot) < 0) {
                Collections.swap(contactList, i, partitionIndex);
                partitionIndex++;
            }

            // if sorting takes a very long time return false
            if (System.currentTimeMillis() - start > timeLimit) {
                return -1;
            }
        }

        Collections.swap(contactList, partitionIndex, right);
        return partitionIndex;
    }

    @Override
    public void execute() {
        System.out.println("Start searching (quick sort + binary search)...");

        start = System.currentTimeMillis();
        boolean success = quick(0, contactList.size() - 1,LinearSearch.searchTime * 10);
        long sortingTime = System.currentTimeMillis() - start;

        long searchingTime = System.currentTimeMillis();
        int found = 0;
        if (success) {
            for (Contact person : people) {
                found += Searching.binary(contactList, person) ? 1 : 0;
            }
        } else {
            new LinearSearch(contactList, people).linearSearch();
            found = LinearSearch.matchCounter;
        }

        SortingAlgorithm.printFullTime(found, people.size(), System.currentTimeMillis() - start,
                sortingTime, System.currentTimeMillis() - searchingTime, success);
    }
}
