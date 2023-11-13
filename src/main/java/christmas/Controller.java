package christmas;

public class Controller {
    private final Menu menu;
    private final InputView inputView;
    private final OutputView outputView;
    private final Validator validator;
    private final Promotion promotion;
    private final DiscountCalculator discountCalculator;
    private final EventBadge eventBadge;

    public Controller(OutputView outputView) {
        this.menu = new Menu();
        this.inputView = new InputView(menu);
        this.outputView = outputView;
        this.validator = new Validator(menu);
        this.promotion = new Promotion();
        this.discountCalculator = new DiscountCalculator(promotion, menu);
        this.eventBadge = new EventBadge();
    }

    public void run() {
        try {
            outputView.printGreeting();

            int visitDate = inputView.readDate();

            MenuOrder menuOrder = inputView.readMenuOrder(menu);

            outputView.printEventPreview(visitDate);

            outputView.printMenuOrder(menuOrder);

            int totalOrderAmount = calculateTotalOrderAmount(menuOrder);
            outputView.printTotalOrderAmount(totalOrderAmount);

            String gift = calculateGift(menuOrder);
            outputView.printGift(gift);

            int christmasDiscount = promotion.calculateChristmasDiscount();
            int weekdayDiscount = promotion.calculateWeekdayDiscount(
                    menuOrder.getOrderDetails().getOrDefault("아이스크림", 0));
            int weekendDiscount = promotion.calculateWeekendDiscount(
                    menuOrder.getOrderDetails().getOrDefault("티본스테이크", 0));
            int specialDiscount = promotion.calculateSpecialDiscount(menuOrder.getOrderDetails().containsKey("별"));
            int giftDiscount = promotion.calculateGiftDiscount(totalOrderAmount);

            outputView.printBenefits(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount,
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
        return menuOrder.getOrderDetails().entrySet().stream()
                .mapToInt(entry -> entry.getValue() * menu.getPrice(entry.getKey()))
                .sum();
    }

    private String calculateGift(MenuOrder menuOrder) {
        int totalOrderAmount = calculateTotalOrderAmount(menuOrder);
        return (totalOrderAmount >= 120000) ? "샴페인 1개" : "없음";
    }

    private int calculateTotalBenefits(int totalDiscount, int giftDiscount) {
        return discountCalculator.calculateTotalBenefits(totalDiscount, giftDiscount);
    }

    private int calculateFinalPayment(int totalOrderAmount, int totalDiscount) {
        return discountCalculator.calculateFinalPayment(totalOrderAmount, totalDiscount);
    }
}
