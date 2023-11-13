package christmas;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Integer> menuPrices;

    private final Validator validator;

    public Menu() {
        this.menuPrices = initializeMenuPrices();

        this.validator = new Validator(this);
    }

    private Map<String, Integer> initializeMenuPrices() {
        Map<String, Integer> prices = new HashMap<>();
        prices.put("양송이수프", 6000);
        prices.put("타파스", 5500);
        prices.put("시저샐러드", 8000);
        prices.put("티본스테이크", 55000);
        prices.put("바비큐립", 54000);
        prices.put("해산물파스타", 35000);
        prices.put("크리스마스파스타", 25000);
        prices.put("초코케이크", 15000);
        prices.put("아이스크림", 5000);
        prices.put("제로콜라", 3000);
        prices.put("레드와인", 60000);
        prices.put("샴페인", 25000);
        return prices;
    }

    public Map<String, Integer> getMenuPrices() {
        return new HashMap<>(menuPrices);
    }

    public int getPrice(String menuItem) {
        try {
            validator.validateMenu(menuItem);
            return menuPrices.get(menuItem);
        } catch (IllegalArgumentException e) {
            validator.throwInvalidMenuOrderException(e.getMessage());

            return -1;
        }
    }
}
