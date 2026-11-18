import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Customer")
class CustomerTest {

    private Customer customer;
    private Course course;
    private Order order;

    @BeforeEach
    void setUp() {
        customer = new Customer("Ivan", "123456789", "Moscow");
        course = new Course(2, 101, 1000.0, 10.0);
        order = new Order();
    }

    @Test
    @DisplayName("Конструктор и геттеры/сеттеры клиента")
    void constructorAndAccessorsWork() {
        assertEquals("Ivan", customer.getName());
        assertEquals("123456789", customer.getPhone());
        assertEquals("Moscow", customer.getAddress());

        customer.setName("Petr");
        customer.setPhone("555");
        customer.setAddress("Kazan");

        assertEquals("Petr", customer.getName());
        assertEquals("555", customer.getPhone());
        assertEquals("Kazan", customer.getAddress());
        assertNotNull(new Customer().getOrders());
        assertNotNull(new Customer().getCourses());
    }

    @Test
    @DisplayName("Клиент добавляет курс и устанавливает обратную связь")
    void addCourseLinksCustomerAndCourse() {
        customer.addCourse(course);

        assertTrue(customer.getCourses().contains(course));
        assertSame(customer, course.getCustomer());
    }

    @Test
    @DisplayName("Повторный и null курс не добавляются")
    void addCourseIgnoresNullAndDuplicates() {
        customer.addCourse(course);
        customer.addCourse(course);
        customer.addCourse(null);

        assertEquals(1, customer.getCourses().size());
    }

    @Test
    @DisplayName("Клиент добавляет заказ и устанавливает обратную связь")
    void addOrderLinksCustomerAndOrder() {
        customer.addOrder(order);

        assertTrue(customer.getOrders().contains(order));
        assertSame(customer, order.getCustomer());
    }

    @Test
    @DisplayName("Повторный и null заказ не добавляются")
    void addOrderIgnoresNullAndDuplicates() {
        customer.addOrder(order);
        customer.addOrder(order);
        customer.addOrder(null);

        assertEquals(1, customer.getOrders().size());
    }

    @Test
    @DisplayName("setCourses устанавливает клиента для каждого курса")
    void setCoursesUpdatesCourseLinks() {
        Course secondCourse = new Course(1, 102, 500, 0);
        customer.setCourses(Arrays.asList(course, secondCourse));

        assertEquals(2, customer.getCourses().size());
        assertSame(customer, course.getCustomer());
        assertSame(customer, secondCourse.getCustomer());
    }

    @Test
    @DisplayName("setCourses с null очищает список")
    void setCoursesNullCreatesEmptyList() {
        customer.addCourse(course);
        customer.setCourses(null);

        assertNotNull(customer.getCourses());
        assertTrue(customer.getCourses().isEmpty());
    }

    @Test
    @DisplayName("setOrders устанавливает клиента для каждого заказа")
    void setOrdersUpdatesOrderLinks() {
        Order secondOrder = new Order();
        customer.setOrders(Arrays.asList(order, secondOrder));

        assertEquals(2, customer.getOrders().size());
        assertSame(customer, order.getCustomer());
        assertSame(customer, secondOrder.getCustomer());
    }

    @Test
    @DisplayName("setOrders с null создаёт пустой список")
    void setOrdersNullCreatesEmptyList() {
        customer.addOrder(order);
        customer.setOrders(null);

        assertNotNull(customer.getOrders());
        assertTrue(customer.getOrders().isEmpty());
    }

    @Test
    @DisplayName("takeCourse без аргументов создаёт заказ по курсам клиента")
    void takeCourseCreatesOrderFromCustomerCourses() {
        customer.addCourse(course);

        Order createdOrder = customer.takeCourse();

        assertNotNull(createdOrder);
        assertSame(customer, createdOrder.getCustomer());
        assertTrue(customer.getOrders().contains(createdOrder));
        assertTrue(createdOrder.getCourses().contains(course));
        assertSame(createdOrder, course.getOrder());
    }

    @Test
    @DisplayName("takeCourse с курсом добавляет его и создаёт заказ")
    void takeCourseWithCourseCreatesOrder() {
        Order createdOrder = customer.takeCourse(course);

        assertNotNull(createdOrder);
        assertTrue(customer.getCourses().contains(course));
        assertTrue(createdOrder.getCourses().contains(course));
        assertTrue(customer.getOrders().contains(createdOrder));
    }

    @Test
    @DisplayName("toString клиента содержит основные данные")
    void toStringContainsCustomerData() {
        String result = customer.toString();

        assertTrue(result.contains("Ivan"));
        assertTrue(result.contains("123456789"));
        assertTrue(result.contains("Moscow"));
    }
}
