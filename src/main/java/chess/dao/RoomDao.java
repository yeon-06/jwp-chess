package chess.dao;

import chess.domain.piece.Team;
import chess.dto.RoomDto;
import chess.dto.RoomsDto;
import chess.dto.TurnDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomDao {
    private static final String UPDATE_ROOM_QUERY = "update room set turn = ? where room_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public RoomDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createRoom(String roomName) {
        String sql = "insert into room (room_name, turn) values (?, 'white');";
        jdbcTemplate.update(sql, roomName);
        return findRoomIdByRoomName(roomName);
    }

    public boolean isExistName(String roomName) {
        String sql = "select count(*) from room where room_name = ?;";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, roomName);
        return count == 0;
    }

    private int findRoomIdByRoomName(String roomName) {
        String sql = "select room_id from room where room_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, roomName);
    }

    public TurnDto findTurnOwnerByRoomId(int roomId) {
        String sql = "select turn from room where room_id = ?;";
        String turnOwner = jdbcTemplate.queryForObject(sql, String.class, roomId);
        return TurnDto.of(turnOwner);
    }

    public void updateTurnOwnerAfterMove(Team turnOwner, int roomId) {
        jdbcTemplate.update(UPDATE_ROOM_QUERY, turnOwner.getTeamString(), roomId);
    }

    public void resetTurnOwner(int roomId) {
        jdbcTemplate.update(UPDATE_ROOM_QUERY, "white", roomId);
    }

    public RoomsDto findRoomList() {
        String sql = "select * from room;";
        List<RoomDto> roomDtos = jdbcTemplate.query(
                sql, (rs, rn) -> {
                    String roomName = rs.getString("room_name");
                    int roomId = rs.getInt("room_id");
                    return RoomDto.of(roomName, roomId);
                });
        return RoomsDto.of(roomDtos);
    }
}