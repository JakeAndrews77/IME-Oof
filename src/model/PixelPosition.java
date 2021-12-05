package model;

import java.util.Objects;

/**
 * Class that represents the postion of a pixel in a 2D plane.
 */
public class PixelPosition {
  private final int x;
  private final int y;

  /**
   * creates a pixel position object.
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public PixelPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof PixelPosition)) {
      return false;
    } else {
      return (((PixelPosition) o).x == this.x
              &&
              ((PixelPosition) o).y == this.y);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }


  ///THIS CLASS HAS BEEN MADE OBSOLETE BY DESIGN CHANGES FOR IMAGES
}
