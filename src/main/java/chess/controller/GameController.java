package chess.controller;

import chess.dto.CreateGameRequest;
import chess.dto.CreateGameResponse;
import chess.dto.DeleteGameRequest;
import chess.dto.GameDto;
import chess.dto.MoveRouteDto;
import chess.service.ChessService;
import chess.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/game")
public class GameController {

    private static final String HTML_TEMPLATE_PATH = "game";

    private final ChessService chessService;

    public GameController(ChessService chessService) {
        this.chessService = chessService;
    }

    @PostMapping("/new")
    public CreateGameResponse createGame(@RequestBody CreateGameRequest createGameRequest) {
        return chessService.initGame(createGameRequest);
    }

    @GetMapping("/{id}")
    public ModelAndView findGame(@PathVariable int id) {
        GameDto gameDto = chessService.findGame(id);
        return ResponseUtil.createModelAndView(HTML_TEMPLATE_PATH, gameDto);
    }

    @PostMapping("/move")
    public ModelAndView playGame(@RequestBody MoveRouteDto moveRoute) {
        chessService.playGame(moveRoute);
        GameDto gameDto = chessService.findGame(moveRoute.getId());
        return ResponseUtil.createModelAndView(HTML_TEMPLATE_PATH, gameDto);
    }

    @PostMapping("/{id}")
    public void delete(@RequestBody DeleteGameRequest deleteGameRequest) {
        chessService.deleteGame(deleteGameRequest);
    }
}
