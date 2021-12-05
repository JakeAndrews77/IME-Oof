package view;

import model.AbstractModel;

/**
 * Concrete implementation of the view which writes output to the client.
 */
public class View extends AbstractView implements IMEView {

  /**
   * Creates a concrete view that write to the given appendable.
   *
   * @param ap    Appendable to which this view should write output.
   * @param model Model associated with this view.
   */
  public View(Appendable ap, AbstractModel model) {
    super(ap, model);
  }

  /**
   * Writes the model from this view into a PPM file.
   *
   * @param imageName name of the image
   * @param filename  name of file
   */
  @Override
  public void writePPM(String imageName, String filename) {
    final Object o = null;
  }


}
