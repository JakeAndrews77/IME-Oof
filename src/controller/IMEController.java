package controller;

/**
 * Controller receives user input and delegates to the correct image model and view.
 */
public interface IMEController {

  /**
   * Receives input to this controller and delegate to the proper method call in the model
   * and view.
   */
  void runIME();
}
