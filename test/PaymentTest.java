import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Payment")
class PaymentTest {

    private Payment payment;
    private Order order;

    @BeforeEach
    void setUp() {
        payment = new Payment(1, 1500.0, "Card", null);
        order = new Order();
    }

    @Test
    @DisplayName("Конструктор и геттеры/сеттеры платежа")
    void constructorAndAccessorsWork() {
        assertEquals(1, payment.getId());
        assertEquals(1500.0, payment.getAmount(), 0.001);
        assertEquals("Card", payment.getMethod());
        assertNull(payment.getPaidAt());

        LocalDate date = LocalDate.of(2025, 3, 10);
        payment.setId(2);
        payment.setAmount(2000.0);
        payment.setMethod("Online");
        payment.setPaidAt(date);

        assertEquals(2, payment.getId());
        assertEquals(2000.0, payment.getAmount(), 0.001);
        assertEquals("Online", payment.getMethod());
        assertEquals(date, payment.getPaidAt());
    }

    @Test
    @DisplayName("Успешная оплата устанавливает дату и статус PAID")
    void processSuccessfulPaymentUpdatesOrder() {
        payment.setOrder(order);

        assertTrue(payment.process());
        assertEquals(LocalDate.now(), payment.getPaidAt());
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    @DisplayName("Успешная оплата без связанного заказа также обрабатывается")
    void processWorksWithoutOrder() {
        assertTrue(payment.process());
        assertEquals(LocalDate.now(), payment.getPaidAt());
    }

    @Test
    @DisplayName("Нулевая или отрицательная сумма не проходит оплату")
    void processRejectsNonPositiveAmount() {
        payment.setAmount(0);
        assertFalse(payment.process());

        payment.setAmount(-10);
        assertFalse(payment.process());
        assertNull(payment.getPaidAt());
    }

    @Test
    @DisplayName("Пустой или null способ оплаты не проходит проверку")
    void processRejectsInvalidMethod() {
        payment.setMethod("");
        assertFalse(payment.process());

        payment.setMethod("   ");
        assertFalse(payment.process());

        payment.setMethod(null);
        assertFalse(payment.process());
    }

    @Test
    @DisplayName("Установка заказа создаёт обратную ссылку платежа")
    void setOrderStoresOrderReference() {
        payment.setOrder(order);

        assertSame(order, payment.getOrder());
    }

    @Test
    @DisplayName("toString платежа содержит сумму и способ оплаты")
    void toStringContainsPaymentData() {
        String result = payment.toString();

        assertTrue(result.contains("1500.0"));
        assertTrue(result.contains("Card"));
    }
}
