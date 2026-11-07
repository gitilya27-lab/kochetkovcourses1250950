/**
 * Класс Lesson описывает отдельный учебный урок.
 * Содержит информацию о названии, продолжительности,
 * категории, курсе и преподавателе.
 */
public class Lesson {

    private int hours;
    private int id;
    private String name;

    private Category category;
    private Teacher teacher;
    private Course course;

    public Lesson() {
    }

    public Lesson(int hours, int id, String name) {
        this.hours = hours;
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Проверяет, доступен ли урок для проведения.
     * Урок считается доступным, если его продолжительность
     * больше нуля.
     *
     * @return true, если урок доступен, иначе false
     */
    public boolean isAvailable() {
        return hours > 0;
    }

    /**
     * Возвращает текстовое представление урока.
     *
     * @return информация об уроке
     */
    @Override
    public String toString() {
        return "Lesson{" +
                "hours=" + hours +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" +
                (category == null ? "none" : category.getId()) +
                ", teacher='" +
                (teacher == null ? "none" : teacher.getName()) +
                '\'' +
                ", courseId=" +
                (course == null ? "none" : course.getId()) +
                '}';
    }
}