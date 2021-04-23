package chess.domain.state;

import chess.exception.InvalidStateException;

public class EndWithoutGame implements State {
    public EndWithoutGame() {
        super();
    }

    @Override
    public State start() {
        throw new InvalidStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State end() {
        throw new InvalidStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State status() {
        throw new InvalidStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public State move(boolean isKingDead) {
        throw new InvalidStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }

    @Override
    public boolean isNotRunning() {
        throw new InvalidStateException("시스템 에러 - 잘못된 상태 호출입니다.");
    }
}