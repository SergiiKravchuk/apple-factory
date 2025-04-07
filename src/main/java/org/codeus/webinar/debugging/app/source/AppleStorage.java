package org.codeus.webinar.debugging.app.source;

import org.instancio.Instancio;
import org.instancio.Model;

import java.util.stream.Stream;

import static org.instancio.Select.field;

public class AppleStorage {

  public static final Model<Apple> appleModel = Instancio.of(Apple.class)
    .generate(field(Apple::size), gen -> gen.enumOf(Apple.Size.class))
    .generate(field(Apple::color), gen -> gen.enumOf(Apple.Color.class))
    .supply(field(Apple::freshness), gen -> gen.floatRange(0.5f, 1.0f))
    .toModel();

  public static Stream<Apple> getBatch() {
    return Instancio.of(appleModel).stream();
  }
}
