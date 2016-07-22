package shestakov.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AccountStorageTest {

    @Test
    public void whenAddAccountShouldBeAdded() {
        AccountStorage storage = new AccountStorage();
        Account account = new Account(new User("Ivanov"));
        storage.add(account);
        assertThat(account, is(storage.get(account.getId())));
    }

    @Test
    public void whenGetAccountShouldBeDone() {
        AccountStorage storage = new AccountStorage();
        Account account = new Account(new User("Ivanov"));
        storage.add(account);
        assertThat(account.getAmount(), is(storage.get(account.getId()).getAmount()));
    }

    @Test
    public void whenEditAccountShouldBeEdited() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        Account account2 = new Account(new User("Petrov"));
        Account account3 = new Account(new User("Sidorov"));
        storage.add(account1);
        storage.add(account2);
        account3.setId(account1.getId());
        storage.edit(account3);
        assertThat(account3, is(storage.get(account1.getId())));
        assertThat(storage.size(), is(2));
    }

    @Test
    public void whenRemoveAccountShouldBeRemoved() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        Account account2 = new Account(new User("Petrov"));
        storage.add(account1);
        storage.add(account2);
        storage.remove(account1);
        assertThat(storage.size(), is(1));
    }

    @Test
    public void whenDoingPlusTransferShouldBeDoneCorrect() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        account1.setAmount(2000f);
        storage.add(account1);
        new TransactionThread(storage, account1.getId(), 1000f).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(3000f));
    }

    @Test
    public void whenDoingMinusTransferShouldBeDoneCorrect() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        account1.setAmount(2000f);
        storage.add(account1);
        new TransactionThread(storage, account1.getId(), -1000f).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(1000f));
    }

    @Test
    public void whenWrongDoubleTransferShouldDoingOnlyOneTransfer() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        account1.setAmount(2000f);
        storage.add(account1);
        new TransactionThread(storage, account1.getId(), -1500f).start();
        new TransactionThread(storage, account1.getId(), -1500f).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(500f));
    }

    @Test
    public void whenEnouthMoneyShouldDoingAllTransfers() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        account1.setAmount(2000f);
        storage.add(account1);
        new TransactionThread(storage, account1.getId(), 1000f).start();
        new TransactionThread(storage, account1.getId(), -2000f).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(1000f));
    }

    @Test
    public void whenUseJoinShouldDoingCorrectTransfers() throws InterruptedException {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        account1.setAmount(2000f);
        storage.add(account1);
        TransactionThread tt1 = new TransactionThread(storage, account1.getId(), -1000f); // correct
        tt1.start();
        tt1.join();
        TransactionThread tt2 = new TransactionThread(storage, account1.getId(), -2000f); // error
        tt2.start();
        tt2.join();
        TransactionThread tt3 = new TransactionThread(storage, account1.getId(), 5000f); // correct
        tt3.start();
        tt3.join();
        TransactionThread tt4 = new TransactionThread(storage, account1.getId(), -6000f);
        tt4.start();
        tt4.join();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(0f));
    }

}