package org.codeus.webinar.debugging.app.packaging;

import org.codeus.webinar.debugging.app.processing.ProcessedApple;
import org.codeus.webinar.debugging.app.processing.ProcessedApple.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PackageMachine {

  abstract public void add(ProcessedApple apple);
  abstract public List<Package> finish();

  public PackageMachine combine(PackageMachine other) {
    throw new UnsupportedOperationException("Parallel stream not supported, yet.");
  }
}

class BatchPackageMachine extends PackageMachine {
  private final int maxBatchSize;
  private final Map<ProductType, List<ProcessedApple>> batches = new HashMap<>();
  private final List<Package> packages = new ArrayList<>();

  public BatchPackageMachine(int maxBatchSize) {
    this.maxBatchSize = maxBatchSize;
  }

  public void add(ProcessedApple apple) {
    var productType = apple.productType();

    var batch = batches.computeIfAbsent(productType, k -> new ArrayList<>());
    batch.add(apple);

    if (batch.size() == maxBatchSize) flush(productType);
  }

  public List<Package> finish() {
    batches.keySet().forEach(this::flush);
    return packages;
  }

  private void flush(ProductType productType) {
    var batch = batches.get(productType);
    if (batch.isEmpty()) return;

    var batchVolume = batch.stream().mapToDouble(ProcessedApple::volume).sum();

    Package newPackage;
    if (productType == ProductType.CIDER) newPackage = new Bottle(batchVolume);
    else newPackage = new Bag(batchVolume);

    packages.add(newPackage);
    batch.clear();
  }
}