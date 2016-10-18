package shestakov.models;

import shestakov.db.Console;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStorage {
    private Map<Long,Account> storage = new ConcurrentHashMap<Long,Account>();
    private static final Random RN = new Random();
    private static final Object extraLock = new Object();

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

    public void transfer(final Account donor, final Account recipient, final float sum) {
        int donorHash = System.identityHashCode(donor);
        int recipientHash = System.identityHashCode(recipient);
        if (donorHash < recipientHash) {
            synchronized (donor) {
                synchronized (recipient) {
                    doTransfer(donor, recipient, sum);
                }
            }
        } else if (donorHash > recipientHash) {
            synchronized (recipient) {
                synchronized (donor) {
                    doTransfer(donor, recipient, sum);
                }
            }
        } else {
            synchronized (extraLock) {
                synchronized (donor) {
                    synchronized (recipient) {
                        doTransfer(donor, recipient, sum);
                    }
                }
            }
        }
    }

    public void doTransfer(final Account donor, final Account recipient, final float sum) {
        if(donor.getAmount() >= sum) {
            donor.setAmount(donor.getAmount() - sum);
            recipient.setAmount(recipient.getAmount() + sum);
        } else {
            new Console().show(donor, "error occured. please try again later");
        }
    }
}
