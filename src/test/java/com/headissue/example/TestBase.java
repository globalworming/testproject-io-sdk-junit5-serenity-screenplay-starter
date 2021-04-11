package com.headissue.example;

import com.headissue.example.screenplay.actor.Memories;
import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.interfaces.junit5.ExceptionsReporter;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.steps.BaseStepListener;
import net.thucydides.core.steps.StepEventBus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Instant;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class TestBase implements ExceptionsReporter {

  protected static volatile ChromeDriver driver;
  protected static Actor checker = new Actor("Tester");

  @BeforeAll
  protected static void setupAll()
      throws InvalidTokenException, AgentConnectException, ObsoleteVersionException, IOException {
    initDriver();
    registerListener();
  }

  private static void initDriver()
      throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException {
    driver = new ChromeDriver(new ChromeOptions(), "Screenplay Example", Instant.now().toString());
    await().atMost(5, SECONDS).until(() -> driver.getSessionId() != null);
  }

  private static void registerListener() {
    StepEventBus.getEventBus().registerListener(new BaseStepListener(null));
    StepEventBus.getEventBus().registerListener(new TestprojectStepListener(driver));
  }

  private static void setupActor() {
    checker.can(BrowseTheWeb.with(driver));
    checker.remember(Memories.NAME, "John Smith");
    checker.remember(Memories.PASSWORD, "12345");
  }

  @AfterAll
  static void tearDownAll() {
    if (driver != null) {
      driver.quit();
    }
  }

  @BeforeEach
  void setUp() {
    setupActor();
  }

  @AfterEach
  void tearDown() {
    StepEventBus.getEventBus().testFinished();
  }

  public ReportingDriver getDriver() {
    return driver;
  }
}
