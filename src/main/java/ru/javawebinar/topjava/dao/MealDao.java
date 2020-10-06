package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    Meal create(Meal meal);

    void delete(int id);

    Meal edit(Meal meal);

    List<Meal> getList();

    Meal getById(int id);
}
