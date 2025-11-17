package dao;

import models.Country;

import java.util.List;

public interface CountryDao {
    void insert(Country country);
    Country findByCode(String code);
    List<Country> findAll();
    void update(Country country);
    void deleteByCode(String code);
}
