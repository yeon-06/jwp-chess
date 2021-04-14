package chess.dao;

import chess.domain.piece.Piece;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class SpringPieceDAO {

    private final JdbcTemplate jdbcTemplate;

    public SpringPieceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Long chessGameId, Piece piece) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO piece(color, shpe, chess_game_id, row, col) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, piece.getColor().toString());
            ps.setString(2, piece.getShape().toString());
            ps.setLong(3, chessGameId);
            ps.setInt(4, piece.getRow());
            ps.setInt(5, piece.getColumn());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    public void saveAll(final Long chessGameId, final List<Piece> pieces) {
        for (final Piece piece : pieces) {
            save(chessGameId, piece);
        }
    }

    public List<Piece> findAllPiecesByChessGameId(Long chessGameId) {
        String query = "SELECT * FROM piece WHERE chess_game_id = ?";
        return jdbcTemplate.queryForList(query, Piece.class, chessGameId);
    }

    public Optional<Piece> findOneByPosition(final Long chessGameId, final int row, final int col) {
        String query = "SELECT * FROM piece WHERE chess_game_id = ? AND row = ? AND col = ?";
        Piece piece = jdbcTemplate.queryForObject(query, Piece.class, chessGameId, row, col);
        return Optional.ofNullable(piece);
    }

    public void update(final Piece piece) {
        String query = "UPDATE piece SET row = ?, col = ? WHERE id = ?";
        jdbcTemplate.update(query, piece.getRow(), piece.getColor(), piece.getId());
    }

    public void delete(final Long chessGameId, final int row, final int col) {
        String query = "DELETE FROM piece WHERE chess_game_id = ? AND row = ? AND col = ?";
        jdbcTemplate.update(query, chessGameId, row, col);
    }

}
