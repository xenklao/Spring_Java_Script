package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> carList(Integer count) {


//        List<Car> carList;
//        {
//            carList = new ArrayList<>();
//            carList.add(new Car("Mazda", "6", 2011));
//            carList.add(new Car("KIA", "Sorento", 2020));
//            carList.add(new Car("Honda", "Accord", 2014));
//            carList.add(new Car("BMW", "6", 2012));
//            carList.add(new Car("Mazda", "3", 2011));
//        }
//        return carList.stream().limit(count).collect(Collectors.toList());
        return carDao.getCars().stream().limit(count).collect(Collectors.toList());
    }
}