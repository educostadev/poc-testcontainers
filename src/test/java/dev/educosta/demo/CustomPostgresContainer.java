package dev.educosta.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;

public class CustomPostgresContainer extends PostgreSQLContainer<CustomPostgresContainer> {

  private static final Logger logger = LoggerFactory.getLogger(CustomPostgresContainer.class);

  private static final String IMAGE_VERSION = "postgres:alpine";
  private static CustomPostgresContainer container;

  private CustomPostgresContainer() {
    super(IMAGE_VERSION);
  }

  public static CustomPostgresContainer getInstance() {
    if (container == null) {
      container = new CustomPostgresContainer();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    logger.debug("POSTGRES INFO");
    logger.debug("DB_URL: " + container.getJdbcUrl());
    logger.debug("DB_USERNAME: " + container.getUsername());
    logger.debug("DB_PASSWORD: " + container.getPassword());
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());


  }

  @Override
  public void stop() {
    //do nothing, JVM handles shut down
  }
}