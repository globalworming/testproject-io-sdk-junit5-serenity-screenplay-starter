package com.headissue.example;

import io.testproject.sdk.drivers.ReportingDriver;
import net.serenitybdd.core.listeners.AbstractStepListener;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class TestprojectStepListener extends AbstractStepListener {

  private final ReportingDriver driver;
  private Stack<ExecutedStepDescription> currentStepStack = new Stack<>();

  public TestprojectStepListener(ReportingDriver driver) {

    this.driver = driver;
  }

  public void testStarted(String s, String s1) {}

  @Override
  public void testFinished(TestOutcome result, boolean isInDataDrivenTest) {
    if (!result.isSuccess()) {
      throw new RuntimeException(result.getTestFailureCause().getOriginalCause());
    }
  }

  @Override
  public void exampleStarted(Map<String, String> data, String exampleName) {}

  @Override
  public void testRunFinished() {}

  @Override
  public void stepFailed(StepFailure failure) {
    driver
        .report()
        .step(
            failure.getMessage(),
            failure.getException().getMessage()
                + "\n"
                + Arrays.stream(failure.getStackTraceElements())
                    .map(StackTraceElement::toString)
                    .collect(Collectors.joining("\n")),
            false,
            false);
  }

  @Override
  public void stepFinished() {
    if (!currentStepStack.empty()) {
      ExecutedStepDescription description = currentStepStack.pop();
      driver.report().step("finished: " + description.getTitle(), description.getName(), true);
    }
  }

  @Override
  public void stepStarted(ExecutedStepDescription description) {
    currentStepStack.push(description);
    driver.report().step("started: " + description.getTitle(), description.getName(), true);
  }

  @Override
  public void stepIgnored() {}

  public void skippedStepStarted(ExecutedStepDescription description) {
    driver.report().step("ignored: " + description.getTitle(), description.getName(), false, false);
  }
}
