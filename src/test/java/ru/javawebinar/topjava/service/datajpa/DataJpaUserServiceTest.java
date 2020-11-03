package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserAbstractServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserAbstractServiceTest {
    @Test
    public void getWithNotFoundUser() {
        assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }

    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(USER_ID);
        List<Meal> userMeal = user.getMeals();
        USER_MATCHER.assertMatch(user, UserTestData.user);
        MEAL_MATCHER.assertMatch(userMeal, MealTestData.meals);
    }

    @Test
    public void getWithEmptyList() {
        User user = service.getWithMeals(UserTestData.USER_WITHOUT_MEALS);
        List<Meal> userMeal = user.getMeals();
        USER_MATCHER.assertMatch(user, UserTestData.userWithoutMeals);
        MEAL_MATCHER.assertMatch(userMeal, MealTestData.emptyList);
    }
}
