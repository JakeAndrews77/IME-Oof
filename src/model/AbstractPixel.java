package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Abstract class for a Pixel. This is where we can reuse code
 * for any class that we want to represent as a Pixel Object.
 */
public abstract class AbstractPixel implements Pixel {


  // This refactoring of the override on .equals now allows a GreyScalePixel to be considered equal
  // to an RGBPixel with R G and B values all the same.
  @Override
  public boolean equals(Object o) {

    if (!(o instanceof Pixel)) {
      return false;
    } else {
      return ((Pixel) o).getRed() == this.getRed()
              && ((Pixel) o).getGreen() == this.getGreen()
              && ((Pixel) o).getBlue() == this.getBlue();
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getRed(), this.getBlue(), this.getGreen());
  }

  /**
   * Checks to make sure the values are within their set parameters.
   *
   * @param component RGB component
   * @param delta     value
   * @param maxValue  maximum value
   * @return the value of the component and delta
   */
  protected int brightenHelper(int component, int delta, int maxValue) {
    if (component + delta < 0) {
      return 0;
    }
    if (component + delta > maxValue) {
      return maxValue;
    }

    return component + delta;
  }

  @Override
  public Pixel filter(double[][] filter, Pixel[][] kernel, int bits) {
    if (filter.length % 2 == 0 || filter[0].length % 2 == 0) {
      throw new IllegalArgumentException("Filter Dimensions must be odd");
    }
    if (filter.length != kernel.length || filter[0].length != kernel[0].length) {
      throw new IllegalArgumentException("Filter and kernel must have equal dimensions");
    }

    // 2d to 1d array
    ArrayList<Integer> filter1d = new ArrayList<>();
    ArrayList<Pixel> kernel1d = new ArrayList<>();

    for (int i = 0; i < filter.length; i++) {
      for (int j = 0; j < filter[i].length; j++) {
        filter1d.add((int) Math.round(filter[i][j]));
        kernel1d.add(kernel[i][j]);
      }
    }

    // Pixel values
    ArrayList<Integer> pixelReds = new ArrayList<>();
    ArrayList<Integer> pixelGreens = new ArrayList<>();
    ArrayList<Integer> pixelBlues = new ArrayList<>();

    for (Pixel pix : kernel1d) {
      pixelReds.add(pix.getRed());
      pixelGreens.add(pix.getGreen());
      pixelBlues.add(pix.getBlue());
    }

    //value found by doing dot product of filter1d and pixel values of each component
    int redSum = 0;
    int greenSum = 0;
    int blueSum = 0;

    for (int i = 0; i < filter1d.size(); i++) {
      redSum = redSum + pixelReds.get(i) * filter1d.get(i);
      greenSum = greenSum + pixelGreens.get(i) * filter1d.get(i);
      blueSum = blueSum + pixelBlues.get(i) * filter1d.get(i);
    }

    return new RGBPixel(redSum, greenSum, blueSum).brighten(0, bits);
  }

  @Override
  public Pixel transform(double[][] matrix, int bits) {
    if (matrix.length != 3
            || matrix[0].length != 3
            || matrix[1].length != 3 || matrix[2].length != 3) {
      throw new IllegalArgumentException("Transform matrix must be a 3 x 3 matrix");
    }

    int red = (int) Math.round((matrix[0][0] * this.getRed())
            + (matrix[0][1] * this.getGreen())
            + (matrix[0][2] * this.getBlue()));
    int blue = (int) Math.round((matrix[1][0] * this.getRed())
            + (matrix[1][1] * this.getGreen())
            + (matrix[1][2] * this.getBlue()));
    int green = (int) Math.round((matrix[2][0] * this.getRed())
            + (matrix[2][1] * this.getGreen())
            + (matrix[2][2] * this.getBlue()));

    //brightening by zero abstracts the code that accounts for saturation should that occur
    return new RGBPixel(red, green, blue).brighten(0, bits);
  }
}
