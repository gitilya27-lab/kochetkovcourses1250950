import java.util.ArrayList;
import java.util.List;

/**
 * Класс Category описывает категорию уроков.
 * Каждая категория может содержать несколько уроков.
 */
public class Category {

    private int id;
    private String name;
    private List<Lesson> lessons;

    public Category() {
        this.lessons = new ArrayList<>();
    }

    public Category(int id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры

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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons == null ?
                new ArrayList<>() : new ArrayList<>(lessons);

        for (Lesson lesson : this.lessons) {
            if (lesson != null) {
                lesson.setCategory(this);
            }
        }
    }

    /**
     * Добавляет урок в категорию.
     *
     * @param lesson урок, который необходимо добавить
     */
    public void addLesson(Lesson lesson) {
        if (lesson != null && !lessons.contains(lesson)) {
            lessons.add(lesson);
            lesson.setCategory(this);
        }
    }

    /**
     * Возвращает текстовое представление категории.
     *
     * @return информация о категории
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lessonsCount=" + lessons.size() +
                '}';
    }
}