package app;

import dao.impl.CityDaoImpl;
import models.City;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        CityDaoImpl cityDao = new CityDaoImpl();
        City testCity = new City(4, "Test","ESP","Test District",  123456);

        cityDao.insert(testCity);
        System.out.println("Ciudad insertada");
        System.out.println("Todas las ciudades:");
        for (City c : cityDao.findAll()) {
            System.out.println("  - "+ c.toString());
        }
        System.out.println("Cambiar ciudad 4");
        cityDao.update(cityDao.findById(4));
        System.out.println(cityDao.findById(4).toString());
    }
}
