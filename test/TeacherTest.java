
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование Teacher")
class TeacherTest {

    private Teacher teacher;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        teacher = new Teacher("John", "555123", "Berlin");
        lesson = new Lesson(3, 201, "Java Basics");
    }

    @Test
    @DisplayName("Конструктор и геттеры/сеттеры преподавателя")
    void teacherConstructorAndAccessorsWork() {
        assertEquals("John", teacher.getName());
        assertEquals("555123", teacher.getPhone());
        assertEquals("Berlin", teacher.getAddress());

        teacher.setName("Mike");
        teacher.setPhone("111");
        teacher.setAddress("Paris");

        assertEquals("Mike", teacher.getName());
        assertEquals("111", teacher.getPhone());
        assertEquals("Paris", teacher.getAddress());
    }

    @Test
    @DisplayName("teachLesson с уроком назначает преподавателя")
    void teachLessonAssignsTeacher() {
        Lesson result = teacher.teachLesson(lesson);

        assertSame(lesson, result);
        assertTrue(teacher.getLessons().contains(lesson));
        assertSame(teacher, lesson.getTeacher());
    }

    @Test
    @DisplayName("teachLesson без аргументов возвращает первый урок или null")
    void teachLessonWithoutArgumentReturnsFirstLesson() {
        assertNull(teacher.teachLesson());

        teacher.teachLesson(lesson);
        assertSame(lesson, teacher.teachLesson());
    }

    @Test
    @DisplayName("teachLesson игнорирует null и не дублирует урок")
    void teachLessonIgnoresNullAndDuplicates() {
        assertNull(teacher.teachLesson((Lesson) null));

        teacher.teachLesson(lesson);
        teacher.teachLesson(lesson);

        assertEquals(1, teacher.getLessons().size());
    }

    @Test
    @DisplayName("setLessons преподавателя обновляет связи и обрабатывает null")
    void teacherSetLessonsUpdatesRelations() {
        Lesson secondLesson = new Lesson(1, 202, "OOP");
        teacher.setLessons(Arrays.asList(lesson, secondLesson));

        assertEquals(2, teacher.getLessons().size());
        assertSame(teacher, lesson.getTeacher());
        assertSame(teacher, secondLesson.getTeacher());

        teacher.setLessons(null);
        assertTrue(teacher.getLessons().isEmpty());
    }

    @Test
    @DisplayName("toString преподавателя возвращает данные")
    void teacherToStringReturnsDescription() {
        assertTrue(teacher.toString().contains("John"));
    }
}
