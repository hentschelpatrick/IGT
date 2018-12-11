package de.igt.controllers;

import java.util.List;

public interface CRUD_Interface<T, E> {
    void create(T object);

    void create_demo(List<T> objects);

    void update(T object);

    void update(List<T> objects);

    void delete(T object);

    void deleteAll();

    T read(E Key);

    List<T> readAll();
}
