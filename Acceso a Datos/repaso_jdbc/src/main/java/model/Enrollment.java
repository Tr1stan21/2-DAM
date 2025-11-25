package model;

import java.time.LocalDate;

public class Enrollment {

    private Integer id;            // id_matricula
    private Integer studentId;     // id_alumno (FK)
    private Integer courseId;      // id_curso (FK)
    private LocalDate enrolledAt;  // fecha_matricula
    private double grade;      // nota

    public Enrollment() {
    }

    public Enrollment(Integer id, Integer studentId, Integer courseId,
                      LocalDate enrolledAt, double grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
        this.grade = grade;
    }

    public Enrollment(Integer studentId, Integer courseId,
                      LocalDate enrolledAt, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDate enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrolledAt=" + enrolledAt +
                ", grade=" + grade +
                '}';
    }
}
