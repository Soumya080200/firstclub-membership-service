package com.firstclub.membership.repository;

import com.firstclub.membership.model.Identifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryRepository<T extends Identifiable> implements Repository<T> {

    private final Map<Long, T> store = new HashMap<>();
    private long idCounter = 0;

    @Override
    public synchronized T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(++idCounter);
        }
        store.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public synchronized Optional<T> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public synchronized List<T> findAll() {
        return new ArrayList<>(store.values());
    }
}
