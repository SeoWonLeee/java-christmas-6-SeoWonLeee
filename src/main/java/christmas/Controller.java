package christmas;

import java.time.LocalDate;

public class Controller {
    private final Menu menu;
    private final InputView inputView;
    private final OutputView outputView;
    private final Promotion promotion;
    private final DiscountCalculator discountCalculator;
    private final EventBadge eventBadge;

    public Controller(Menu menu, OutputView outputView) {
        this.menu = menu;
        this.inputView = new InputView(menu);
        this.outputView = outputView;
        this.promotion = new Promotion();
        this.discountCalculator = new DiscountCalculator(promotion, menu);
        this.eventBadge = new EventBadge();
    }

    public void run() {
        try {
            outputView.printGreeting();

            int visitDate = inputView.readDate();
            MenuOrder menuOrder = inputView.readMenuOrder(menu);
            menuOrder.setVisitDate(visitDate);

            outputView.printEventPreview(visitDate);
            outputView.printMenuOrder(menuOrder);

            int totalOrderAmount = calculateTotalOrderAmount(menuOrder);
            outputView.printTotalOrderAmount(totalOrderAmount);

            String gift = calculateGift(menuOrder);
            outputView.printGift(gift);

            LocalDate currentDate = LocalDate.now();
            int christmasDiscount = promotion.calculateChristmasDiscount(visitDate);
            int weekdayDiscount = promotion.calculateWeekdayDiscount(currentDate,
                    menuOrder.getOrderDetails().getOrDefault("아이스크림", 0) +
                            menuOrder.getOrderDetails().getOrDefault("초코케이크", 0));
            int weekendDiscount = promotion.calculateWeekendDiscount(currentDate,
                    menuOrder.getOrderDetails().getOrDefault("티본스테이크", 0) +
                            menuOrder.getOrderDetails().getOrDefault("바비큐립", 0) +
                            menuOrder.getOrderDetails().getOrDefault("해산물파스타", 0) +
                            menuOrder.getOrderDetails().getOrDefault("크리스마스파스타", 0));
            int specialDiscount = promotion.calculateSpecialDiscount(visitDate);
            int giftDiscount = promotion.calculateGiftPromotion(totalOrderAmount);

            outputView.printBenefits(christmasDiscount, specialDiscount, weekdayDiscount, weekendDiscount,
                    giftDiscount);

            int totalDiscount = discountCalculator.calculateTotalDiscount(menuOrder);
            int totalBenefits = calculateTotalBenefits(totalDiscount, giftDiscount);
            int finalPayment = calculateFinalPayment(totalOrderAmount, totalDiscount);

            outputView.printTotalBenefits(totalBenefits);
            outputView.printFinalPayment(finalPayment);

            String eventBadgeResult = eventBadge.determineEventBadge(totalBenefits);
            outputView.printEventBadge(eventBadgeResult);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private int calculateTotalOrderAmount(MenuOrder menuOrder) {
        return menuOrder.calculateTotalOrderAmount(menu);
    }

    private String calculateGift(MenuOrder menuOrder) {
        int totalOrderAmount = calculateTotalOrderAmount(menuOrder);

        if (totalOrderAmount >= 120000) {
            return "샴페인 1개";
        }

        return "없음";
    }

    private int calculateTotalBenefits(int totalDiscount, int giftDiscount) {
        return discountCalculator.calculateTotalBenefits(totalDiscount, giftDiscount);
    }

    private int calculateFinalPayment(int totalOrderAmount, int totalDiscount) {
        return discountCalculator.calculateFinalPayment(totalOrderAmount, totalDiscount);
    }
}