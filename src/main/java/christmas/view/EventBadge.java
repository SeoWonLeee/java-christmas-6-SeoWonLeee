package christmas.view;

public class EventBadge {

    public String determineEventBadge(int totalDiscount) {
        if (totalDiscount >= 5000 && totalDiscount < 10000) {
            return "별";
        }

        if (totalDiscount >= 10000 && totalDiscount < 20000) {
            return "트리";
        }

        if (totalDiscount >= 20000) {
            return "산타";
        }

        return "없음";
    }
}
