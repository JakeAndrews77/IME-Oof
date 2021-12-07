package model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Mock used to test interactions between the model, view and controller. This mock simulates
 * method calls made to the model.
 */
public class MockModel implements IMEModel {

  private final Appendable ap;
  private ArrayList<String> namesMock;

  public MockModel(Appendable ap) {
    this.ap = ap;
  }


  @Override
  public void addImage(IMEImage image) {
    try {
      this.ap.append(String.format("loaded %s as %s\n", image.getName()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.namesMock.add(image.getName());
  }

  @Override
  public void toGreyScale(Pixel.GreyScaleType type, String fromId, String destId) {
    try {
      ap.append(String.format("Grey scale image %s with component %s, returned as %s\n",
              fromId, type, destId));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void flipHorizontal(String fromId, String destId) {
    try {
      ap.append(String.format("Image %s flipped horizontally, returned as %s\n", fromId, destId));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void flipVertical(String fromId, String destId) {
    try {
      ap.append(String.format("Image %s flipped vertically, returned as %s\n", fromId, destId));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void brighten(int delta, String fromId, String destId) {
    try {
      ap.append(String.format("Brighten image %s by amount %d returned as %s.\n",
              fromId, delta, destId));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void mosaic(int numSeeds, String fromId, String destId) {
    try {
      ap.append(String.format("Mosaic of image %s using %d seeds returned as %s.\n",
              fromId, numSeeds, destId));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public ArrayList<String> getNames() {
    return this.namesMock;
  }

  @Override
  public String toPPMString(String id) {
    try {
      this.ap.append(String.format("PPM String for model: %s\n", id));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public void filter(double[][] kernel, String fromId, String destId) {
    try {
      ap.append(String.format("Image %s filtered, returned as %s ", fromId, destId));
      ap.append("Kernel used:\n" + Arrays.deepToString(kernel) + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void transform(double[][] matrix, String fromId, String destId) {
    try {
      ap.append(String.format("Image %s transformed, returned as %s ", fromId, destId));
      ap.append("Matrix used:\n" + Arrays.deepToString(matrix) + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the image with the identifier given. If no such image exists,
   * an IllegalStateException is thrown.
   *
   * @param id the id of the image
   * @return IME image.
   */
  @Override
  public IMEImage findWithID(String id) {
    return null;
  }


}
