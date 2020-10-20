package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;
    public static final int NOT_FOUND = 1000;

    public static final Meal userMeal2 = new Meal(START_SEQ + 2, LocalDateTime.of(2020, Month.MAY, 20, 12, 30), "user Завтрак", 800);
    public static final Meal userMeal3 = new Meal(START_SEQ + 3, LocalDateTime.of(2020, Month.MAY, 20, 16, 40), "user Обед", 500);
    public static final Meal userMeal4 = new Meal(START_SEQ + 4, LocalDateTime.of(2020, Month.MAY, 20, 20, 20), "user Ужин", 500);
    public static final Meal userMeal5 = new Meal(START_SEQ + 5, LocalDateTime.of(2020, Month.MAY, 20, 0, 0), "user Еда на граничное значение", 300);
    public static final Meal userMeal6 = new Meal(START_SEQ + 6, LocalDateTime.of(2020, Month.MAY, 21, 8, 20), "user Завтрак", 800);
    public static final Meal userMeal7 = new Meal(START_SEQ + 7, LocalDateTime.of(2020, Month.MAY, 21, 12, 0), "user Обед", 1000);

    public static final Meal adminMeal8 = new Meal(START_SEQ + 8, LocalDateTime.of(2020, Month.MAY, 20, 9, 12), "admin Завтрак", 600);
    public static final Meal adminMeal9 = new Meal(START_SEQ + 9, LocalDateTime.of(2020, Month.MAY, 20, 15, 13), "admin Обед", 500);
    public static final Meal adminMeal10 = new Meal(START_SEQ + 10, LocalDateTime.of(2020, Month.MAY, 20, 18, 22), "admin Ужин", 900);

    public static final List<Meal> userMeals = Arrays.asList(
            userMeal7, userMeal6, userMeal4, userMeal3, userMeal2, userMeal5
    );

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 21, 0), "new", 410);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal2);
        updated.setDateTime(LocalDateTime.of(2020, Month.MAY, 23, 12, 0));
        updated.setDescription("UpdatedDescription");
        updated.setCalories(400);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
