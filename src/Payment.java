import java.time.LocalDate;

/**
 * Класс Payment описывает оплату заказа.
 * Содержит сумму платежа, способ оплаты и дату проведения.
 */
public class Payment {

    private int id;
    private double amount;
    private String method;
    private LocalDate paidAt;

    private Order order;

    public Payment() {
    }

    public Payment(int id, double amount, String method,
                   LocalDate paidAt) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.paidAt = paidAt;
    }

    // Геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDate getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDate paidAt) {
        this.paidAt = paidAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Выполняет обработку платежа.
     * Проверяет правильность суммы и наличие способа оплаты.
     * При успешной обработке устанавливает дату оплаты
     * и изменяет статус заказа на PAID.
     *
     * @return true, если платёж успешно обработан,
     *         иначе false
     */
    public boolean process() {
        if (amount <= 0 || method == null || method.isBlank()) {
            return false;
        }

        paidAt = LocalDate.now();

        if (order != null) {
            order.setStatus(OrderStatus.PAID);
        }

        return true;
    }

    /**
     * Возвращает текстовое представление платежа.
     *
     * @return информация о платеже
     */
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", paidAt=" + paidAt +
                '}';
    }
}