package christmas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Promotion {
    private static final LocalDate CHRISTMAS_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_END_DATE = LocalDate.of(2023, 12, 25);

    public int calculateChristmasDiscount(int visitDate) {
        LocalDate visitLocalDate = LocalDate.of(2023, 1, 1).plusDays(visitDate - 1);
        if (visitLocalDate.isAfter(CHRISTMAS_START_DATE) && visitLocalDate.isBefore(CHRISTMAS_END_DATE.plusDays(1))) {
            long daysUntilChristmas = visitLocalDate.until(CHRISTMAS_END_DATE, ChronoUnit.DAYS);
            return (int) Math.min(1000 + (daysUntilChristmas * 100), 3400);
        }
        return 0;
    }

    public int calculateWeekdayDiscount(int dessertCount) {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.getDayOfWeek().getValue() >= 1 && currentDate.getDayOfWeek().getValue() <= 4) {
            return dessertCount * 2023;
        }

        return 0;
    }

    public int calculateWeekendDiscount(int mainCount) {
        LocalDate currentDate = LocalDate.now();

        if (currentDate.getDayOfWeek().getValue() >= 5 && currentDate.getDayOfWeek().getValue() <= 6) {
            return mainCount * 2023;
        }

        return 0;
    }

    public int calculateSpecialDiscount(int visitDate) {
        LocalDate visitLocalDate = LocalDate.of(2023, 1, 1).plusDays(visitDate - 1);
        int dayOfMonth = visitLocalDate.getDayOfMonth();
        if (visitLocalDate.getMonthValue() == 12 && (dayOfMonth == 3 || dayOfMonth == 10 || dayOfMonth == 17
                || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 31)) {
            return 1000;
        }
        return 0;
    }

    public int calculateGiftDiscount(int totalOrderPrice) {
        if (totalOrderPrice >= 120000) {
            return 25000;
        } else {
            return 0;
        }
    }
}