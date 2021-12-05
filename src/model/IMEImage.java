package model;


import java.util.ArrayList;

/**
 * Interface to maintain data for a single image.
 * Each image has an identifier and a two-dimensional representation of its pixel data
 */
public interface IMEImage {

  /**
   * Get the name of this model.
   *
   * @return the name
   */
  String getName();

  /**
   * Convert the image in this model to a GreyScale Image using the specified component
   * or method of determining brightness.
   *
   * @param type The method for converting the image to greyscale.
   * @return A new model with the greyscale image
   */
  IMEImage toGreyScale(Pixel.GreyScaleType type, String id);

  /**
   * Flips this image horizontally.
   *
   * @return A new model with the flipped image
   */
  IMEImage flipHorizontal(String id);

  /**
   * Flips this image vertically.
   *
   * @return The flipped image
   */
  IMEImage flipVertical(String id);

  /**
   * Brightens the image of this model by the given amount.
   *
   * @param delta Amount by which to brighten the image. To darken, input a negative integer.
   * @return a new model with the brightened image.
   */
  IMEImage brighten(int delta, String id);

  /**
   * Returns a PPM string representing this image.
   *
   * @return A string in PPM format for this image.
   */
  String toPPMString();

  /**
   * Applies the given filter to the image and returns the result.
   *
   * @param vals the filter that should be applied
   * @return the result of the filter
   */
  IMEImage filter(double[][] vals, String id);

  /**
   * Returns the result of the given image transformation applied to this image.
   *
   * @return transformed image
   */
  IMEImage transform(double[][] matrix, String id);

  /**
   * Fetch and return all of the pixels in this image.
   * @return The pixels of this image.
   */
  ArrayList<ArrayList<Pixel>> getAllPixels();

  /**
   * Fetch and return the maximum value of any pixel in this image.
   * @return the highest integer value among all components in this image
   */
  int getMaxVal();
}
