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
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);


    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        Map<Integer,Meal> meals = repository.computeIfAbsent(userId, m -> new HashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(),meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return meals.computeIfPresent(meal.getId(), (id, m) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals.get(id) : null;
    }

    public List<Meal> getFiltered(Predicate<Meal> filter, int userId) {
        log.info("getFiltered with userId {}", userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals
                .values()
                .stream()
                .filter(filter)
                .sorted(Collections.reverseOrder(Comparator.comparing(Meal::getDateTime)))
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    @Override
    public List<Meal> getFilteredList(LocalDate startDate, LocalDate endDate, int userId) {
        log.info("getFilteredList with userId {}", userId);
        return getFiltered(meal -> DateTimeUtil.isBetweenDays(meal.getDate(), startDate, endDate), userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll with userId {}", userId);
        return getFiltered(meal -> true, userId);
    }
}

