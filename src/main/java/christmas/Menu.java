package christmas;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Integer> appetizers;
    private final Map<String, Integer> mains;
    private final Map<String, Integer> desserts;
    private final Map<String, Integer> beverages;

    private final Validator validator;

    public Menu() {
        this.appetizers = initializeAppetizers();
        this.mains = initializeMains();
        this.desserts = initializeDesserts();
        this.beverages = initializeBeverages();

        this.validator = new Validator(this);
    }

    private Map<String, Integer> initializeAppetizers() {
        Map<String, Integer> appetizers = new HashMap<>();
        appetizers.put("양송이수프", 6000);
        appetizers.put("타파스", 5500);
        appetizers.put("시저샐러드", 8000);
        return appetizers;
    }

    private Map<String, Integer> initializeMains() {
        Map<String, Integer> mains = new HashMap<>();
        mains.put("티본스테이크", 55000);
        mains.put("바비큐립", 54000);
        mains.put("해산물파스타", 35000);
        mains.put("크리스마스파스타", 25000);
        return mains;
    }

    private Map<String, Integer> initializeDesserts() {
        Map<String, Integer> desserts = new HashMap<>();
        desserts.put("초코케이크", 15000);
        desserts.put("아이스크림", 5000);
        return desserts;
    }

    private Map<String, Integer> initializeBeverages() {
        Map<String, Integer> beverages = new HashMap<>();
        beverages.put("제로콜라", 3000);
        beverages.put("레드와인", 60000);
        beverages.put("샴페인", 25000);
        return beverages;
    }

    public Map<String, Integer> getAppetizers() {
        return new HashMap<>(appetizers);
    }

    public Map<String, Integer> getMains() {
        return new HashMap<>(mains);
    }

    public Map<String, Integer> getDesserts() {
        return new HashMap<>(desserts);
    }

    public Map<String, Integer> getBeverages() {
        return new HashMap<>(beverages);
    }

    public int getPrice(String category, String menuItem) {
        try {
            validator.validateMenu(menuItem);

            Map<String, Integer> categoryMap = getCategoryMap(category);
            return categoryMap.get(menuItem);
        } catch (IllegalArgumentException e) {
            validator.throwInvalidMenuOrderException(e.getMessage());
            return -1;
        }
    }

    private Map<String, Integer> getCategoryMap(String category) {
        switch (category) {
            case "애피타이저":
                return getAppetizers();
            case "메인":
                return getMains();
            case "디저트":
                return getDesserts();
            case "음료":
                return getBeverages();
            default:
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 카테고리입니다. 다시 입력해 주세요.");
        }
    }

    public int getPrice(String menuItem) {
        try {
            validator.validateMenu(menuItem);

            Map<String, Integer> allMenuPrices = getMenuPrices();
            return allMenuPrices.get(menuItem);
        } catch (IllegalArgumentException e) {
            validator.throwInvalidMenuOrderException(e.getMessage());
            return -1;
        }
    }

    public Map<String, Integer> getMenuPrices() {
        Map<String, Integer> allMenuPrices = new HashMap<>();
        allMenuPrices.putAll(getAppetizers());
        allMenuPrices.putAll(getMains());
        allMenuPrices.putAll(getDesserts());
        allMenuPrices.putAll(getBeverages());
        return allMenuPrices;
    }
}