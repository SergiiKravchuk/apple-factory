package org.codeus.webinar.debugging.app.processing;

public record ProcessedApple(ProductType productType, double volume) {

  public enum ProductType {
    JUICE, CIDER
  }
}
