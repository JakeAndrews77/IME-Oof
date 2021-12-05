package view;

/**
 * Interface to write output from the model of an IME program and display messages to the user.
 */
public interface IMEView {

  /**
   * Displays the provided message to the user.
   *
   * @param m The message that should be rendered.
   */
  void renderMessage(String m);

  /**
   * Writes the model from this view into a PPM file.
   * (method obsolete. Use ImageUtil.saveImage instead)
   */
  void writePPM(String imageName, String filename);


}
