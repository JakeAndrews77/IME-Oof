package model;

/**
 * Interface to store the ppm data contained in a single pixel.
 * Pixel data is immutable but its red, green, and blue data components are callable.
 */
public interface Pixel {

  /**
   * Enumeration for six different types of greyscale images
   * that can be made using data from pixels.
   */
  enum GreyScaleType {
    RED,
    BLUE,
    GREEN,
    VALUE,
    INTENSITY,
    LUMA
  }

  /**
   * Converts this pixel into a greyScale pixel based on the specified component
   * or measure of brightness.
   *
   * @param type Component or measurment method to use for conversion to greyscale.
   * @return A greyscale pixel with the specified component.
   */
  GreyScalePixel toGreyScale(GreyScaleType type);

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  /**
   * Brighten this pixel by the specified amount and return the result.
   *
   * @param delta Amount by which to brighten the image. Negative numbers will darken the image.
   * @param bits  number of bits to represent this pixel
   * @return brightened pixel
   */
  Pixel brighten(int delta, int bits);

  /**
   * Returns a full PPM string for the image with the id given.
   *
   * @return ppm string of this image
   */
  String toPPMString();

  /**
   * Returns the resulting pixel given the filter and the surrounding pixels.
   *
   * @param filter the filter being applied to this pixel
   * @param kernel The pixels surrounding this pixel
   * @return the resulting pixel.
   */
  Pixel filter(double[][] filter, Pixel[][] kernel, int bits);

  /**
   * Transforms this pixel with the given matrix.
   *
   * @param matrix matrix with which to transform this pixel
   * @return the transformed pixel
   */
  Pixel transform(double[][] matrix, int bits);


  /**
   * Returns the red component of this image.
   *
   * @return the red component of this image
   */
  int getRed();

  /**
   * Returns the green component of this image.
   *
   * @return the green component of this image
   */
  int getGreen();

  /**
   * Returns the blue component of this image.
   *
   * @return the blue component of this image
   */
  int getBlue();

}
