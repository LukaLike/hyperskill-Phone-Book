package phonebook.Main;

public class Contact {
    private String number;
    private final String fullName;

    public Contact(String fullName) {
        this.fullName = fullName;
    }

    public Contact(String number, String fullName) {
        this.number = number;
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public String getFullName() {
        return fullName;
    }

    public int compareTo(Contact contact) {
        return this.fullName.compareTo(contact.getFullName());
    }

    public boolean equals(Contact contact) {
        return this.getFullName().equals(contact.getFullName());
    }

    @Override
    public String toString() {
        return "Contact{" + number + " " + fullName + "}";
    }
}
