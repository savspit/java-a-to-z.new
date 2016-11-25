package ru.shestakov;

import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<User, Integer> {
}
