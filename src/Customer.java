import java.util.ArrayList;
import java.util.List;

/**
 * Класс Customer описывает клиента, который может приобретать курсы
 * и создавать заказы.
 */
public class Customer {

    private String name;
    private String phone;
    private String address;

    private List<Order> orders;
    private List<Course> courses;

    public Customer() {
        this.orders = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public Customer(String name, String phone, String address) {
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders == null ?
                new ArrayList<>() : new ArrayList<>(orders);

        for (Order order : this.orders) {
            if (order != null) {
                order.setCustomer(this);
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
                course.setCustomer(this);
            }
        }
    }

    /**
     * Добавляет заказ в список заказов клиента.
     *
     * @param order заказ, который необходимо добавить
     */
    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
            order.setCustomer(this);
        }
    }

    /**
     * Добавляет курс в список курсов клиента.
     *
     * @param course курс, который необходимо добавить
     */
    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
            course.setCustomer(this);
        }
    }

    /**
     * Создаёт новый заказ на основе курсов клиента.
     *
     * @return созданный заказ
     */
    public Order takeCourse() {
        Order order = new Order();
        order.setCustomer(this);

        for (Course course : courses) {
            order.addCourse(course);
        }

        addOrder(order);

        return order;
    }

    /**
     * Добавляет выбранный курс клиенту и создаёт заказ.
     *
     * @param course выбранный курс
     * @return созданный заказ
     */
    public Order takeCourse(Course course) {
        addCourse(course);
        return takeCourse();
    }

    /**
     * Возвращает текстовое представление клиента.
     *
     * @return информация о клиенте
     */
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ordersCount=" + orders.size() +
                ", coursesCount=" + courses.size() +
                '}';
    }
}