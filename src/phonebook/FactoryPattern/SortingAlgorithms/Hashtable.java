package phonebook.FactoryPattern.SortingAlgorithms;

import phonebook.Main.Contact;
import phonebook.FactoryPattern.SortingAlgorithm;

import java.util.List;

public class Hashtable<T> implements SortingAlgorithm {

    private final List<Contact> contactList;
    private final List<Contact> people;
    private long start;

    public Hashtable(List<Contact> contactList, List<Contact> people) {
        this.contactList = contactList;
        this.people = people;
    }

    @SuppressWarnings({"unchecked"})
    public HashTable<T> hash(long timeLimit) {
        HashTable<T> hashTable = new HashTable<>(contactList.size());

        for (Contact contact : contactList) {
            hashTable.put(HashTable.hash(contact.getFullName()), (T) contact);

            // if sorting takes a very long time return false
            if (System.currentTimeMillis() - start > timeLimit) {
                return null;
            }
        }

        return hashTable;
    }

    @SuppressWarnings({"unchecked"})
    private static class HashTable<T> {
        private final int size;
        private final TableEntry<T>[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public void put(int key, T value) {
            int idx = findKey(key);

            if (idx == -1) {
                return;
            }

            table[idx] = new TableEntry(key, value);
        }

        public T get(int key) {
            int idx = findKey(key);

            if (idx == -1 || table[idx] == null) {
                return null;
            }

            return table[idx].getValue();
        }

        public int findKey(int key) {
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        static int hash(String keyString) {
            int sum = 0;

            char[] key = keyString.toCharArray();
            for (char c : key) {
                sum += c;
            }

            sum += key[0] * key[key.length - 1];
            return sum;
        }
    }

    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }

    @Override
    public void execute() {
        System.out.println("Start searching (hash table)...");

        start = System.currentTimeMillis();
        HashTable<T> hashTable = hash(LinearSearch.searchTime * 10);
        long creatingTime = System.currentTimeMillis() - start;

        long searchingTime = System.currentTimeMillis();
        int found = 0;
        boolean success;
        if (hashTable != null) {
            success = true;
            for (Contact person : people) {
                found += hashTable.get(HashTable.hash(person.getFullName())) != null ? 1 : 0;
            }
        } else {
            success = false;
            new LinearSearch(contactList, people).linearSearch();
            found = LinearSearch.matchCounter;
        }

        printFullTime(found, people.size(), System.currentTimeMillis() - start, creatingTime,
                System.currentTimeMillis() - searchingTime, success);
    }

    static void printFullTime(int found, int total, long timeElapsed, long creatingTime, long searchingTime, boolean success) {
        SortingAlgorithm.printTime(found, total, timeElapsed);
        if (success) System.out.printf("Creating time: %s\n", SortingAlgorithm.millisecondsToTime(creatingTime));
        else System.out.printf("Creating time: %s - STOPPED, moved to linear search\n", SortingAlgorithm.millisecondsToTime(creatingTime));
        System.out.printf("Searching time: %s\n", SortingAlgorithm.millisecondsToTime(searchingTime) + "\n");
    }
}
