package christmas;

import java.util.Map;

public class Validator {
    private static final int MAX_MENU_QUANTITY = 20;
    private final Map<String, Integer> menuPrices;

    public Validator(Menu menu) {
        this.menuPrices = menu.getMenuPrices();
    }

    public int validateDate(String input) {
        try {
            int date = Integer.parseInt(input);
            if (date < 1 || date > 31) {
                throwInvalidDateException();
            }
            return date;
        } catch (NumberFormatException e) {
            throwInvalidDateException();
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateMenuOrder(Menu menu, MenuOrder menuOrder) {
        if (menuOrder.getOrderDetails().isEmpty()) {
            throwInvalidMenuOrderException("[ERROR] 주문 내역이 비어 있습니다. 다시 입력해 주세요.");
        }

        int totalOrderAmount = menuOrder.getOrderDetails().entrySet().stream()
                .mapToInt(entry -> entry.getValue() * menuPrices.get(entry.getKey()))
                .sum();

        long totalMenuQuantity = menuOrder.getOrderDetails().values().stream().mapToInt(Integer::intValue).sum();
        if (totalMenuQuantity > MAX_MENU_QUANTITY) {
            throwInvalidMenuOrderException("[ERROR] 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }

    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throwInvalidMenuOrderException("[ERROR] 음료의 개수는 1 이상이어야 합니다. 다시 입력해 주세요.");
        }
    }

    public void throwInvalidDateException() {
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public void throwInvalidMenuOrderException() {
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public void throwInvalidMenuOrderException(String message) {
        throw new IllegalArgumentException(message);
    }

    public void validateMenu(String menuItem) {
        if (!menuPrices.containsKey(menuItem)) {
            throwInvalidMenuException();
        }
    }

    public void throwInvalidMenuException() {
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}

