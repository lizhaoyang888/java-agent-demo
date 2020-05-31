package com.example.hello.service.impl;

import com.example.hello.bean.Car;
import com.example.hello.dao.CarMapper;
import com.example.hello.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> all() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carMapper.all();
    }

}
