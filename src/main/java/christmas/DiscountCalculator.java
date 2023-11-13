package christmas;

import java.util.Map;

public class DiscountCalculator {
    private final Promotion promotion;

    private final Menu menu;

    public DiscountCalculator(Promotion promotion, Menu menu) {
        this.promotion = promotion;
        this.menu = menu;
    }

    public int calculateTotalDiscount(MenuOrder menuOrder) {
        int mainCount = menuOrder.getOrderDetails().entrySet().stream()
                .filter(entry -> entry.getKey().equals("티본스테이크") || entry.getKey().equals("바비큐립") ||
                        entry.getKey().equals("해산물파스타") || entry.getKey().equals("크리스마스파스타"))
                .mapToInt(Map.Entry::getValue)
                .sum();

        int dessertCount = menuOrder.getOrderDetails().entrySet().stream()
                .filter(entry -> entry.getKey().equals("초코케이크") || entry.getKey().equals("아이스크림"))
                .mapToInt(Map.Entry::getValue)
                .sum();

        return promotion.calculateChristmasDiscount() +
                promotion.calculateWeekdayDiscount(dessertCount) +
                promotion.calculateWeekendDiscount(mainCount) +
                promotion.calculateSpecialDiscount(menuOrder) +
                promotion.calculateGiftDiscount(calculateTotalOrderPrice(menuOrder, menu));
    }

    private int calculateTotalOrderPrice(MenuOrder menuOrder, Menu menu) {
        return menuOrder.getOrderDetails().entrySet().stream()
                .mapToInt(entry -> entry.getValue() * menu.getPrice(entry.getKey()))
                .sum();
    }

    public int calculateTotalBenefits(int totalDiscount, int giftPrice) {
        return totalDiscount + giftPrice;
    }

    public int calculateFinalPayment(int totalOrderPrice, int totalDiscount) {
        return Math.max(totalOrderPrice - totalDiscount, 0);
    }
}
