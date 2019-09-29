package com.bilgeadam.onlinefoodapp.service;

import com.bilgeadam.onlinefoodapp.domain.Employee;
import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.repo.EmployeeRepository;
import com.bilgeadam.onlinefoodapp.repo.MealRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public MealService(MealRepository mealRepository,EmployeeRepository employeeRepository) {
        this.mealRepository = mealRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public void delete(String code) {
        mealRepository.deleteById(code);
    }

    public Optional<Meal> findByCode(String code){
        return mealRepository.findById(code);
    }

    public Meal save(Meal meal){
        Optional<Employee> employee = employeeRepository.findById(1L);
        employee.ifPresent(meal::setEmployee);
        return mealRepository.save(meal);
    }
}
