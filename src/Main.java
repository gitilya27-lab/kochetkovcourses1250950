import java.time.LocalDate;



/**
 * Главный класс программы.
 * Используется для проверки работы всех классов,
 * их конструкторов, методов, связей и перечисления OrderStatus.
 */
public class Main {
    /**
     * Главный метод программы.
     * Создаёт объекты всех классов, демонстрирует работу их методов,
     * устанавливает связи между объектами и выводит информацию
     * о результатах работы программы в консоль.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {

        // Создание клиента
        Customer customer = new Customer(
                "Ivan",
                "123456789",
                "Moscow"
        );

        System.out.println("--- CUSTOMER ---");
        System.out.println(customer);

        customer.setPhone("987654321");
        System.out.println(customer.getName());
        System.out.println(customer.getPhone());
        System.out.println(customer.getAddress());


        // Создание категории
        Category category = new Category(1, "Programming");

        System.out.println("\n--- CATEGORY ---");
        System.out.println(category);

        category.setName("IT");
        System.out.println(category.getId());
        System.out.println(category.getName());


        // Создание преподавателя
        Teacher teacher = new Teacher(
                "John",
                "555123",
                "Berlin"
        );

        System.out.println("\n--- TEACHER ---");
        System.out.println(teacher);

        teacher.setName("Michael");
        System.out.println(teacher.getName());
        System.out.println(teacher.getPhone());
        System.out.println(teacher.getAddress());


        // Создание урока
        Lesson lesson = new Lesson(
                3,
                201,
                "Java Basics"
        );

        System.out.println("\n--- LESSON ---");
        System.out.println(lesson);

        lesson.setHours(4);
        lesson.setName("Advanced Java");

        System.out.println(lesson.getId());
        System.out.println(lesson.getName());
        System.out.println(lesson.getHours());

        System.out.println("Доступность урока: " +
                lesson.isAvailable());


        // Связь категории и урока
        category.addLesson(lesson);

        System.out.println(category.getLessons());

        lesson.setCategory(category);
        System.out.println(lesson.getCategory());


        // Связь преподавателя и урока
        teacher.teachLesson(lesson);

        System.out.println(teacher.getLessons());
        System.out.println(teacher.teachLesson());

        lesson.setTeacher(teacher);
        System.out.println(lesson.getTeacher());


        // Создание курса
        Course course = new Course(
                2,
                101,
                1000,
                10
        );

        System.out.println("\n--- COURSE ---");
        System.out.println(course);

        course.setQuantity(3);
        course.setPrice(1500);
        course.setDiscount(15);

        System.out.println(course.getId());
        System.out.println(course.getQuantity());
        System.out.println(course.getPrice());
        System.out.println(course.getDiscount());

        System.out.println("Цена со скидкой: " +
                course.calculateDiscountedPrice());

        System.out.println("Общая стоимость курса: " +
                course.calculateTotalPrice());


        // Связь курса и урока
        course.addLesson(lesson);

        System.out.println(course.getLessons());

        lesson.setCourse(course);
        System.out.println(lesson.getCourse());


        // Связь клиента и курса
        customer.addCourse(course);

        System.out.println(customer.getCourses());

        course.setCustomer(customer);
        System.out.println(course.getCustomer());


        // Создание заказа
        Order order = customer.takeCourse();

        System.out.println("\n--- ORDER ---");
        System.out.println(order);

        order.setId(301);
        order.setCreatedAt(LocalDate.now());
        order.setStatus(OrderStatus.NEW);

        System.out.println(order.getId());
        System.out.println(order.getCreatedAt());
        System.out.println(order.getStatus());

        // Расчёт стоимости заказа
        System.out.println("Стоимость заказа: " +
                order.getTotal());

        // Добавление курса в заказ
        order.addCourse(course);

        System.out.println(order.getCourses());

        order.setCustomer(customer);
        System.out.println(order.getCustomer());

        // Отмена заказа
        order.cancel();

        System.out.println("Статус после отмены: " +
                order.getStatus());

        // Изменение статуса
        order.setStatus(OrderStatus.NEW);


        // Создание оплаты
        Payment payment = new Payment(
                401,
                order.getTotal(),
                "Card",
                null
        );

        System.out.println("\n--- PAYMENT ---");
        System.out.println(payment);

        payment.setId(402);
        payment.setAmount(order.getTotal());
        payment.setMethod("Online");

        System.out.println(payment.getId());
        System.out.println(payment.getAmount());
        System.out.println(payment.getMethod());
        System.out.println(payment.getPaidAt());

        // Связь оплаты и заказа
        order.setPayment(payment);

        payment.setOrder(order);
        System.out.println(payment.getOrder());

        // Обработка оплаты
        System.out.println("Результат оплаты: " +
                payment.process());

        System.out.println("Статус заказа: " +
                order.getStatus());

        System.out.println("Дата оплаты: " +
                payment.getPaidAt());


        // Итоговая информация
        System.out.println("\n--- ALL OBJECTS ---");

        System.out.println(customer.toString());
        System.out.println(course.toString());
        System.out.println(order.toString());
        System.out.println(payment.toString());
        System.out.println(category.toString());
        System.out.println(lesson.toString());
        System.out.println(teacher.toString());

        System.out.println("\n--- ORDER STATUS ---");

        System.out.println(OrderStatus.NEW);
        System.out.println(OrderStatus.PAID);
        System.out.println(OrderStatus.CANCELLED);

        System.out.println("\nПрограмма завершена!");
    }
}