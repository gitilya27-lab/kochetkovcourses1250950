import java.util.ArrayList;
import java.util.List;

/**
 * Класс Teacher описывает преподавателя.
 * Преподаватель может проводить несколько уроков.
 */
public class Teacher {

    private String name;
    private String phone;
    private String address;

    private List<Lesson> lessons;

    public Teacher() {
        this.lessons = new ArrayList<>();
    }

    public Teacher(String name, String phone, String address) {
        this();
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons == null ?
                new ArrayList<>() : new ArrayList<>(lessons);

        for (Lesson lesson : this.lessons) {
            if (lesson != null) {
                lesson.setTeacher(this);
            }
        }
    }

    /**
     * Возвращает первый урок из списка уроков преподавателя.
     *
     * @return первый урок или null, если список пуст
     */
    public Lesson teachLesson() {
        if (lessons.isEmpty()) {
            return null;
        }

        return lessons.get(0);
    }

    /**
     * Назначает преподавателя для указанного урока
     * и добавляет урок в список его занятий.
     *
     * @param lesson урок, который будет проводить преподаватель
     * @return назначенный урок или null, если урок не указан
     */
    public Lesson teachLesson(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        if (!lessons.contains(lesson)) {
            lessons.add(lesson);
        }

        lesson.setTeacher(this);

        return lesson;
    }

    /**
     * Возвращает текстовое представление преподавателя.
     *
     * @return информация о преподавателе
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", lessonsCount=" + lessons.size() +
                '}';
    }
}