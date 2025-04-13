package org.codeus.webinar.debugging.app;

import org.codeus.webinar.debugging.app.classification.ClassificationMachine;
import org.codeus.webinar.debugging.app.packaging.PackageMachine;
import org.codeus.webinar.debugging.app.processing.ProductMachine;
import org.codeus.webinar.debugging.app.source.Apple;
import org.codeus.webinar.debugging.app.source.AppleStorage;
import org.codeus.webinar.debugging.app.util.ProductMachineIsNotFoundException;

import java.util.stream.Collector;

public class FactoryFlow {

  private State currentState;

  public FactoryFlow() {
    this.currentState = State.INITIALIZED;
  }

  public State getCurrentState() {
    return currentState;
  }

  public enum State {
    INITIALIZED, PROCESSING, PACKAGING, FINISHED
  }

  private boolean isAppleFresh(Apple apple) {
    return apple.freshness() >= 0.65f;
  }

  public void execute() {
    var classificator = ClassificationMachine.getDefaultClassificationMachine();
    var packager = PackageMachine.getDefaultPackageMachine();
    var rawApplesBatch = AppleStorage.getBatch().limit(100).toList();

    currentState = State.PACKAGING;

    try {
      var packages = rawApplesBatch.stream()
        .filter(this::isAppleFresh)
        .map(classificator::classify)
        .map(ProductMachine::processApple)
        .collect(Collector.of(
          () -> packager,
          PackageMachine::add,
          PackageMachine::combine,
          PackageMachine::finish
        ));


      System.out.println("Report:\n" + packages);
    } catch (ProductMachineIsNotFoundException ex) {
      System.out.printf("Error during processing: %s%n", ex.getMessage());
    }
    currentState = State.FINISHED;
  }
}
