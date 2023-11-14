package christmas.domian;

import christmas.util.Validator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuOrder {
    private final Map<String, Integer> orderDetails;
    private final Validator validator;
    private int visitDate;

    public MenuOrder(Menu menu) {
        this.orderDetails = new LinkedHashMap<>();
        this.validator = new Validator(menu);
    }

    public void setVisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public int getVisitDate() {
        return visitDate;
    }

    public void addOrder(String menuItem, int quantity) {
        try {
            validator.validateQuantity(quantity);
            validator.validateMenu(menuItem);

            orderDetails.put(menuItem, orderDetails.getOrDefault(menuItem, 0) + quantity);
        } catch (IllegalArgumentException e) {
            validator.throwInvalidMenuOrderException(e.getMessage());
        }
    }

    public int calculateTotalOrderAmount(Menu menu) {
        return orderDetails.entrySet().stream()
                .mapToInt(entry -> entry.getValue() * menu.getPrice(entry.getKey()))
                .sum();
    }

    public Map<String, Integer> getOrderDetails() {
        return new LinkedHashMap<>(orderDetails);
    }
}
