package phonebook.Main;

import phonebook.FactoryPattern.SortingAlgorithm;
import phonebook.FactoryPattern.SortingAlgorithmFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String directory = "directory.txt";
    private static final String find = "find.txt";

    private static final List<Contact> phoneBook = new ArrayList<>();
    private static final List<Contact> people = new ArrayList<>();

    public static void main(String[] args) {
        loadFiles();

        // new factory
        SortingAlgorithmFactory factory = new SortingAlgorithmFactory(phoneBook, people);

        // linear search
        factory.setPhoneBook(initializePhoneBookTemp());
        SortingAlgorithm linearSearch = factory.getSortingAlgorithm("LinearSearch");
        linearSearch.execute();

        // bubble sort + jump search
        factory.setPhoneBook(initializePhoneBookTemp());
        SortingAlgorithm bubbleSort = factory.getSortingAlgorithm("BubbleSort");
        bubbleSort.execute();

        // quick sort + binary search
        factory.setPhoneBook(initializePhoneBookTemp());
        SortingAlgorithm quickSort = factory.getSortingAlgorithm("QuickSort");
        quickSort.execute();

        // hash table
        factory.setPhoneBook(initializePhoneBookTemp());
        SortingAlgorithm hashTable = factory.getSortingAlgorithm("HashTable");
        hashTable.execute();
    }

    private static List<Contact> initializePhoneBookTemp() {
        List<Contact> newPhoneBook = new ArrayList<>();

        for (Contact contact : phoneBook) {
            newPhoneBook.add(new Contact(contact.getNumber(), contact.getFullName()));
        }

        return newPhoneBook;
    }

    private static void loadFiles() {
        File file1 = new File(directory);
        File file2 = new File(find);

        Scanner scanner;
        try {
            // load all contacts
            scanner = new Scanner(file1);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split("\\s", 2);
                phoneBook.add(new Contact(line[0], line[1]));
            }

            // load contacts to find
            scanner = new Scanner(file2);
            while (scanner.hasNext()) {
                people.add(new Contact(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
