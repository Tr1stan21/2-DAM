package main;

import dao.ActorDao;

public class MainApp {
    public static void main(String[] args) {

        ActorDao a = new ActorDao();
        String[][] actores = {
                {"Pepe", "Botella"},
                {"María", "González"},
                {"Luis", "Fernández"},
                {"Ana", "Martínez"},
                {"Carlos", "López"},
                {"Lucía", "Rodríguez"},
                {"Javier", "Sánchez"},
                {"Elena", "Díaz"},
                {"Antonio", "Ruiz"},
                {"Carmen", "Jiménez"},
                {"Miguel", "Moreno"},
                {"Laura", "Muñoz"},
                {"Sergio", "Álvarez"},
                {"Paula", "Romero"},
                {"Diego", "Navarro"},
                {"Raquel", "Torres"},
                {"Andrés", "Domínguez"},
                {"Isabel", "Vázquez"},
                {"Fernando", "Castro"},
                {"Patricia", "Ortega"}
        };
        a.insertActorsByBatches(actores, 4);
    }
}
