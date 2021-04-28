package chess.controller;

import chess.dto.*;
import chess.service.ChessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChessController {
    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<RoomsDto> showRoomList() {
        RoomsDto roomsDto = chessService.findAll();
        return ResponseEntity.ok().body(roomsDto);
    }

    @GetMapping("/{roomId}/board")
    public ResponseEntity<BoardDto> loadSavedBoard(@PathVariable int roomId) {
        BoardDto boardDto = chessService.findBoardByRoomId(roomId);
        return ResponseEntity.ok().body(boardDto);
    }

    @PostMapping("/room")
    public ResponseEntity<BoardDto> makeRoom(@RequestBody RoomNameDto roomNameDto) {
        BoardDto boardDto = chessService.createRoom(roomNameDto);
        return ResponseEntity.ok().body(boardDto);
    }

    @GetMapping("/{roomId}/reset")
    public ResponseEntity<BoardDto> resetBoard(@PathVariable int roomId) {
        BoardDto boardDto = chessService.resetBoard(roomId);
        return ResponseEntity.ok().body(boardDto);
    }

    @GetMapping("/{roomId}/score")
    public ResponseEntity<ScoreDto> scoreStatus(@PathVariable int roomId) {
        ScoreDto scoreDto = chessService.score(roomId);
        return ResponseEntity.ok().body(scoreDto);
    }

    @PostMapping("/{roomId}/move")
    public ResponseEntity<BoardDto> move(@RequestBody MoveInfoDto moveInfoDto, @PathVariable int roomId) {
        BoardDto boardDto = chessService.move(moveInfoDto, roomId);
        return ResponseEntity.ok().body(boardDto);
    }
}