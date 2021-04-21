package chess.controller;

import chess.service.SpringChessService;
import chess.service.dto.GameStatusRequestDto;
import chess.service.dto.GameStatusViewDto;
import chess.service.dto.TilesDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChessGameController {

    private final SpringChessService chessService;

    public ChessGameController(final SpringChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/games")
    public String startPage(final Model model) {
        final TilesDto tilesDto = chessService.emptyBoard();
        model.addAttribute("tilesDto", tilesDto);
        return "board";
    }

    @GetMapping("/view")
    public String viewPopUp(final Model model) {
        final List<GameStatusViewDto> gameStatusViewDtos = chessService.roomInfos();
        model.addAttribute("gameStatusViewDtos", gameStatusViewDtos);
        return "view";
    }
}
