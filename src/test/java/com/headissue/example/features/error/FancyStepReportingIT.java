package com.headissue.example.features.error;

import com.headissue.example.TestBase;
import com.headissue.example.screenplay.actions.GivesFiveStars;
import com.headissue.example.screenplay.actions.WatchDemoVideo;
import com.headissue.example.screenplay.page.Home;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import net.serenitybdd.screenplay.actions.Open;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.io.IOException;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

@EnabledIfEnvironmentVariable(named = "TP_DEV_TOKEN", matches = ".*")
public class FancyStepReportingIT extends TestBase {

  @BeforeAll
  protected static void setup()
      throws InvalidTokenException, AgentConnectException, ObsoleteVersionException, IOException {
    TestBase.setupAll();
    driver.report().disableCommandReports(true);
  }

  @Test
  @DisplayName("assertion fails")
  void whenAssertionFails() {
    driver.report().disableCommandReports(true);
    checker.attemptsTo(Open.browserOn(new Home()));
    checker.should(seeThat(the(".doesNotExist"), isVisible()));
  }

  @Test
  @DisplayName("assertion fails, skipped steps are reported")
  void whenAssertionFailsThenNoteSkipped() {
    checker.attemptsTo(Open.browserOn(new Home()));
    checker.should(seeThat(the(".doesNotExist"), isVisible()));
    checker.attemptsTo(new WatchDemoVideo());
    checker.attemptsTo(new GivesFiveStars());
  }

  @Test
  @DisplayName("report standard exception")
  void reportException() throws Exception {
    checker.attemptsTo(Open.browserOn(new Home()));
    checker.attemptsTo(new WatchDemoVideo());
    if (true) throw new Exception("When standard exception is reported");
    checker.attemptsTo(new GivesFiveStars());
  }
}
