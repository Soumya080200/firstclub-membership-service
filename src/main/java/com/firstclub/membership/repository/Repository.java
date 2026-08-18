package com.firstclub.membership.repository;

import com.firstclub.membership.model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identifiable> {

    T save(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();
}
