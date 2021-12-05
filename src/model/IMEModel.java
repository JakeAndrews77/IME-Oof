package model;

import java.util.ArrayList;

/**
 * Interface to maintain data for a model which may contain many images.
 * Each image will have a two-dimensional representation of its pixel data and an identifier.
 */
public interface IMEModel {

  /**
   * Add the specified image to this model.
   *
   * @param image The image to be added
   */
  void addImage(IMEImage image);

  /**
   * Makes a greyscale image based on the specified component of the given image.
   * Saves result as a specified name and adds it to this model
   *
   * @param type   GreyScale type
   * @param fromId id of the image to use
   * @param destId id given to resultant image
   */
  void toGreyScale(Pixel.GreyScaleType type, String fromId, String destId);

  /**
   * Makes a horizontally flipped image based on the given image.
   * Saves result as a specified name and adds it to this model
   *
   * @param fromId id of the image to use
   * @param destId id given to resultant image
   */
  void flipHorizontal(String fromId, String destId);

  /**
   * Makes a vertically flipped image based on the given image.
   * Saves result as a specified name and adds it to this model
   *
   * @param fromId id of the image to use
   * @param destId id given to resultant image
   */
  void flipVertical(String fromId, String destId);

  /**
   * Makes a brightened image by the given amount based on the given image.
   * Saves result as a specified name and adds it to this model
   *
   * @param fromId id of the image to use
   * @param destId id given to resultant image
   * @param delta  Amount by which to brighten the image. A negative int will darken the image
   */
  void brighten(int delta, String fromId, String destId);

  /**
   * Makes a mosaic of the old image with the given amount of seeds.
   * Saves result as a specified name and adds it to this model
   *
   * @param fromId id of the image to use
   * @param destId id given to resultant image
   * @param delta  Amount of seeds used to create the mosaic.
   */
  void mosaic(int delta, String fromId, String destId);

  /**
   * Returns all the names of images in this model.
   *
   * @return the names of the images
   */
  ArrayList<String> getNames();

  /**
   * Returns the PPM String for the image with the given name.
   */
  String toPPMString(String id);

  /**
   * Applies the given kernel to the image with the specified id.
   * saves result to given destination id.
   *
   * @param kernel kernel to use
   * @param fromId id of image to be transformed
   * @param destId name to save result as
   */
  void filter(double[][] kernel, String fromId, String destId);

  /**
   * Applies the given color transformation to the image with the specified id.
   * saves result to given destination id.
   *
   * @param matrix color transformation to use
   * @param fromId id of image to be transformed
   * @param destId name to save result as
   */
  void transform(double[][] matrix, String fromId, String destId);


  /**
   * Returns the image with the identifier given. If no such image exists,
   * an IllegalStateException is thrown.
   * @param id Identifier to search for in the image
   * @return An image in this model with the id given
   */
  IMEImage findWithID(String id);


}
