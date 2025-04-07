package org.codeus.webinar.debugging.app.classification;

import org.codeus.webinar.debugging.app.classification.ClassifiedApple.AppleType;
import org.codeus.webinar.debugging.app.source.Apple;

public class ClassificationMachine {

  public static ClassificationMachine getDefaultClassificationMachine() {
    return new ClassificationMachine();
  }

  public ClassifiedApple classify(Apple apple) {
    AppleType appleType;
    if (apple.color() == Apple.Color.YELLOW && apple.size() == Apple.Size.SMALL) {
      appleType = AppleType.GINGER;
    } else if (apple.color() == Apple.Color.RED && apple.size() == Apple.Size.LARGE) {
      appleType = AppleType.ENVY;
    } else if (apple.color() == Apple.Color.GREEN && apple.size() == Apple.Size.SMALL) {
      appleType = AppleType.MUTSU;
    } else if (apple.color() == Apple.Color.GREEN && apple.size() == Apple.Size.MEDIUM) {
      appleType = AppleType.GRANNY_SMITH;
    } else if (apple.color() == Apple.Color.YELLOW && apple.size() == Apple.Size.LARGE) {
      appleType = AppleType.GOLDEN;
    } else if (apple.color() == Apple.Color.RED && apple.size() == Apple.Size.SMALL) {
      appleType = AppleType.GALA;
    } else if (apple.color() == Apple.Color.RED && apple.size() == Apple.Size.MEDIUM) {
      appleType = AppleType.FUJI;
    } else {
      appleType = AppleType.UNKNOWN;
    }

    return new ClassifiedApple(appleType, apple);
  }
}
