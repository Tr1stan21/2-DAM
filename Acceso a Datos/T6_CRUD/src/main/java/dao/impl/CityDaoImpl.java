package dao.impl;

import connection.DatabaseConnection;
import dao.CityDao;
import models.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao {

    private static final CityDaoImpl instance;

    static {
        instance = new CityDaoImpl();
    }

    public CityDaoImpl(){}

    public static CityDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void insert(City city) {
        String sql = "INSERT INTO city (countryCode, district, name, population) VALUES (?, ?, ?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, city.getCountryCode());
            ps.setString(2, city.getDistrict());
            ps.setString(3, city.getName());
            ps.setInt(4, city.getPopulation());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public City findById(int id) {
        String sql = "SELECT id, name, countryCode, district, population FROM city WHERE id = ?";

        try(Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new City(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("countryCode"),
                        rs.getString("district"),
                        rs.getInt("population")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        List<City> allCities = new ArrayList<>();
        String sql = "SELECT id, name, countryCode, district, population FROM city";

        try(Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery(); {
                while(rs.next()) {
                    allCities.add(new City(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("countryCode"),
                            rs.getString("district"),
                            rs.getInt("population")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCities;
    }

    @Override
    public void update(City city) {
        String sql = "UPDATE city SET name = ?, countryCode = ?, district = ?, population = ? WHERE id = ?";

        try(Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, city.getName());
            ps.setString(2, city.getCountryCode());
            ps.setString(3, city.getDistrict());
            ps.setInt(4, city.getPopulation());
            ps.setInt(5, city.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM city WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
