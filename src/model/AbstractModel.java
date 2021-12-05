package model;

import java.util.ArrayList;

/**
 * Abstraction of model functions to select the correct image
 * and then manipulate it according to the given function.
 */
public abstract class AbstractModel implements IMEModel {

  private ArrayList<IMEImage> images;

  /**
   * Creates a model with no images in it.
   */
  public AbstractModel() {
    this.images = new ArrayList<>();
  }

  @Override
  public void addImage(IMEImage image) {
    this.removeDupeIDs(image.getName());
    this.images.add(image);
  }

  @Override
  public void toGreyScale(Pixel.GreyScaleType type, String fromId, String destId) {
    IMEImage newImage = this.findWithID(fromId).toGreyScale(type, destId);
    this.removeDupeIDs(destId);
    this.images.add(newImage);
  }

  @Override
  public void flipHorizontal(String fromId, String destId) {
    IMEImage newImage = this.findWithID(fromId).flipHorizontal(destId);
    this.removeDupeIDs(destId);
    this.images.add(newImage);
  }

  @Override
  public void flipVertical(String fromId, String destId) {
    IMEImage newImage = this.findWithID(fromId).flipVertical(destId);
    this.removeDupeIDs(destId);
    this.images.add(newImage);
  }


  @Override
  public void brighten(int delta, String fromId, String destId) {
    IMEImage newImage = this.findWithID(fromId).brighten(delta, destId);
    this.removeDupeIDs(destId);
    this.images.add(newImage);
  }

  @Override
  public ArrayList<String> getNames() {
    ArrayList<String> names = new ArrayList<>();
    for (IMEImage i : this.images) {
      names.add(i.getName());
    }
    return names;
  }

  @Override
  public String toPPMString(String id) {
    return this.findWithID(id).toPPMString();
  }

  @Override
  public IMEImage findWithID(String id) {
    for (IMEImage i : this.images) {
      if (i.getName().equals(id)) {
        return i;
      }
    }
    throw new IllegalStateException("image not found");
  }

  protected void removeDupeIDs(String id) {
    this.images.removeIf(i -> i.getName().equals(id));
  }


  @Override
  public void filter(double[][] kernel, String fromId, String destId) {
    IMEImage newImage = this.findWithID(fromId).filter(kernel, destId);
    this.removeDupeIDs(destId);
    this.images.add(newImage);
  }

  @Override
  public void transform(double[][] matrix, String fromId, String destId) {
    IMEImage newImage = this.findWithID(fromId).filter(matrix, destId);
    this.removeDupeIDs(destId);
    this.images.add(newImage);
  }



}
