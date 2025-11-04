import dao.ActorDao;

import java.sql.Array;

public class MainApp {
    public static void main(String[] args) {
        ActorDao a = new ActorDao();

        String[][] actores = {
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},

        };
        a.addActorsBatch(actores);

        actores = new String[][]{
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},

        };
        a.addActorsBatch(actores);


        actores = new String[][]{
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},

        };
        a.addActorsBatch(actores);

        actores = new String[][]{
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},
                {"Pepe", "Botella"},

        };
        a.addActorsBatch(actores);

    }
}
