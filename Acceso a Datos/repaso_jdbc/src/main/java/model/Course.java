package model;

public class Course {

    private Integer id;       // id_curso
    private String name;      // nombre
    private String description; // descripcion
    private Integer hours;    // horas

    public Course() {
    }

    public Course(Integer id, String name, String description, Integer hours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hours = hours;
    }

    public Course(String name, String description, Integer hours) {
        this.name = name;
        this.description = description;
        this.hours = hours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hours=" + hours +
                '}';
    }
}
