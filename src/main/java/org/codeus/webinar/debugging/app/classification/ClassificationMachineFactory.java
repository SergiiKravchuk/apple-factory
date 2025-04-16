package org.codeus.webinar.debugging.app.classification;

public class ClassificationMachineFactory {

  public static ClassificationMachine getDefaultClassificationMachine() {
    return new BaseClassificationMachine();
  }
}
