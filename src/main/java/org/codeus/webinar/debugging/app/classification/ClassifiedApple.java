package org.codeus.webinar.debugging.app.classification;

import org.codeus.webinar.debugging.app.source.Apple;

public record ClassifiedApple(AppleType type, Apple apple) {

  public enum AppleType {
    //Regular Juice:
    GINGER(0.80f),
    ENVY(0.95f),
    MUTSU(0.90f),
    GRANNY_SMITH(0.85f),
    UNKNOWN(0.7F),

    //Cider:
    GOLDEN(0.83f),
    GALA(0.89f),
    FUJI(0.95f),
    ;

    private final float modifier;

    AppleType(float modifier) {
      this.modifier = modifier;
    }

    public float getModifier() {
      return modifier;
    }
  }
}


