package christmas;

import java.util.Map;

public class OutputView {
    public void printGreeting() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreview(int eventDate) {
        System.out.println("12월 " + eventDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printMenuOrder(MenuOrder menuOrder) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> entry : menuOrder.getOrderDetails().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", totalOrderAmount));
    }

    public void printGift(String gift) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(gift);
    }

    public void printBenefits(Promotion promotion, MenuOrder menuOrder, int weekdayDiscount, int weekendDiscount,
                              int giftDiscount) {
        int christmasDiscount = promotion.calculateChristmasDiscount();
        int specialDiscount = promotion.calculateSpecialDiscount(menuOrder);

        System.out.println("\n<혜택 내역>");
        boolean anyDiscountApplied = (christmasDiscount > 0) || (weekdayDiscount > 0) ||
                (weekendDiscount > 0) || (specialDiscount > 0) || (giftDiscount > 0);

        if (anyDiscountApplied) {
            printDiscount("크리스마스 디데이 할인", christmasDiscount);
            printDiscount("평일 할인", weekdayDiscount);
            printDiscount("주말 할인", weekendDiscount);
            printDiscount("특별 할인", specialDiscount);
            printDiscount("증정 이벤트", giftDiscount);
        } else {
            System.out.println("없음");
        }
    }

    private boolean isNoDiscount(int... discounts) {
        for (int discount : discounts) {
            if (discount > 0) {
                return false;
            }
        }
        return true;
    }

    private void printDiscount(String event, int discountAmount) {
        String discountString;
        if (discountAmount > 0) {
            discountString = String.format("-%,d원", discountAmount);
        } else {
            discountString = "없음";
        }
        System.out.println(event + ": " + discountString);
    }

    public void printTotalBenefits(int totalBenefits) {
        System.out.println("\n<총혜택 금액>");
        String formattedTotalBenefits;
        if (totalBenefits < 0) {
            formattedTotalBenefits = String.format("-%,d원", Math.abs(totalBenefits));
        } else {
            formattedTotalBenefits = String.format("%,d원", totalBenefits);
        }
        System.out.println(formattedTotalBenefits);
    }

    public void printFinalPayment(int finalPayment) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", finalPayment));
    }

    public void printEventBadge(String eventBadge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(eventBadge);
    }
}
