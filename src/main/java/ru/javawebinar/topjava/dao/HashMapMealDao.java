package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapMealDao implements ContactDao {
    private static final Map<Integer, Meal> listOfMeal = new ConcurrentHashMap<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    static {
        Meal meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        meal.setId(count.getAndIncrement());
        listOfMeal.put(meal.getId(), meal);
        Meal meal2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        meal2.setId(count.getAndIncrement());
        listOfMeal.put(meal2.getId(), meal2);
        Meal meal3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        meal3.setId(count.getAndIncrement());
        listOfMeal.put(meal3.getId(), meal3);
        Meal meal4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        meal4.setId(count.getAndIncrement());
        listOfMeal.put(meal4.getId(), meal4);
        Meal meal5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        meal5.setId(count.getAndIncrement());
        listOfMeal.put(meal5.getId(), meal5);
        Meal meal6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        meal6.setId(count.getAndIncrement());
        listOfMeal.put(meal6.getId(), meal6);
        Meal meal7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
        meal7.setId(count.getAndIncrement());
        listOfMeal.put(meal7.getId(), meal7);
    }

    @Override
    public void save(Meal meal) {
        meal.setId(count.getAndIncrement());
        listOfMeal.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        listOfMeal.remove(id);
    }

    @Override
    public void edit(Meal meal) {
        listOfMeal.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getList() {
        return new ArrayList<>(listOfMeal.values());
    }

    @Override
    public Meal getById(int id) {
        return listOfMeal.get(id);
    }
}
