package com.headissue.example.features.error;

import com.headissue.example.TestBase;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@EnabledIfEnvironmentVariable(named = "TP_DEV_TOKEN", matches = ".*")
public class DefaultErrorReporting extends TestBase {

  @Test
  @DisplayName("assertion fails")
  void whenAssertionFails() {
    driver.report().step("Simple Step");
    fail("This test failed");
  }

  /** TODO in testproject report actual: "failed" expected: "aborted"? */
  @Test
  @DisplayName("assume fails")
  void whenAssumeFails() {
    driver.report().step("Simple Step");
    Assumptions.assumeTrue(false);
  }

  @Test
  @DisplayName("report standard exception")
  void reportException() throws Exception {
    driver.navigate().to("http://example.testproject.io/");
    driver.report().step("Some Step");
    throw new Exception("When standard exception is reported");
  }

  @Nested
  /** TODO in testproject report actual: "success" expected: "failed"? */
  class NestedClass {

    @Test
    @DisplayName("when assertion fails in nested")
    void whenAssertionFailsInNested() {
      driver.navigate().to("http://example.testproject.io/");
      String title = driver.getTitle();
      assertEquals("another title", title);
    }

    @Test
    @DisplayName("report standard exception in nested")
    void reportExceptionInNested() throws Exception {
      driver.navigate().to("http://example.testproject.io/");
      driver.report().step("Some Step");
      throw new Exception("When standard exception is reported");
    }
  }
}
