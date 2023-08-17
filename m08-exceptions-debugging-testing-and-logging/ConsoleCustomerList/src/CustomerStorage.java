import java.io.IOException;
import java.util.HashMap;

public class CustomerStorage {

    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        if (data == null || data.equals("")) {
            throw new IllegalArgumentException("Missing parameters");
        }
        String[] components = data.split("\\s+", 4);
        if (components.length < 4) {
            throw new IllegalArgumentException(String.format("Not enough parameters (%d of %d)",
                    components.length, 4));
        }

        // name and surname
        String name = components[0] + " " + components[1];

        // email
        if (!components[2].matches("^^\\w[\\w-.]*@[\\w-]+\\.[a-z]{2,4}$")) {
            throw new IllegalArgumentException("Wrong email");
        }

        // mobile phone number
        if (!components[3].matches("^(?<country>\\+7|8)?[\\s-]?\\(?[\\s-]?" +
                "(?<operator>\\d{3})[\\s-]?\\)?[\\s-]?(?<number>(?:\\d[\\s-]?){7})$")) {
            throw new IllegalArgumentException("Wrong mobile phone number");
        } else {
            components[3] = components[3].replaceAll("[\\s()-]", "");
        }

        storage.put(name, new Customer(name, components[2], components[3]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Missing name");
        }
        if (!storage.containsKey(name)) {
            throw new IllegalArgumentException("Non-existent name");
        }
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}
