import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Order")
class OrderTest {

    private Order order;
    private Customer customer;
    private Course course;

    @BeforeEach
    void setUp() {
        order = new Order();
        customer = new Customer("Ivan", "123", "Moscow");
        course = new Course(2, 101, 1000.0, 10.0);
    }

    @Test
    @DisplayName("Конструктор без параметров задаёт дату и статус NEW")
    void defaultConstructorSetsInitialValues() {
        assertEquals(LocalDate.now(), order.getCreatedAt());
        assertEquals(OrderStatus.NEW, order.getStatus());
        assertNotNull(order.getCourses());
        assertTrue(order.getCourses().isEmpty());
    }

    @Test
    @DisplayName("Конструктор с параметрами создаёт заказ")
    void parameterizedConstructorWorks() {
        LocalDate date = LocalDate.of(2025, 5, 10);
        Order created = new Order(77, date, OrderStatus.PAID);

        assertEquals(77, created.getId());
        assertEquals(date, created.getCreatedAt());
        assertEquals(OrderStatus.PAID, created.getStatus());
    }

    @Test
    @DisplayName("Конструктор заменяет null дату и статус значениями по умолчанию")
    void constructorHandlesNullDateAndStatus() {
        Order created = new Order(78, null, null);

        assertEquals(LocalDate.now(), created.getCreatedAt());
        assertEquals(OrderStatus.NEW, created.getStatus());
    }

    @Test
    @DisplayName("Геттеры и сеттеры заказа работают")
    void accessorsWork() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        order.setId(15);
        order.setCreatedAt(date);
        order.setStatus(OrderStatus.PAID);

        assertEquals(15, order.getId());
        assertEquals(date, order.getCreatedAt());
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    @DisplayName("Установка клиента обновляет клиента курсов заказа")
    void setCustomerUpdatesCourses() {
        order.addCourse(course);
        order.setCustomer(customer);

        assertSame(customer, order.getCustomer());
        assertSame(customer, course.getCustomer());
    }

    @Test
    @DisplayName("Добавление курса связывает курс, заказ и клиента")
    void addCourseCreatesRelations() {
        order.setCustomer(customer);
        order.addCourse(course);

        assertTrue(order.getCourses().contains(course));
        assertSame(order, course.getOrder());
        assertSame(customer, course.getCustomer());
        assertTrue(customer.getCourses().contains(course));
    }

    @Test
    @DisplayName("Повторный и null курс не добавляются в заказ")
    void addCourseIgnoresNullAndDuplicates() {
        order.addCourse(course);
        order.addCourse(course);
        order.addCourse(null);

        assertEquals(1, order.getCourses().size());
    }

    @Test
    @DisplayName("setCourses заменяет курсы и связывает их с заказом")
    void setCoursesUpdatesCourseRelations() {
        Course secondCourse = new Course(1, 102, 500, 0);
        order.setCustomer(customer);
        order.setCourses(Arrays.asList(course, secondCourse));

        assertEquals(2, order.getCourses().size());
        assertSame(order, course.getOrder());
        assertSame(order, secondCourse.getOrder());
        assertSame(customer, secondCourse.getCustomer());
    }

    @Test
    @DisplayName("setCourses с null очищает список курсов")
    void setCoursesNullCreatesEmptyList() {
        order.addCourse(course);
        order.setCourses(null);

        assertNotNull(order.getCourses());
        assertTrue(order.getCourses().isEmpty());
    }

    @Test
    @DisplayName("Общая стоимость заказа равна сумме курсов со скидками")
    void getTotalSumsCoursePrices() {
        Course secondCourse = new Course(1, 102, 500, 0);
        order.addCourse(course);       // 2 * 1000 со скидкой 10% = 1800
        order.addCourse(secondCourse); // 1 * 500 = 500

        assertEquals(2300.0, order.getTotal(), 0.001);
    }

    @Test
    @DisplayName("Отмена заказа меняет статус на CANCELLED")
    void cancelChangesStatus() {
        order.cancel();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    @DisplayName("Установка оплаты связывает платёж с заказом")
    void setPaymentCreatesRelation() {
        Payment payment = new Payment(1, 100, "Card", null);
        order.setPayment(payment);

        assertSame(payment, order.getPayment());
        assertSame(order, payment.getOrder());
    }

    @Test
    @DisplayName("toString заказа содержит номер, статус и итог")
    void toStringContainsOrderData() {
        order.setId(55);
        order.addCourse(course);

        String result = order.toString();

        assertTrue(result.contains("55"));
        assertTrue(result.contains("NEW"));
        assertTrue(result.contains("total="));
    }
}
