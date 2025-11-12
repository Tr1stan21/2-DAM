package models;

public enum Continent {
        // Cada constante tiene asociado el texto que se guarda en la base de datos
        ASIA("Asia"),
        EUROPE("Europe"),
        NORTH_AMERICA("North America"),
        AFRICA("Africa"),
        OCEANIA("Oceania"),
        ANTARCTICA("Antarctica"),
        SOUTH_AMERICA("South America");

        // Campo que guarda el valor usado en la base de datos
        private final String dbValue;

        // Asigna el valor de texto (dbValue) a cada constante
        Continent(String dbValue) {
            this.dbValue = dbValue;
        }
}
