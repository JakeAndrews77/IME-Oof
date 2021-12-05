package view;

import java.io.IOException;

/**
 * Represents a mock class for the view implementation. This class is supposed to act
 * as a mock to help us further test our code.
 */
public class MockView implements IMEView {
  Appendable ap;

  public MockView(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void renderMessage(String m) {
    try {
      this.ap.append(m);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writePPM(String imageName, String filename) {
    try {
      this.ap.append("The image" + imageName + "was written to" + filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
