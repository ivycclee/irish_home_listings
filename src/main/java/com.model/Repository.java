package com.model;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    public List<T> getAll();

    public Optional<T> getByID(int id);

}
