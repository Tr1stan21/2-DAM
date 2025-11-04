package app;

import dao.ActorDAO;
import fileManager.ActorFileWriter;

public class MainApp {
    public static void main(String[] args) {
        ActorDAO a = new ActorDAO();
        ActorFileWriter w = new ActorFileWriter();
        w.writeToCsv(a.getAllActors());

    }
}
