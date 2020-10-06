package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapMealDao implements MealDao {
    private final Map<Integer, Meal> listOfMeal = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public HashMapMealDao(){
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal create(Meal meal) {
        meal.setId(count.getAndIncrement());
        listOfMeal.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        listOfMeal.remove(id);
    }

    @Override
    public Meal edit(Meal meal) {
        if(listOfMeal.containsKey(meal.getId())) {
            listOfMeal.put(meal.getId(), meal);
        }
        return meal;
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
