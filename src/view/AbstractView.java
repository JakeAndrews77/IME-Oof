package view;

import model.AbstractModel;
import model.IMEModel;

import java.io.IOException;

/**
 * Abstract class for the view.
 */
public abstract class AbstractView implements IMEView {
  IMEModel model;
  Appendable ap;

  /**
   * Creates a view that writes to the provided appendable object.
   * @param ap object where output should be written.
   */
  public AbstractView(Appendable ap, AbstractModel model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void renderMessage(String m) {
    try {
      this.ap.append(m);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
