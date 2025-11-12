package dao;

import connection.DatabaseConnection;
import models.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    public List<City> getAllCities(){
        List<City> allCities = new ArrayList<>();
        String query = "SELECT id, countryCode, district, id, name, population";

        try(Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery(); {
                while(rs.next()) {
                    allCities.add(new City(
                            rs.getString("countryCode"),
                            rs.getString("district"),
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("population")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCities;
    }

    public City getCityById() {

    }
}
