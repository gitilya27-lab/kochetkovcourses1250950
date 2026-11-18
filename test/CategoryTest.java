
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование Category")
class CategoryTest {

    private Category category;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        category = new Category(1, "Programming");
        lesson = new Lesson(3, 201, "Java Basics");
    }

    @Test
    @DisplayName("Конструктор и геттеры/сеттеры категории")
    void categoryConstructorAndAccessorsWork() {
        assertEquals(1, category.getId());
        assertEquals("Programming", category.getName());

        category.setId(2);
        category.setName("IT");

        assertEquals(2, category.getId());
        assertEquals("IT", category.getName());
        assertNotNull(new Category().getLessons());
    }

    @Test
    @DisplayName("Категория добавляет урок и устанавливает категорию уроку")
    void categoryAddLessonCreatesRelation() {
        category.addLesson(lesson);

        assertTrue(category.getLessons().contains(lesson));
        assertSame(category, lesson.getCategory());
    }

    @Test
    @DisplayName("Категория игнорирует null и повторный урок")
    void categoryIgnoresNullAndDuplicates() {
        category.addLesson(lesson);
        category.addLesson(lesson);
        category.addLesson(null);

        assertEquals(1, category.getLessons().size());
    }

    @Test
    @DisplayName("setLessons категории обновляет связи")
    void categorySetLessonsUpdatesRelations() {
        Lesson secondLesson = new Lesson(2, 202, "OOP");
        category.setLessons(Arrays.asList(lesson, secondLesson));

        assertEquals(2, category.getLessons().size());
        assertSame(category, lesson.getCategory());
        assertSame(category, secondLesson.getCategory());

        category.setLessons(null);
        assertTrue(category.getLessons().isEmpty());
    }

    @Test
    @DisplayName("toString категории возвращает данные")
    void categoryToStringReturnsDescription() {
        assertTrue(category.toString().contains("Programming"));
    }
}
