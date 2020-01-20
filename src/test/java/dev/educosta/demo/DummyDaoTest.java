package dev.educosta.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * See https://stackoverflow.com/questions/59024184/spring-boot-with-testcontainers-how-to-prevent-db-initialization-on-context-re
 */
@SpringBootTest
@ContextConfiguration
public class DummyDaoTest {

  public static PostgreSQLContainer postgreSQLContainer = CustomPostgresContainer.getInstance();

  @Autowired
  DummyDao dao;

  @Test
  void injectedComponentsAreNotNull() {
    assertNotNull(dao);
  }

  @Test
  void save_new_value() {
    Dummy value = new Dummy(1000, "dummy");
    int affectedRows = dao.save(value);
    assertEquals(1, affectedRows);
  }


  @Test
  void read_all() {
    assertThat(dao.readAll()).isNotEmpty();
  }

}
