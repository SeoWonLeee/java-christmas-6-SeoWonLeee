package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final Validator validator;

    public InputView(Menu menu) {
        this.validator = new Validator(menu);
    }

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return validator.validateDate(input);
    }

    public MenuOrder readMenuOrder(Menu menu) {
        MenuOrder menuOrder = new MenuOrder(menu);

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        try {
            String[] orders = input.split(",");

            for (String order : orders) {
                String[] menuDetails = order.split("-");

                if (menuDetails.length != 2) {
                    validator.throwInvalidMenuOrderException();
                }

                String menuItem = menuDetails[0].trim();
                int quantity = Integer.parseInt(menuDetails[1].trim());

                validator.validateQuantity(quantity);

                menuOrder.addOrder(menuItem, quantity);
            }

            validator.validateMenuOrder(menu, menuOrder);

            return menuOrder;
        } catch (NumberFormatException e) {
            validator.throwInvalidMenuOrderException();

            return null;
        }
    }
}