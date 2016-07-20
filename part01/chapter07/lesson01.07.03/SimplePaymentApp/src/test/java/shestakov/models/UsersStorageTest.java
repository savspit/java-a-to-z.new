package shestakov.models;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UsersStorageTest {

    @Test
    public void whenAddUserShouldBeAdded() {
        UsersStorage storage = new UsersStorage();
        User user = new User("Ivanov", 2000);
        storage.add(user);
        assertThat(user, is(storage.get(user.getId())));
    }

    @Test
    public void whenGetUserShouldBeDone() {
        UsersStorage storage = new UsersStorage();
        User user = new User("Ivanov", 2000);
        storage.add(user);
        assertThat(user.getAmount(), is(storage.get(user.getId()).getAmount()));
    }

    @Test
    public void whenEditUserShouldBeEdited() {
        UsersStorage storage = new UsersStorage();
        User user1 = new User("Ivanov", 2000);
        User user2 = new User("Petrov", 1500);
        storage.add(user1);
        storage.add(user2);
        User user3 = new User("Sidorov", 3000);
        user3.setId(user1.getId());
        storage.edit(user3);
        assertThat(user3, is(storage.get(user1.getId())));
        assertThat(storage.size(), is(2));
    }

    @Test
    public void whenRemoveUserShouldBeRemoved() {
        UsersStorage storage = new UsersStorage();
        User user1 = new User("Ivanov", 2000);
        User user2 = new User("Petrov", 1500);
        storage.add(user1);
        storage.add(user2);
        storage.remove(user1);
        assertThat(storage.size(), is(1));
    }

    @Test
    public void whenDoubleTransferShouldDoingFirstTransfer() {
        UsersStorage storage = new UsersStorage();
        User user1 = new User("Ivanov", 2000);
        User user2 = new User("Petrov", 1500);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(user1,user2,1000);
        storage.transfer(user1,user2,500);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(storage.get(user1.getId()).getAmount(), is(500f));
        assertThat(storage.get(user2.getId()).getAmount(), is(3000f));
    }

    @Test
    public void whenNotEnouthMoneyShouldDoingOnlyOneTransfer() {
        UsersStorage storage = new UsersStorage();
        User user1 = new User("Ivanov", 2000);
        User user2 = new User("Petrov", 1500);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(user1,user2,2000);
        storage.transfer(user1,user2,500);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(storage.get(user1.getId()).getAmount()>=0);
    }

}