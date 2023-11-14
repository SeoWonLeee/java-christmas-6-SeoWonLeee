package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class christmasTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_결과_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "티본스테이크 1개",
                    "바비큐립 1개",
                    "초코케이크 2개",
                    "제로콜라 1개",
                    "<할인 전 총주문 금액>",
                    "142,000원",
                    "<증정 메뉴>",
                    "샴페인 1개",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -1,200원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "증정 이벤트: -25,000원",
                    "<총혜택 금액>",
                    "-31,246원",
                    "<할인 후 예상 결제 금액>",
                    "135,754",
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }

    @Test
    void 공백_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException(" ");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_수량_초과_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-10, 바비큐립-10, 초코케이크-5");
            assertThat(output()).contains("[ERROR] 한 번에 최대 20개까지만 주문할 수 있습니다.");
        });
    }

    @Test
    void 날짜_범위_예외_테스트() {
        assertSimpleTest(() -> {
            runException("0", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 음료만_주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-1, 레드와인-1");
            assertThat(output()).contains("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 메뉴를 다시 입력해 주세요.");
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
