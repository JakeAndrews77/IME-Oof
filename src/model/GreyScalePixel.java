package model;

import java.util.Objects;

/**
 * Class to represent a pixel using a single value for its brightness
 * in a shade of grey. ie. a GreyScale representation.
 */
public class GreyScalePixel extends AbstractPixel implements Pixel {
  protected final int scale;

  /**
   * Creates a new GreyScalePixel with the specified brightness.
   *
   * @param scale the brightness and darkness of this image
   */
  public GreyScalePixel(int scale) {
    this.scale = scale;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GreyScalePixel)) {
      return false;
    } else {
      return (((GreyScalePixel) o).scale == this.scale);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.scale);
  }

  @Override
  public Pixel brighten(int delta, int bits) {
    int maxVal = ((int) Math.pow(2, bits)) - 1;
    return new GreyScalePixel(brightenHelper(this.scale, delta, maxVal));
  }

  @Override
  public String toPPMString() {
    return String.format("%d\r\n%d\r\n%d\r\n", this.scale, this.scale, this.scale);
  }


  @Override
  public GreyScalePixel toGreyScale(GreyScaleType type) {
    return this;
  }


  @Override
  public int getRed() {
    return this.scale;
  }

  @Override
  public int getGreen() {
    return this.scale;
  }

  @Override
  public int getBlue() {
    return this.scale;
  }

}
