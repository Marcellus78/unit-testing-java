package com.marcellus.testing.meal;

import com.marcellus.testing.meal.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    public void add(Meal meal) {
        meals.add(meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void delete(Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> findByName(String mealName, boolean exactMactch) {

        List<Meal> result;

        if(exactMactch) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(mealName))
                    .collect(Collectors.toList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(mealName))
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<Meal> findByPrice(int price) {
        return meals.stream()
                .filter(meal -> meal.getPrice() == price)
                .collect(Collectors.toList());
    }
}
