package dao.impl;

import connection.DatabaseConnection;
import dao.CountryDao;
import models.Continent;
import models.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    private static final CountryDaoImpl instance;

    static {
        instance = new CountryDaoImpl();
    }

    public CountryDaoImpl(){}

    public static CountryDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void insert(Country country) {
        String sql = "INSERT INTO country (code, name, continent, region, surfaceArea, population, localName, governmentForm, code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, country.getCode());
            ps.setString(2, country.getName());
            ps.setString(3, country.getContinent().getDbValue());
            ps.setString(4, country.getRegion());
            ps.setDouble(5, country.getSurfaceArea());
            ps.setInt(6, country.getPopulation());
            ps.setString(7, country.getLocalName());
            ps.setString(8, country.getGovernmentForm());
            ps.setString(9, country.getCode2());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Country findByCode(String code) {
        String sql = "SELECT code, name, continent, region, " +
                "surfaceArea, population, localName, governmentForm, code2 " +
                "FROM country WHERE code = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Country(
                        rs.getString("code"),
                        rs.getString("name"),
                        Continent.fromDbValue(rs.getString("continent")),
                        rs.getString("region"),
                        rs.getDouble("surfaceArea"),
                        rs.getInt("population"),
                        rs.getString("localName"),
                        rs.getString("governmentForm"),
                        rs.getString("code2")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Country> findAll() {
        List<Country> allCountries = new ArrayList<>();
        String sql = "SELECT code, name, continent, region, " +
                "surfaceArea, population, localName, governmentForm, code2 " +
                "FROM country";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allCountries.add(new Country(
                        rs.getString("code"),
                        rs.getString("name"),
                        Continent.fromDbValue(rs.getString("continent")),
                        rs.getString("region"),
                        rs.getDouble("surfaceArea"),
                        rs.getInt("population"),
                        rs.getString("localName"),
                        rs.getString("governmentForm"),
                        rs.getString("code2")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allCountries;
    }

    @Override
    public void update(Country country) {
        String sql = "UPDATE country SET " +
                "name = ?, continent = ?, region = ?, " +
                "surfaceArea = ?, population = ?, localName = ?, " +
                "governmentForm = ?, code2 = ? " +
                "WHERE code = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, country.getName());
            ps.setString(2, country.getContinent().getDbValue());
            ps.setString(3, country.getRegion());
            ps.setDouble(4, country.getSurfaceArea());
            ps.setInt(5, country.getPopulation());
            ps.setString(6, country.getLocalName());
            ps.setString(7, country.getGovernmentForm());
            ps.setString(8, country.getCode2());
            ps.setString(9, country.getCode());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByCode(String code) {
        String sql = "DELETE FROM country WHERE code = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
