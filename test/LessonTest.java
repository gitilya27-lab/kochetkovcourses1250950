import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование Lesson")
class LessonTest {

    private Lesson lesson;

    @BeforeEach
    void setUp() {
        lesson = new Lesson(3, 201, "Java Basics");
    }

    @Test
    @DisplayName("Доступность урока зависит от количества часов")
    void lessonAvailabilityDependsOnHours() {
        assertTrue(lesson.isAvailable());

        lesson.setHours(0);
        assertFalse(lesson.isAvailable());

        lesson.setHours(-1);
        assertFalse(lesson.isAvailable());
    }

    @Test
    @DisplayName("Конструктор и геттеры/сеттеры урока")
    void lessonConstructorAndAccessorsWork() {
        assertEquals(3, lesson.getHours());
        assertEquals(201, lesson.getId());
        assertEquals("Java Basics", lesson.getName());

        lesson.setHours(5);
        lesson.setId(202);
        lesson.setName("Advanced Java");

        Category category = new Category(1, "Programming");
        Teacher teacher = new Teacher("John", "555123", "Berlin");
        Course course = new Course(1, 10, 100, 0);

        lesson.setCategory(category);
        lesson.setTeacher(teacher);
        lesson.setCourse(course);

        assertEquals(5, lesson.getHours());
        assertEquals(202, lesson.getId());
        assertEquals("Advanced Java", lesson.getName());
        assertSame(category, lesson.getCategory());
        assertSame(teacher, lesson.getTeacher());
        assertSame(course, lesson.getCourse());
    }

    @Test
    @DisplayName("toString урока возвращает данные")
    void lessonToStringReturnsDescription() {
        assertTrue(lesson.toString().contains("Java Basics"));
    }
}
