package model;

import java.util.ArrayList;

/**
 * Abstraction of methods that should generally apply to a model of an image.
 */
public abstract class AbstractImage implements IMEImage {
  private final String identifier;
  private final ArrayList<ArrayList<Pixel>> pixels;
  private final int maxVal;
  private final int bits;

  /**
   * Constructs an IMEModel with the given name and RGB data.
   *
   * @param id    The name of the model
   * @param pixel A hashmap of pixel RGB values to their position in an image.
   */
  AbstractImage(String id, ArrayList<ArrayList<Pixel>> pixel, int maxVal) {
    this.identifier = id;
    this.pixels = pixel;
    this.maxVal = maxVal;

    int currentBits = 0;
    while (maxVal < (int) Math.pow(2, currentBits)) {
      currentBits += 1;
    }
    this.bits = currentBits;
  }

  /**
   * Instantiates a model with the given name and data.
   *
   * @param id     Name for this image
   * @param pixels RGB data of this image
   * @return The model
   */
  protected abstract IMEImage createModel(
          String id, ArrayList<ArrayList<Pixel>> pixels, int maxVal);

  /**
   * Returns the name of this model.
   *
   * @return The name given to this model.
   */
  public String getName() {
    return this.identifier;
  }

  /**
   * Convert the image in this model to a GreyScale Image using the specified component
   * or method of determining brightness.
   *
   * @param type The method for converting the image to greyscale.
   * @return A new model with the greyscale image
   */
  public IMEImage toGreyScale(Pixel.GreyScaleType type, String id) {
    ArrayList<ArrayList<Pixel>> pixel = new ArrayList<>();
    ArrayList<Pixel> pixel1d = new ArrayList<>();

    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(i).size(); j++) {
        pixel1d.set(j, this.pixels.get(i).get(j)).toGreyScale(type);
      }
      pixel.set(i, pixel1d);
    }
    return this.createModel(id, pixel, ((int) Math.pow(2, this.bits)) - 1);
  }

  @Override
  public IMEImage flipHorizontal(String id) {
    ArrayList<ArrayList<Pixel>> pixel = new ArrayList<>();
    ArrayList<Pixel> pixel1d = new ArrayList<>();

    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(i).size(); j++) {
        pixel1d.set(j, this.pixels.get(i).get(this.pixels.get(i).size() - 1 - j));
      }
      pixel.set(i, pixel1d);
    }
    return this.createModel(id, pixel, ((int) Math.pow(2, this.bits)) - 1);
  }

  @Override
  public IMEImage flipVertical(String id) {
    ArrayList<ArrayList<Pixel>> pixel = new ArrayList<>();

    for (int i = 0; i < this.pixels.size(); i++) {
      pixel.set(i, this.pixels.get(this.pixels.size() - 1));
    }
    return this.createModel(id, pixel, ((int) Math.pow(2, this.bits)) - 1);
  }

  @Override
  public IMEImage brighten(int delta, String id) {
    ArrayList<ArrayList<Pixel>> pixel = new ArrayList<>();
    ArrayList<Pixel> pixel1d = new ArrayList<>();

    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(i).size(); j++) {
        pixel1d.set(j, this.pixels.get(i).get(j).brighten(delta, this.bits));
      }
      pixel.set(i, pixel1d);
    }
    return this.createModel(id, pixel, ((int) Math.pow(2, this.bits)) - 1);
  }

  @Override
  public String toPPMString() {
    ArrayList<String> ppms = new ArrayList<>();
    for (int i = 0; i < this.pixels.size(); i++) {
      ppms.add(this.ppmLine(i));
    }
    return String.format("P3\n%d %d\n%d\n",
            this.pixels.get(0).size(),
            this.pixels.size(),
            this.maxVal)
            + String.join("", ppms);
  }

  private String ppmLine(int row) {
    ArrayList<String> ppms = new ArrayList<>();
    for (Pixel pixel : this.pixels.get(row)) {
      ppms.add(pixel.toPPMString());
    }
    return String.join("", ppms);
  }


  @Override
  public IMEImage filter(double[][] vals, String id) {
    if (vals.length % 2 == 0 || vals[0].length % 2 == 0) {
      throw new IllegalArgumentException("Filter Dimensions must be odd");
    }

    ArrayList<ArrayList<Pixel>> filteredPixels = new ArrayList<>();

    for (int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Pixel> pixel1d = new ArrayList<>();
      for (int j = 0; j < this.pixels.get(i).size(); j++) {
        pixel1d.add(this.pixels.get(i).get(j).filter(vals,
                this.formKernel(i, j, vals.length, vals[0].length),
                this.bits));
      }
      filteredPixels.add(pixel1d);
    }

    return this.createModel(id, filteredPixels, this.maxVal);
  }

  private Pixel[][] formKernel(int x, int y, int length, int width) {
    int xOffset = (length - 1) / 2;
    int yOffset = (width - 1) / 2;

    Pixel[][] kernel = new Pixel[length][width];

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        if (x - xOffset + i < 0 || y - yOffset + j < 0
                || x - xOffset + i > this.pixels.size()
                || y - yOffset + j > this.pixels.get(i).size()) {
          kernel[i][j] = new GreyScalePixel(0);
        } else {
          kernel[i][j] = this.pixels.get(x - xOffset + i).get(y - yOffset + j);
        }
      }
    }

    return kernel;
  }

  @Override
  public IMEImage transform(double[][] matrix, String id) {
    if (matrix.length != 3
            || matrix[0].length != 3
            || matrix[1].length != 3
            || matrix[2].length != 3) {
      throw new IllegalArgumentException("Transform matrix must be a 3 x 3 matrix");
    }

    ArrayList<ArrayList<Pixel>> transformPixel = new ArrayList<>();

    for (int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Pixel> pixel1d = new ArrayList<>();
      for (int j = 0; j < this.pixels.get(i).size(); j++) {
        pixel1d.add(this.pixels.get(i).get(j).transform(matrix, this.bits));
      }
      transformPixel.add(pixel1d);
    }

    return this.createModel(id, transformPixel, this.maxVal);
  }

  @Override
  public ArrayList<ArrayList<Pixel>> getAllPixels() {
    return this.pixels;
  }

  @Override
  public int getMaxVal() {
    return this.maxVal;
  }

}
