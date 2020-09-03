package phonebook.Main;

import java.util.ArrayList;
import java.util.List;

public class Searching {
    public static boolean jump(List<Contact> phoneBook, Contact person) {
        int currentRight = 0;
        int prevRight = 0;

        if (phoneBook.isEmpty()) { return true; }
        if (phoneBook.get(0).equals(person)) { return true; }

        int jumpLength = (int) Math.sqrt(phoneBook.size());

        while (currentRight < phoneBook.size() - 1) {
            currentRight = Math.min(phoneBook.size() - 1, currentRight + jumpLength);

            if (phoneBook.get(currentRight).compareTo(person) >= 0) {
                return jump(new ArrayList<>(phoneBook.subList(prevRight + 1, currentRight + 1)), person);
            }

            prevRight = currentRight;
        }
        return false;
    }

    public static boolean binary(List<Contact> phoneBook, Contact person) {
        int left = 0;
        int right = phoneBook.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (person.equals(phoneBook.get(mid))) {
                return true;
            } else if (person.compareTo(phoneBook.get(mid)) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
