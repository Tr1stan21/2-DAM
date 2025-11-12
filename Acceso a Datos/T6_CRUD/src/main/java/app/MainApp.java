package app;

import dao.impl.CityDaoImpl;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        CityDaoImpl cityDao = new CityDaoImpl();

        cityDao.update(cityDao.findById(4));
        System.out.println(cityDao.findById(4).toString());
    }
}
