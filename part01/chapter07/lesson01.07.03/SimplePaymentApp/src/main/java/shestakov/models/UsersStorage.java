package shestakov.models;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class UsersStorage {
    private Map<Long,User> storage = new ConcurrentHashMap<Long,User>();
    private static final Random RN = new Random();

    public User get(long id) {
        return this.storage.get(id);
    }

    public void add(User user) {
        user.setId(this.generateId());
        this.storage.put(user.getId(),user);
    }

    public void edit(User user) {
        this.storage.put(user.getId(), user);
    }

    public void remove(User user) {
        this.storage.remove(user.getId());
    }

    public long generateId() {
        return System.currentTimeMillis() + RN.nextInt();
    }

    public void transfer(User donor, User recipient, float sum) {
        new TransferThread(this.storage.get(donor.getId()), this.storage.get(recipient.getId()), sum).start();
    }

    public int size() {
        return storage.size();
    }

}
