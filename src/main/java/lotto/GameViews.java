package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

// InputView와 OutputView를 캡슐화하여 컨트롤러의 인스턴스 변수 개수를 줄이기 위한 클래스

public class GameViews {
    private final InputView inputView;
    private final OutputView outputView;

    public GameViews(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public InputView inputView() {
        return inputView;
    }

    public OutputView outputView() {
        return outputView;
    }
}
