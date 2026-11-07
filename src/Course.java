import java.util.ArrayList;
import java.util.List;

/**
 * Класс Course описывает учебный курс.
 * Содержит информацию о количестве, цене и скидке,
 * а также связанные с курсом уроки.
 */
public class Course {

    private int quantity;
    private int id;
    private double price;
    private double discount;

    private Customer customer;
    private Order order;
    private List<Lesson> lessons;

    public Course() {
        this.lessons = new ArrayList<>();
    }

    public Course(int quantity, int id, double price, double discount) {
        this();
        this.quantity = quantity;
        this.id = id;
        this.price = price;
        this.discount = discount;
    }

    // Геттеры и сеттеры

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons == null ?
                new ArrayList<>() : new ArrayList<>(lessons);

        for (Lesson lesson : this.lessons) {
            if (lesson != null) {
                lesson.setCourse(this);
            }
        }
    }

    /**
     * Добавляет урок в список уроков курса.
     *
     * @param lesson урок, который необходимо добавить
     */
    public void addLesson(Lesson lesson) {
        if (lesson != null && !lessons.contains(lesson)) {
            lessons.add(lesson);
            lesson.setCourse(this);
        }
    }

    /**
     * Рассчитывает цену одного курса с учётом скидки.
     * Размер скидки задаётся в процентах.
     *
     * @return цена курса после применения скидки
     */
    public double calculateDiscountedPrice() {
        double actualDiscount = Math.max(0,
                Math.min(discount, 100));

        return price * (1 - actualDiscount / 100);
    }

    /**
     * Рассчитывает полную стоимость всех экземпляров курса
     * с учётом количества и скидки.
     *
     * @return общая стоимость курса
     */
    public double calculateTotalPrice() {
        return calculateDiscountedPrice() * quantity;
    }

    /**
     * Возвращает текстовое представление курса.
     *
     * @return информация о курсе
     */
    @Override
    public String toString() {
        return "Course{" +
                "quantity=" + quantity +
                ", id=" + id +
                ", price=" + price +
                ", discount=" + discount +
                ", discountedPrice=" + calculateDiscountedPrice() +
                ", totalPrice=" + calculateTotalPrice() +
                ", lessonsCount=" + lessons.size() +
                '}';
    }
}