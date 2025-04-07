package org.codeus.webinar.debugging.app.processing;

import org.codeus.webinar.debugging.app.classification.ClassifiedApple;
import org.codeus.webinar.debugging.app.classification.ClassifiedApple.AppleType;
import org.codeus.webinar.debugging.app.util.ProductMachineIsNotFoundException;

import java.util.List;
import java.util.Set;

public abstract class ProductMachine {

  public static ProcessedApple processApple(ClassifiedApple classifiedApple) {
    ProductMachine targetProductMachine = getProductMachines().stream()
      .filter(productMachine -> productMachine.canProcess(classifiedApple))
      .findFirst()
      .orElseThrow(() ->
        new ProductMachineIsNotFoundException("Product machine is not found for AppleType: %s".formatted(classifiedApple.type())));

    ProcessedApple processedApple = targetProductMachine.process(classifiedApple);
    System.out.println("Processed Apple=" + processedApple);
    return processedApple;
  }

  public static List<ProductMachine> getProductMachines() {
    return List.of(
      new JuiceProductMachine(),
      new CiderProductMachine()
    );
  }

  protected abstract boolean canProcess(ClassifiedApple classifiedApple);

  protected abstract ProcessedApple process(ClassifiedApple classifiedApple);
}













class JuiceProductMachine extends ProductMachine {

  private static final float productModifier = 0.95f;
  private final Set<AppleType> targetAppleTypes = Set.of(
    AppleType.GINGER,
    AppleType.ENVY,
    AppleType.MUTSU,
    AppleType.GRANNY_SMITH,
    AppleType.UNKNOWN
  );

  @Override
  protected boolean canProcess(ClassifiedApple classifiedApple) {
    return targetAppleTypes.contains(classifiedApple.type());
  }

  @Override
  public ProcessedApple process(ClassifiedApple classifiedApple) {
    var rawApple = classifiedApple.apple();
    var processedVolume = classifiedApple.type().getModifier() * rawApple.size().getVolume() * rawApple.freshness() * productModifier;

    return new ProcessedApple(ProcessedApple.ProductType.JUICE, processedVolume);
  }
}












class CiderProductMachine extends ProductMachine {

  private static final float productModifier = 0.7f;
  private final Set<AppleType> targetAppleTypes = Set.of(
    AppleType.GOLDEN,
    AppleType.GALA,
    AppleType.FUJI
  );

  @Override
  protected boolean canProcess(ClassifiedApple classifiedApple) {
    return targetAppleTypes.contains(classifiedApple.type());
  }

  @Override
  public ProcessedApple process(ClassifiedApple classifiedApple) {
    var rawApple = classifiedApple.apple();
    var processedVolume = classifiedApple.type().getModifier() * rawApple.size().getVolume() * rawApple.freshness() * productModifier;

    return new ProcessedApple(ProcessedApple.ProductType.CIDER, processedVolume);
  }
}