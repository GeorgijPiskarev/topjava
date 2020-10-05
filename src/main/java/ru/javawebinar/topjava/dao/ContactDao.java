package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface ContactDao {
    void save(Meal meal);

    void delete(int id);

    void edit(Meal meal);

    List<Meal> getList();

    Meal getById(int id);
}
