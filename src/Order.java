import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Order описывает заказ клиента.
 * Заказ содержит дату создания, статус, список курсов
 * и информацию об оплате.
 */
public class Order {

    private int id;
    private LocalDate createdAt;
    private OrderStatus status;

    private Customer customer;
    private List<Course> courses;
    private Payment payment;

    public Order() {
        this.createdAt = LocalDate.now();
        this.status = OrderStatus.NEW;
        this.courses = new ArrayList<>();
    }

    public Order(int id, LocalDate createdAt, OrderStatus status) {
        this();
        this.id = id;
        this.createdAt = createdAt == null ?
                LocalDate.now() : createdAt;
        this.status = status == null ?
                OrderStatus.NEW : status;
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;

        for (Course course : courses) {
            if (course != null) {
                course.setCustomer(customer);
            }
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses == null ?
                new ArrayList<>() : new ArrayList<>(courses);

        for (Course course : this.courses) {
            if (course != null) {
                course.setOrder(this);
                course.setCustomer(customer);
            }
        }
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;

        if (payment != null) {
            payment.setOrder(this);
        }
    }

    /**
     * Добавляет курс в заказ.
     *
     * @param course курс, который необходимо добавить
     */
    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
            course.setOrder(this);
            course.setCustomer(customer);

            if (customer != null) {
                customer.addCourse(course);
            }
        }
    }

    /**
     * Рассчитывает общую стоимость заказа
     * на основе стоимости всех добавленных курсов.
     *
     * @return общая стоимость заказа
     */
    public double getTotal() {
        double total = 0;

        for (Course course : courses) {
            total += course.calculateTotalPrice();
        }

        return total;
    }

    /**
     * Отменяет заказ, изменяя его статус на CANCELLED.
     */
    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }

    /**
     * Возвращает текстовое представление заказа.
     *
     * @return информация о заказе
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", coursesCount=" + courses.size() +
                ", total=" + getTotal() +
                ", paymentId=" +
                (payment == null ? "none" : payment.getId()) +
                '}';
    }
}