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
    public void whenWrongDoubleTransferShouldDoingOnlyOneTransfer() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        Account account2 = new Account(new User("Petrov"));
        account1.setAmount(2000f);
        account2.setAmount(0f);
        storage.add(account1);
        storage.add(account2);
        storage.transfer(account1, account2, 1500f);
        storage.transfer(account1, account2, 1500f);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(500f));
        assertThat(storage.get(account2.getId()).getAmount(), is(1500f));
    }

    @Test
    public void whenEnouthMoneyShouldDoingAllTransfers() {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        Account account2 = new Account(new User("Petrov"));
        account1.setAmount(2000f);
        account2.setAmount(0f);
        storage.add(account1);
        storage.add(account2);
        storage.transfer(account1, account2, 1000);
        storage.transfer(account1, account2, 1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(0f));
        assertThat(storage.get(account2.getId()).getAmount(), is(2000f));
    }

    @Test
    public void whenUseJoinShouldDoingCorrectTransfers() throws InterruptedException {
        AccountStorage storage = new AccountStorage();
        Account account1 = new Account(new User("Ivanov"));
        Account account2 = new Account(new User("Petrov"));
        Account account3 = new Account(new User("Sidorov"));
        account1.setAmount(2000f);
        account2.setAmount(0f);
        account3.setAmount(10000f);
        storage.add(account1);
        storage.add(account2);
        storage.add(account3);
        storage.transfer(account1, account2, 500);
        storage.transfer(account3, account1, 5000);
        storage.transfer(account1, account2, 1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(account1.getId()).getAmount(), is(5500f));
        assertThat(storage.get(account2.getId()).getAmount(), is(1500f));
        assertThat(storage.get(account3.getId()).getAmount(), is(5000f));
    }

}