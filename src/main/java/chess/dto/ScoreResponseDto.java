package chess.dto;

import chess.domain.game.Result;

public class ScoreResponseDto {
    private final double blackScore;
    private final double whiteScore;

    public ScoreResponseDto(Result result) {
        this.blackScore = result.getBlackScore();
        this.whiteScore = result.getWhiteScore();
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}