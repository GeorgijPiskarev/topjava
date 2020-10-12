package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);


    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.computeIfAbsent(userId, m -> new HashMap<>()).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (isExists(meal.getId(), userId)) {
            return repository.get(userId).put(meal.getId(), meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        if (isExists(id, userId)) {
            repository.get(userId).remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        if (isExists(id, userId)) {
            return repository.get(userId).get(id);
        }
        return null;
    }

    @Override
    public List<Meal> getFilteredList(LocalDate startDate, LocalDate endDate, int userId) {
        return getAll(userId)
                .stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll with userId {}", userId);
        List<Meal> meals = new ArrayList<>();
        if (repository.get(userId) != null) {
            meals = new ArrayList<>(repository.get(userId).values());
            meals.sort(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime)));
        }
        return meals;
    }

    private boolean isExists(int id, int userId) {
        Map<Integer, Meal> mealMap = repository.get(userId);
        return mealMap != null && mealMap.get(id) != null;
    }
}

