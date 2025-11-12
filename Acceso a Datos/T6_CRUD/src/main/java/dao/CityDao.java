package dao;

import models.City;
import java.util.List;

public interface CityDao {
    void addCity(City city);

    City getCityById(int id);

    List<City> getAllCities();

    void updateCity(City city);

    void deleteCity(int id);

}
