package ru.shestakov;

import org.springframework.stereotype.Component;

@Component
public class MemoryStorage implements Storage {
    @Override
    public void add(User user) {
        System.out.println("Store to memory");
    }
}
