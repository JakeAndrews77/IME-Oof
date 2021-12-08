import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

import controller.IMEController;
import controller.IMEControllerImp;
import model.AbstractModel;
import model.Model;
import view.IMEView;
import view.View;

/**
 * This is the main class for the IME application. This is where a user is able to input a file
 * and run the application.
 */
public class IME {

  /**
   * Main method of the class where scripts can be read and run.
   *
   * @param args command line inputs.
   */
  public static void main(String[] args) throws FileNotFoundException {
    gui.JFrameView frame;
    IMEController controller;
    AbstractModel model;
    IMEView view;
    Appendable sb = new StringBuilder();

    // This is our additional code, not implemented by the providers, that allows the text version
    // to be attempted to be run. The comment block of code below was originally uncommented, but
    // throws an exception upon running it.
    Readable readable = new InputStreamReader(System.in);
    model = new Model();
    controller = new IMEControllerImp(readable, new View(System.out, model), model);
    controller.runIME();

    // The following code was originally uncommented by our providers, but was error prone so is
    // left out of the program for the time being.


//    if (args[0].equals("-file")) {
//
//      try (BufferedReader br = new BufferedReader(new FileReader(args[1] + ".txt"))) {
//        String line = br.readLine();
//
//        while (line != null) {
//          sb.append(line);
//          sb.append(System.lineSeparator());
//          line = br.readLine();
//        }
//
//        model = new Model();
//        view = new View(sb, model);
//        controller = new IMEControllerImp(new InputStreamReader(System.in), view, model);
//        controller.runIME();
//
//      } catch (IOException exception) {
//        throw new FileNotFoundException("invalid file");
//      }
//    } else if (args[0].equals("-jar Program.jar ")) {
//      model = new Model();
//      view = new View(sb, model);
//      controller = new IMEControllerImp(new InputStreamReader(System.in), view, model);
//      controller.runIME();
//
//      frame = new gui.JFrameView();
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out on application
//
//      // make frame visible
//      frame.setVisible(true);
//    } else {
//      throw new IllegalArgumentException("invalid input");
//    }
//
//  }
  }
}
