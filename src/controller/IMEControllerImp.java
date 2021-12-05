package controller;

import java.io.IOException;
import java.util.Scanner;

import imageutils.ImageUtil;
import model.IMEImage;
import model.IMEModel;
import model.Pixel;
import view.IMEView;

/**
 * Controller class for the Image Processor.
 */
public class IMEControllerImp implements IMEController {
  private Readable readable;
  private IMEModel model;
  private IMEView view;

  /**
   * Instantiates a new Image Controller.
   *
   * @param rd    Readable from which to read client input
   * @param view  View which will output messages to a client
   * @param model Model to be handled in this controller
   * @throws IllegalArgumentException throws exception if readable, view, or model is null
   */
  public IMEControllerImp(Readable rd,
                          IMEView view,
                          IMEModel model) throws IllegalArgumentException {
    if (rd == null || view == null || model == null) {
      throw new IllegalArgumentException("readable model or view is null!");
    }
    this.readable = rd;
    this.view = view;
    this.model = model;
  }

  /**
   * Receives input to this controller and delegate to the proper method call in the model
   * and view.
   */
  @Override
  public void runIME() {

    this.view.renderMessage("Welcome to this Image Processor\n");
    this.listImages();
    boolean programQuit = false;
    int delta = 0;

    Scanner inScan = new Scanner(this.readable);

    while (!programQuit) {

      String command = inScan.next();

      if (command.equals("brighten")) {
        try {
          delta = Integer.parseInt(inScan.next());
        } catch (NumberFormatException n) {
          this.view.renderMessage("brighten requires an int argument");
        }
      }

      String imageName = inScan.next(); // will be a filepath for load-image and save-image commands
      String destName = inScan.next();

      if (this.model.getNames().contains(destName)) {
        this.view.renderMessage(String.format("Image %s will be overwritten", destName));
      }

      //You can add cases to this switch to support more new user commands.
      switch (command) {
        case "load-image":
          try {
            IMEImage newImage = ImageUtil.readAnyImage(imageName, destName);
            this.model.addImage(newImage);
          } catch (IOException e) {
            this.view.renderMessage(
                    String.format("IO Error occurred. Check that %s exists", imageName));
          }
          break;
        case "red-component-image":
          this.model.toGreyScale(Pixel.GreyScaleType.RED, imageName, destName);
          break;
        case "green-component-image":
          this.model.toGreyScale(Pixel.GreyScaleType.GREEN, imageName, destName);
          break;
        case "blue-component-image":
          this.model.toGreyScale(Pixel.GreyScaleType.BLUE, imageName, destName);
          break;
        case "value-component-image":
          this.model.toGreyScale(Pixel.GreyScaleType.VALUE, imageName, destName);
          break;
        case "intensity-component-image":
          this.model.toGreyScale(Pixel.GreyScaleType.INTENSITY, imageName, destName);
          break;
        case "luma-component-image":
          this.model.toGreyScale(Pixel.GreyScaleType.LUMA, imageName, destName);
          break;
        case "horizontal-flip":
          this.model.flipHorizontal(imageName, destName);
          break;
        case "vertical-flip":
          this.model.flipVertical(imageName, destName);
          break;
        case "brighten":
          this.model.brighten(delta, imageName, destName);
          break;
        case "blur":
          double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}};
          this.model.filter(blurKernel, imageName, destName);
          break;
        case "sharpen":
          double[][] sharpenKernel = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
          this.model.filter(sharpenKernel, imageName, destName);
          break;
        case "greyscale":
          double[][] luma = {{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
          this.model.transform(luma, imageName, destName);
          break;
        case "sepia":
          double[][] sepia = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
          this.model.transform(sepia, imageName, destName);
          break;
        case "save-image":
          try {
            ImageUtil.saveImage(this.model.findWithID(destName), imageName);
          }
          catch (IOException e) {
            this.view.renderMessage(String.format("file %s not found", imageName));
          }
          catch (IllegalStateException e) {
            this.view.renderMessage(String.format("no image %s exists", destName));
          }
          break;
        case "q":
          programQuit = true;
          this.view.renderMessage("thanks for using this program");
          System.exit(0);
          return;
        default:
          this.view.renderMessage("Not a valid command. Please try again.\n");
      }

      if (this.model.getNames().contains(destName)) {
        this.view.renderMessage(String.format("Image Created: %s\n", destName));
      }
    }


  }

  private void listImages() {
    this.view.renderMessage("Images in this session:\n");
    for (String s : this.model.getNames()) {
      this.view.renderMessage(s + "\n");
    }
  }


}
