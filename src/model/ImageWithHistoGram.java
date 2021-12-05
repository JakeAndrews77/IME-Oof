package model;


import java.util.ArrayList;

/**
 * Concrete Image Class with a field containing its histogram.
 */
public class ImageWithHistoGram extends AbstractImage implements IMEImage {

  /**
   * Creates an ImageWithHistoGram that has the given id, pixels and maximumValue.
   *
   * @param id     name assigned to this image
   * @param pixels Array of pixels in this image
   * @param maxVal Maximum value a pixel in this image has.
   */
  public ImageWithHistoGram(String id, ArrayList<ArrayList<Pixel>> pixels, int maxVal) {
    super(id, pixels, maxVal);
    HistoGram histoGram = new HistoGram(new BaseImage(id, pixels, maxVal));
  }


  @Override
  protected IMEImage createModel(String id, ArrayList<ArrayList<Pixel>> pixels, int maxVal) {
    return new ImageWithHistoGram(id, pixels, maxVal);
  }
}
