package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.HashMapMealDao;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao mealDao;

    @Override
    public void init() throws ServletException {
        super.init();
        mealDao = new HashMapMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case ("edit"):
                request.setAttribute("meal", mealDao.getById(MealsUtil.convertToNumber(request,"id")));
                request.getRequestDispatcher("updateMeal.jsp").forward(request, response);
                break;
            case ("delete"):
                log.debug("delete meal");
                mealDao.delete(MealsUtil.convertToNumber(request,"id"));
                response.sendRedirect("meals");
                return;
            case ("add"):
                request.getRequestDispatcher("updateMeal.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getList(), LocalTime.MIN,
                        LocalTime.MAX, MealsUtil.NUMBER_OF_CALORIES));
                request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("redirect to updateMeal");
        request.setCharacterEncoding("UTF-8");

        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));

        if(!request.getParameter("id").isEmpty()){
            log.debug("edit meal");
            meal.setId(MealsUtil.convertToNumber(request,"id"));
            mealDao.edit(meal);
        }else {
            log.debug("create meal");
            mealDao.create(meal);
        }

        response.sendRedirect("meals");
    }
}
