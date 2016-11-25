package ru.shestakov;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUserToStorageShouldSafeIt() {
        MemoryStorage memory = new MemoryStorage();
        UserStorage storage = new UserStorage(memory);
        storage.add(new User());
    }

    @Test
    public void whenLoadContextShouldGetBeans() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage memory = context.getBean(UserStorage.class);
        memory.add(new User());
        assertNotNull(memory);
    }
}