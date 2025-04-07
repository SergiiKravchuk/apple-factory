package org.codeus.webinar.debugging.app.source;

public record Apple(Color color, Size size, float freshness) {

  public enum Color {GREEN, YELLOW, RED}

  public enum Size {
    SMALL(70),
    MEDIUM(100),
    LARGE(130);

    private final int volume;

    Size(int volume) {
      this.volume = volume;
    }

    public int getVolume() {
      return volume;
    }
  }
}
