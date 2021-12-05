package model;

/**
 * This class stores the RGB data of a single pixel. It contains methods to return the value,
 * (maximum of RGB), intensity (average), or luma of the pixel. All methods return as an int so,
 * that their results may be used to produce a greyscale image.
 */
public class RGBPixel extends AbstractPixel implements Pixel {
  private final int red;
  private final int green;
  private final int blue;


  /**
   * Constructs a pixel with the specified RGB components.
   *
   * @param red   The red component
   * @param blue  The blue component
   * @param green the green component
   */
  public RGBPixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;

  }


  @Override
  public Pixel brighten(int delta, int bits) {

    int maxVal = ((int) Math.pow(2, bits)) - 1;

    return new RGBPixel(brightenHelper(this.red, delta, maxVal),
            brightenHelper(this.blue, delta, maxVal),
            brightenHelper(this.green, delta, maxVal));

  }

  @Override
  public String toPPMString() {
    return String.format("%d\r\n%d\r\n%d\r\n", this.red, this.green, this.blue);
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  /**
   * Obtain the value of this pixel. (defined as the maximum of its components).
   */
  private int getValue() {
    return Math.max(Math.max(this.red, this.green), this.blue);
  }


  /**
   * Obtain the intensity of this pixel. (defined as the average of its components)
   */
  private int getIntensity() {
    return Math.toIntExact(Math.round((this.red + this.blue + this.green) / 3.0));
  }

  /**
   * Obtain the Luma of this image. (Defined by the following formula)
   * 0.2126r+0.7152g+0.0722b
   */
  private int getLuma() {
    return Math.toIntExact(Math.round((0.2126 * this.red)
            + (0.7152 * this.green) + (0.0722 * this.blue)));
  }

  @Override
  public GreyScalePixel toGreyScale(GreyScaleType type) throws IllegalArgumentException {
    switch (type) {
      case RED:
        return new GreyScalePixel(this.red);
      case GREEN:
        return new GreyScalePixel(this.green);
      case BLUE:
        return new GreyScalePixel(this.blue);
      case VALUE:
        return new GreyScalePixel(this.getValue());
      case INTENSITY:
        return new GreyScalePixel(this.getIntensity());
      case LUMA:
        return new GreyScalePixel(this.getLuma());
      default:
        throw new IllegalArgumentException("no such value");
    }

  }

}
