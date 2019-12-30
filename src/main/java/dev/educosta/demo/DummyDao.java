package dev.educosta.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DummyDao {

  @Autowired
  private JdbcTemplate template;

  public Dummy findDummyById(int id) {
    return template
        .queryForObject("SELECT * FROM dummy_test where id=?", new Object[]{id},
            new DummyRowMapper());
  }

  public List<Dummy> readAll() {
    return template.query("SELECT id,name_value FROM dummy_test", new DummyRowMapper());
  }

  public int save(Dummy dummy) {
    return template.update("INSERT INTO dummy_test (id,name_value) VALUES (?,?) ",
        new Object[]{dummy.getId(), dummy.getName()});
  }

  static class DummyRowMapper implements RowMapper<Dummy> {
    @Override
    public Dummy mapRow(ResultSet rs, int rowNumber) throws SQLException {
      return new Dummy(rs.getInt("id"), rs.getString("name_value"));
    }
  }
}
