package dao;

import models.City;

import java.util.List;

public interface CityDao {
    void insert(City city);
    City findById(int id);
    List<City> findAll();
    void update(City city);
    void deleteById(int id);
}
