package app;

import dao.impl.CityDaoImpl;
import dao.impl.CountryDaoImpl;
import models.City;
import models.Continent;
import models.Country;

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
        cityDao.update(testCity);
        System.out.println(cityDao.findById(7).toString());
        cityDao.deleteById(7);
        System.out.println("Ciudad 4 eliminada");


        CountryDaoImpl countryDao = new CountryDaoImpl();

        // 1. INSERT
        Country testCountry = new Country(
                "XXX",                   // code (PK temporal que no exista en la BD)
                "Testland",              // name
                Continent.EUROPE,        // ajusta al enum que tengas
                "Test Region",           // region
                123.45,                  // surfaceArea
                123456,                  // population
                "Test Local Name",       // localName
                "Test Government",       // governmentForm
                "TX"                     // code2
        );

        countryDao.insert(testCountry);
        System.out.println("País insertado");

        // 2. FIND ALL
        System.out.println("Todos los países:");
        for (Country c : countryDao.findAll()) {
            System.out.println("  - " + c.toString());
        }

        // 3. UPDATE
        System.out.println("Cambiar país XXX");
        Country countryToUpdate = countryDao.findByCode("XXX");
        countryToUpdate.setName("Testlandia");
        countryToUpdate.setPopulation(999999);
        countryDao.update(countryToUpdate);

        System.out.println(countryDao.findByCode("XXX").toString());

        // 4. DELETE
        countryDao.deleteByCode("XXX");
        System.out.println("País XXX eliminado");
    }
}
