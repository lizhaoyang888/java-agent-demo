package com.example.hello.controller;


import com.example.hello.bean.Car;
import com.example.hello.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {


    @Autowired
    private CarService carService;


    @GetMapping("/all")
    public List<Car> all(){
        return other();
    }


    public List<Car> other(){
        return carService.all();
    }
}
