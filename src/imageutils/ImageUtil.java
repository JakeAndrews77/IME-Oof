package imageutils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.awt.Color;

import model.BaseImage;
import model.IMEImage;
import model.Pixel;
import model.RGBPixel;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static IMEImage readPPM(String filename, String imageName) throws IOException {
    Scanner sc;


    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IOException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    ArrayList<ArrayList<Pixel>> pixel2d = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> pixel1d = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixel1d.add(new RGBPixel(r, g, b));
      }
      pixel2d.add(pixel1d);
    }


    return new BaseImage(imageName, pixel2d, maxValue);
  }

  /**
   * Reads an image from any format using ImageIO and converts the resulting
   * BufferedImage to an IMEImage.
   *
   * @param fileName  File to be read
   * @param imageName Identifer to be assigned to the read image
   * @return An IME image using the RGB dats of the file provided with the specified name
   * @throws IOException throws an exception if the file is invalid.
   */
  public static IMEImage readAnyImage(String fileName, String imageName) throws IOException {
    BufferedImage input = ImageIO.read(new File(fileName));

    ArrayList<ArrayList<Pixel>> pixel2d = new ArrayList<>();
    for (int i = 0; i < input.getHeight(); i++) {
      ArrayList<Pixel> pixel1d = new ArrayList<>();
      for (int j = 0; j < input.getWidth(); j++) {
        Color rgb = new Color(input.getRGB(j, i), false);
        pixel1d.add(new RGBPixel(rgb.getRed(), rgb.getGreen(), rgb.getBlue()));
      }
      pixel2d.add(pixel1d);
    }
    return new BaseImage(imageName, pixel2d, 255);
  }

  /**
   * Saves the provided image to a file with the given name.
   *
   * @param image    The image to be saved
   * @param fileName File path and name under which to save the image.
   */
  public static void saveImage(IMEImage image, String fileName) throws IOException {
    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
    BufferedImage outImage = ImageUtil.ime2BufferedImage(image);

    ImageIO.write(outImage, extension, new File(fileName));
  }

  protected static BufferedImage ime2BufferedImage(IMEImage image) {
    ArrayList<ArrayList<Pixel>> pixels = image.getAllPixels();
    BufferedImage outImage = new BufferedImage(pixels.size(),
            pixels.get(0).size(),
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < pixels.size(); i++) {
      for (int j = 0; j < pixels.get(i).size(); j++) {
        outImage.setRGB(j, i, 65536 * pixels.get(i).get(j).getRed()
                + 256 * pixels.get(i).get(j).getGreen() + pixels.get(i).get(j).getBlue());
      }
    }

    return outImage;
  }
}

