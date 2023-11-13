package christmas;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuOrder {
    private final Map<String, Integer> orderDetails;

    private final Validator validator;

    public MenuOrder(Menu menu) {
        this.orderDetails = new LinkedHashMap<>();

        this.validator = new Validator(menu);
    }

    public void addOrder(String menuItem, int quantity) {
        try {
            validator.validateQuantity(quantity);
            validator.validateMenu(menuItem);

            if (orderDetails.containsKey(menuItem)) {
                orderDetails.put(menuItem, orderDetails.get(menuItem) + quantity);
            } else {
                orderDetails.put(menuItem, quantity);
            }
        } catch (IllegalArgumentException e) {
            validator.throwInvalidMenuOrderException(e.getMessage());
        }
    }

    public Map<String, Integer> getOrderDetails() {
        return new LinkedHashMap<>(orderDetails);
    }
}
