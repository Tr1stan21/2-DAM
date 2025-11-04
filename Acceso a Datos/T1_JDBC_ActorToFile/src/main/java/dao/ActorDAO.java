package dao;

import connection.DatabaseConnection;
import models.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for retrieving Actor data from the database.
 */
public class ActorDAO {

    /**
     * Retrieves all actors from the database.
     *
     * @return a list containing all Actor objects found in the "actor" table
     */
    public List<Actor> getAllActors() {
        ArrayList<Actor> allActors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name, last_update from actor";

        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();{

                while (result.next()) {
                    allActors.add(new Actor(
                            result.getInt("actor_id"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getTimestamp("last_update")
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Error: Ha habido un error en la base de datos");
        }

        return allActors;
    }
}
