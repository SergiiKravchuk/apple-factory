package org.codeus.webinar.debugging.app.packaging;

public class PackageMachineFactory {

  public static PackageMachine getDefaultPackageMachine() {
    return new BatchPackageMachine(5);
  }

}
