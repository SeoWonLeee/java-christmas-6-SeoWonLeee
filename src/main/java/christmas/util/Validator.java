package christmas.util;

import christmas.domian.Menu;
import christmas.domian.MenuOrder;
import java.util.Map;

public class Validator {
    private static final int MAX_MENU_QUANTITY = 20;
    private final Map<String, Integer> menuPrices;
    private final Menu menu;

    public Validator(Menu menu) {
        this.menuPrices = menu.getMenuPrices();
        this.menu = menu;
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

    public void validateMenuOrder(MenuOrder menuOrder) {
        if (menuOrder.getOrderDetails().isEmpty()) {
            throwInvalidMenuOrderException("[ERROR] 주문 내역이 비어 있습니다. 다시 입력해 주세요.");
        }

        boolean onlyBeverages = menuOrder.getOrderDetails().keySet().stream()
                .allMatch(menuItem -> menuPrices.containsKey(menuItem) &&
                        menu.getItemType(menuItem) == Menu.ItemType.BEVERAGE);

        if (onlyBeverages && menuOrder.getOrderDetails().size() > 0) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 메뉴를 다시 입력해 주세요.");
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
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 주문 수량은 최소 1 이상이어야 합니다. 다시 입력해 주세요.");
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
