package model;

import java.util.ArrayList;

/**
 * Basic model to represent a single Image with RGB components mapped to a position.
 */
public class BaseImage extends AbstractImage implements IMEImage {

  /**
   * Creates a BaseModel, with the given name and pixel data.
   *
   * @param id    the name of this model
   * @param pixel hashmap of the pixel RGB vales to their position
   */
  public BaseImage(String id, ArrayList<ArrayList<Pixel>> pixel, int maxVal) {
    super(id, pixel, maxVal);
  }


  @Override
  protected IMEImage createModel(String id, ArrayList<ArrayList<Pixel>> newModel, int maxVal) {
    return new BaseImage(id, newModel, maxVal);
  }

}
