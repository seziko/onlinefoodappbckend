package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/meals")
public class MealResource {

    private final MealService mealService;

    @Autowired
    public MealResource(MealService mealService) {
        this.mealService = mealService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<Meal> getAllMeals(){
        return mealService.getAllMeals();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{code}")
    public Optional<Meal> getMeal(@PathVariable String code){
        return mealService.findByCode(code);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{code}")
    public ResponseEntity<Meal> updateMeal(@PathVariable String code,@RequestBody Meal meal){
        return new ResponseEntity<Meal>(mealService.save(meal), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal){
        Meal createdMeal = mealService.save(meal);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/meals/{code}")
                .buildAndExpand(createdMeal.getCode()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code){
        try{
            mealService.delete(code);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
