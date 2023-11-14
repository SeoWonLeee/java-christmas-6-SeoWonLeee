package christmas;

import java.time.LocalDate;

public class Promotion {
    private static final LocalDate CHRISTMAS_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_END_DATE = LocalDate.of(2023, 12, 25);
    private static final int MAX_CHRISTMAS_DISCOUNT = 3400;
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    private static final int GIFT_DISCOUNT_THRESHOLD = 120000;
    private static final int GIFT_DISCOUNT_AMOUNT = 25000;


    public int calculateChristmasDiscount(int visitDate) {
        LocalDate visitLocalDate = calculateLocalDate(visitDate);

        if (isDateInRange(visitLocalDate, CHRISTMAS_START_DATE, CHRISTMAS_END_DATE)) {
            long daysUntilChristmas = CHRISTMAS_START_DATE.until(visitLocalDate).getDays();
            int progressiveDiscount = (int) Math.min(1000 + (daysUntilChristmas * 100), MAX_CHRISTMAS_DISCOUNT);
            return Math.max(0, progressiveDiscount);
        }
        return 0;
    }

    public int calculateWeekdayDiscount(LocalDate currentDate, int dessertCount) {
        if (isWeekday(currentDate)) {
            return dessertCount * WEEKDAY_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    public int calculateWeekendDiscount(LocalDate currentDate, int mainCount) {
        if (isWeekend(currentDate)) {
            return mainCount * WEEKDAY_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    public int calculateSpecialDiscount(int visitDate) {
        LocalDate visitLocalDate = calculateLocalDate(visitDate);
        int dayOfMonth = visitLocalDate.getDayOfMonth();
        if (isDateInDecember(visitLocalDate) && isSpecialDiscountDay(dayOfMonth)) {
            return 1000;
        }
        return 0;
    }

    public int calculateGiftPromotion(int totalOrderPrice) {
        if (totalOrderPrice >= GIFT_DISCOUNT_THRESHOLD) {
            return GIFT_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    private LocalDate calculateLocalDate(int visitDate) {
        return LocalDate.of(2023, 12, 1).plusDays(visitDate - 1);
    }

    private boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return date.isAfter(startDate) && date.isBefore(endDate.plusDays(1));
    }

    private boolean isWeekday(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek >= 1 && dayOfWeek <= 4;
    }

    private boolean isWeekend(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek >= 5 && dayOfWeek <= 6;
    }

    private boolean isDateInDecember(LocalDate date) {
        return date.getMonthValue() == 12;
    }

    private boolean isSpecialDiscountDay(int dayOfMonth) {
        return dayOfMonth == 3 || dayOfMonth == 10 || dayOfMonth == 17 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 31;
    }
}
