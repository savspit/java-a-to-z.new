package ru.shestakov;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Repository<T> {

    @Transactional
    T save(T model);

    List<T> getAll();
}
