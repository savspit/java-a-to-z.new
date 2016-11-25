package ru.shestakov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStorage {
    private static final Logger Log = LoggerFactory.getLogger(UserStorage.class);

    private final Storage storage;

    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }
}
