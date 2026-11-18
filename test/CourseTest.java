import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Course")
class CourseTest {

    private Course course;
    private Lesson lesson;

    @BeforeEach
    void setUp() {
        course = new Course(2, 101, 1000.0, 10.0);
        lesson = new Lesson(2, 201, "Java Basics");
    }

    @Test
    @DisplayName("Конструктор и геттеры/сеттеры курса")
    void constructorAndAccessorsWork() {
        assertEquals(2, course.getQuantity());
        assertEquals(101, course.getId());
        assertEquals(1000.0, course.getPrice(), 0.001);
        assertEquals(10.0, course.getDiscount(), 0.001);

        course.setQuantity(3);
        course.setId(202);
        course.setPrice(1500.0);
        course.setDiscount(15.0);

        assertEquals(3, course.getQuantity());
        assertEquals(202, course.getId());
        assertEquals(1500.0, course.getPrice(), 0.001);
        assertEquals(15.0, course.getDiscount(), 0.001);
    }

    @Test
    @DisplayName("Цена одного курса рассчитывается с учётом скидки")
    void calculateDiscountedPriceAppliesDiscount() {
        assertEquals(900.0, course.calculateDiscountedPrice(), 0.001);
    }

    @Test
    @DisplayName("Общая стоимость учитывает количество и скидку")
    void calculateTotalPriceUsesQuantityAndDiscount() {
        assertEquals(1800.0, course.calculateTotalPrice(), 0.001);
    }

    @Test
    @DisplayName("Скидка 100 процентов делает стоимость нулевой")
    void fullDiscountMakesPriceZero() {
        course.setDiscount(100.0);
        assertEquals(0.0, course.calculateDiscountedPrice(), 0.001);
        assertEquals(0.0, course.calculateTotalPrice(), 0.001);
    }

    @Test
    @DisplayName("Установка некорректной скидки вызывает исключение")
    void invalidDiscountThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> course.setDiscount(101.0));
        assertThrows(IllegalArgumentException.class,
                () -> course.setDiscount(-1.0));
    }

    @Test
    @DisplayName("Курс добавляет урок и устанавливает обратную связь")
    void addLessonLinksLessonAndCourse() {
        course.addLesson(lesson);

        assertTrue(course.getLessons().contains(lesson));
        assertSame(course, lesson.getCourse());
    }

    @Test
    @DisplayName("Повторный и null урок не добавляются")
    void addLessonIgnoresNullAndDuplicates() {
        course.addLesson(lesson);
        course.addLesson(lesson);
        course.addLesson(null);

        assertEquals(1, course.getLessons().size());
    }

    @Test
    @DisplayName("setLessons заменяет список и связывает уроки с курсом")
    void setLessonsUpdatesLessonLinks() {
        Lesson secondLesson = new Lesson(1, 202, "OOP");
        course.setLessons(Arrays.asList(lesson, secondLesson));

        assertEquals(2, course.getLessons().size());
        assertSame(course, lesson.getCourse());
        assertSame(course, secondLesson.getCourse());
    }

    @Test
    @DisplayName("setLessons с null создаёт пустой список")
    void setLessonsNullCreatesEmptyList() {
        course.addLesson(lesson);
        course.setLessons(null);
        assertNotNull(course.getLessons());
        assertTrue(course.getLessons().isEmpty());
    }

    @Test
    @DisplayName("Курс хранит ссылки на клиента и заказ")
    void customerAndOrderLinksCanBeSet() {
        Customer customer = new Customer("Ivan", "123", "Moscow");
        Order order = new Order();
        course.setCustomer(customer);
        course.setOrder(order);
        assertSame(customer, course.getCustomer());
        assertSame(order, course.getOrder());
    }

    @Test
    @DisplayName("toString курса содержит основные поля")
    void toStringContainsCourseData() {
        String result = course.toString();
        assertTrue(result.contains("101"));
        assertTrue(result.contains("1000.0"));
        assertTrue(result.contains("discountedPrice"));
    }
}
