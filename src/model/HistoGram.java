package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a histogram by mapping each picel value to the frequency in an image.
 */
public class HistoGram {
  private final Map<Integer, Integer> redVals;
  private final Map<Integer, Integer> greenVals;
  private final Map<Integer, Integer> blueVals;
  private final Map<Integer, Integer> intensityVals;

  /**
   * Constructs a single histogram to represent the given image.
   *
   * @param image the image used to make this histogram
   */
  public HistoGram(IMEImage image) {

    //initialize hashmaps
    HashMap<Integer, Integer> reds = new HashMap<>();
    HashMap<Integer, Integer> greens = new HashMap<>();
    HashMap<Integer, Integer> blues = new HashMap<>();
    HashMap<Integer, Integer> intensities = new HashMap<>();

    //frequencies of each possible pixel value initialized to zero
    for (int i = 0; i <= image.getMaxVal(); i++) {
      reds.put(i, 0);
      greens.put(i, 0);
      blues.put(i, 0);
      intensities.put(i, 0);
    }

    //iterate all pixels in the image and count all occurrences of each pixel value
    for (ArrayList<Pixel> row : image.getAllPixels()) {
      for (Pixel p : row) {
        reds.merge(p.getRed(), 1, ((counter, one) -> counter + 1));
        greens.merge(p.getGreen(), 1, ((counter, one) -> counter + 1));
        blues.merge(p.getBlue(), 1, ((counter, one) -> counter + 1));
        intensities.merge((int) Math.round((p.getRed() + p.getGreen() + p.getBlue()) / 3.0),
                1, ((counter, one) -> counter + 1));
      }
    }

    //Use the local variables to initialize the immutable hashmaps
    this.redVals = reds;
    this.greenVals = greens;
    this.blueVals = blues;
    this.intensityVals = intensities;
  }


  /**
   * Get the red component data in this histogram.
   *
   * @return the red data in this histogram
   */
  public Map<Integer, Integer> getReds() {
    return this.redVals;
  }

  /**
   * Get the green component data in this histogram.
   *
   * @return the green data in this histogram
   */
  public Map<Integer, Integer> getGreens() {
    return this.greenVals;
  }

  /**
   * Get the blue component data in this histogram.
   *
   * @return the blue data in this histogram
   */
  public Map<Integer, Integer> getBlues() {
    return this.blueVals;
  }

  /**
   * Get the intensity data in this histogram.
   *
   * @return the intensity data in this histogram
   */
  public Map<Integer, Integer> getIntensities() {
    return this.intensityVals;
  }

}
