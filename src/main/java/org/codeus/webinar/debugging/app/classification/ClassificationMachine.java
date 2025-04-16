package org.codeus.webinar.debugging.app.classification;

import org.codeus.webinar.debugging.app.source.Apple;

public interface ClassificationMachine {

  ClassifiedApple classify(Apple apple);
}
