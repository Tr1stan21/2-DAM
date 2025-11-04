package fileManager;

import models.Actor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class that handles the writing of actors to a file
 */
public class ActorFileWriter {

    /**
     * Writes the list of actors to a CSV file named "actor.csv".
     *
     * @param actors list of Actor objects to be written
     */
    public void writeToCsv(List<Actor> actors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("actor.csv"))) {
            writer.write("actor_id;first_name;last_name;last_update\n");
            for(Actor actor : actors) {
                writer.write(actor.getActor_id() + ";" + actor.getFirst_name() + ";" + actor.getLast_name() + ";" + actor.getLast_update() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
