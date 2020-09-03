package phonebook.FactoryPattern;

import phonebook.Main.Contact;
import phonebook.FactoryPattern.SortingAlgorithms.BubbleSort;
import phonebook.FactoryPattern.SortingAlgorithms.Hashtable;
import phonebook.FactoryPattern.SortingAlgorithms.LinearSearch;
import phonebook.FactoryPattern.SortingAlgorithms.QuickSort;

import java.util.List;

public class SortingAlgorithmFactory {
    List<Contact> contactList;
    List<Contact> people;

    public SortingAlgorithmFactory(List<Contact> contactList, List<Contact> people) {
        this.contactList = contactList;
        this.people = people;
    }

    public SortingAlgorithm getSortingAlgorithm(String sortingAlgorithm) {
        switch (sortingAlgorithm) {
            case "LinearSearch":
                return new LinearSearch(contactList, people);
            case "BubbleSort":
                return new BubbleSort(contactList, people);
            case "QuickSort":
                return new QuickSort(contactList, people);
            case "HashTable":
                return new Hashtable(contactList, people);
            default:
                return null;
        }
    }

    public void setPhoneBook(List<Contact> phoneBook) {
        this.contactList = phoneBook;
    }
}
