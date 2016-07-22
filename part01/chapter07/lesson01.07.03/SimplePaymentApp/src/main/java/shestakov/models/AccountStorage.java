package shestakov.models;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStorage {
    private Map<Long,Account> storage = new ConcurrentHashMap<Long,Account>();
    private static final Random RN = new Random();

    public Account get(long id) {
        return this.storage.get(id);
    }

    public void add(Account account) {
        account.setId(this.generateId());
        this.storage.put(account.getId(),account);
    }

    public void edit(Account account) {
        this.storage.put(account.getId(), account);
    }

    public void remove(Account account) {
        this.storage.remove(account.getId());
    }

    public long generateId() {
        return System.currentTimeMillis() + RN.nextInt();
    }

    public int size() {
        return storage.size();
    }

    public void transfer(Account donor, Account recipient, float sum) {
        new TransactionThread(donor, recipient, sum).start();

    }

}
