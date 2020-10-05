package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.ContactDao;
import ru.javawebinar.topjava.dao.HashMapMealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/meals")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final ContactDao contactDao = new HashMapMealDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("meal", contactDao.getById(id));
            request.getRequestDispatcher("updateMeal.jsp").forward(request, response);
        } else if (action != null && action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            contactDao.delete(id);
        }
        request.setAttribute("meals", MealsUtil.filteredByStreams(contactDao.getList(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to updateMeal");
        request.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime"), formatter),
                request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            meal.setId(id);
            contactDao.edit(meal);
        } catch (Exception e) {
            contactDao.save(meal);
        }

        response.sendRedirect("meals");
    }
}
