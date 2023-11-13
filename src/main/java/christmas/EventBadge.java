package christmas;

public class EventBadge {

    public String determineEventBadge(int totalDiscount) {
        if (totalDiscount >= 5000 && totalDiscount < 10000) {
            return "별";
        } else if (totalDiscount >= 10000 && totalDiscount < 20000) {
            return "트리";
        } else if (totalDiscount >= 20000) {
            return "산타";
        } else {
            return "없음";
        }
    }
}
