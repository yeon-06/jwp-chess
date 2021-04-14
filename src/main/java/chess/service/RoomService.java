package chess.service;

import chess.dao.RoomDAO;
import chess.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class RoomService {

    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<RoomDTO> allRooms() {
        return roomDAO.allRooms();
    }

    public void createRoom(final String name) {
        roomDAO.createRoom(name);
    }

    public void changeStatus(final String roomId) {
        roomDAO.changeStatusEndByRoomId(roomId);
    }

    public List<String> allRoomsId() {
        return roomDAO.allRoomIds();
    }
}
