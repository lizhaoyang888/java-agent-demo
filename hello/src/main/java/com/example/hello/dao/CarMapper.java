package com.example.hello.dao;

import com.example.hello.bean.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CarMapper {

    public List<Car> all(){
        List<Car> cars = new ArrayList<>(10);
        for (int i=0;i<10;i++){
            Car car = new Car();
            car.setName(UUID.randomUUID().toString()+"car");
            cars.add(car);
        }
        return cars;
    }
}
