import imageutils.ImageUtil;
import model.HistoGram;
import org.junit.Test;

import java.io.IOException;

import model.IMEImage;
import model.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Test class for abstract model.
 */
public abstract class AbstractImageTest {
  IMEImage baseImage;
  IMEImage testImage;


  protected abstract void setTestImage(String modelName, String fileName) throws IOException;

  protected abstract void setActualImage(String modelName, String fileName) throws IOException;

  @Test
  public void testToGreyScaleRed() throws IOException {
    setTestImage("Koala", "res/Koala.ppm");
    setActualImage("GreyscaleRed", "res/pngToPpm/koala-red-greyscale.ppm");

    assertEquals(this.baseImage.toGreyScale(Pixel.GreyScaleType.RED, "GreyscaleRed"),
            this.testImage);
  }

  @Test
  public void testToGreyScaleGreen() throws IOException {
    setTestImage("Koala", "res/Koala.ppm");
    setActualImage("GreyScaleGreen", "res/pngToPpm/koala-green-greyscale.ppm");

    assertEquals(this.baseImage.toGreyScale(Pixel.GreyScaleType.GREEN, "GreyScaleGreen"),
            this.testImage);
  }

  @Test
  public void testToGreyScaleBlue() throws IOException {
    setTestImage("Koala", "res/Koala.ppm");
    setActualImage("GreyscaleBlue", "res/pngToPpm/koala-blue-greyscale.ppm");

    assertEquals(this.baseImage.toGreyScale(Pixel.GreyScaleType.BLUE, "GreyscaleBlue"),
            this.testImage);
  }

  @Test
  public void testVerticalFlip() throws IOException {
    setTestImage("Test", "res/test.ppm");
    setActualImage("VerticalFlip", "res/test-vertical.ppm");

    assertEquals(this.baseImage.flipVertical("Test"),
            this.testImage);
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    setTestImage("Test", "res/test.ppm");
    setActualImage("HorizontalFlip", "res/test-horizontal.ppm");

    assertEquals(this.baseImage.flipHorizontal("Test"), this.testImage);
  }

  @Test
  public void testBrighten() throws IOException {
    setTestImage("Test", "res/test.ppm");
    setActualImage("BrightenTest", "res/test-brighter-by-50.ppm");

    assertEquals(this.baseImage.brighten(50, "BrightenTest"), this.testImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMosaicTooManySeeds() throws IOException {
    IMEImage img = new ImageUtil().readAnyImage("dog.jpg", "dog");
    IMEImage mosaicImg = img.mosaic(999999999, "dogMosaic");
  }

  @Test
  public void testMosaic() throws IOException {
    IMEImage img = new ImageUtil().readAnyImage("dog.jpg", "dog");
    IMEImage mosaic500Img = img.mosaic(500, "dog500Mosaic");
    IMEImage mosaic5000Img = img.mosaic(5000, "dog5000Mosaic");
    new ImageUtil().saveImage(mosaic500Img, "dog-500-seed-mosaic.jpg");
    new ImageUtil().saveImage(mosaic5000Img, "dog-5000-seed-mosaic.jpg");
    assertEquals(img.mosaic(500, "dog500Mosaic"), mosaic500Img);
    assertEquals(img.mosaic(5000, "dog5000Mosaic"), mosaic5000Img);
  }

  @Test
  public void testLuma() throws IOException {
    setTestImage("Koala", "res/Koala.ppm");
    setActualImage("KoalaLuma", "res/pngToPpm/koala-luma-greyscale.ppm");

    assertEquals(this.baseImage.toGreyScale(Pixel.GreyScaleType.LUMA, "KoalaLuma"),
            this.testImage);
  }

  @Test
  public void testIntensity() throws IOException {
    setTestImage("Koala", "res/Koala.ppm");
    setActualImage("KoalaIntensity",
            "res/pngToPpm/koala-intensity-greyscale.ppm");

    assertEquals(this.baseImage.toGreyScale(Pixel.GreyScaleType.INTENSITY, "KoalaIntensity"),
            this.testImage);
  }

  @Test
  public void testValue() throws IOException {
    setTestImage("Koala", "res/Koala.ppm");
    setActualImage("KoalaValue",
            "res/pngToPpm/koala-intensity-greyscale.ppm");

    assertEquals(this.baseImage.toGreyScale(Pixel.GreyScaleType.VALUE, "KoalaValue"),
            this.testImage);
  }

  @Test
  public void testPixelPPM() throws IOException {
    setTestImage("test", "res/test.ppm");
    assertEquals(this.baseImage.toPPMString().replace("\r", ""), "P3\n" +
            "2 2\n" +
            "255\n" +
            "25\n" +
            "25\n" +
            "50\n" +
            "50\n" +
            "75\n" +
            "75\n" +
            "100\n" +
            "100\n" +
            "150\n" +
            "150\n" +
            "200\n" +
            "200\n");
  }

  @Test
  public void testFilter() throws IOException {

    double[][] filter = new double[3][3];
    setTestImage("test", "res/test.ppm");
    setActualImage("KoalaValue",
            "res/test-vertical.ppm");
    assertEquals(this.baseImage.filter(filter, "test"), this.testImage);
    // index out of bounds error
  }

  @Test
  public void testTransform() throws IOException {
    double[][] transform = new double[3][3];
    setTestImage("test", "res/test.ppm");
    setActualImage("KoalaValue",
            "res/test-vertical.ppm");
    assertEquals(this.baseImage.transform(transform, "test"), this.testImage);
    // hashcode error
  }

  @Test
  public void testHistoGram() throws IOException {
    setTestImage( "test", "res/test.ppm");
    HistoGram hist = new HistoGram(this.baseImage);
    assertEquals(hist.getReds().get(25), 1, 0);
    assertEquals(hist.getReds().get(50), 1, 0);
    assertEquals(hist.getReds().get(100), 1, 0);
    assertEquals(hist.getReds().get(150), 1, 0);
    assertEquals(hist.getGreens().get(25), 1, 0);
    assertEquals(hist.getGreens().get(75), 1, 0);
    assertEquals(hist.getGreens().get(100), 1, 0);
    assertEquals(hist.getGreens().get(200), 1, 0);
    assertEquals(hist.getBlues().get(50), 1, 0);
    assertEquals(hist.getBlues().get(75), 1, 0);
    assertEquals(hist.getBlues().get(150), 1, 0);
    assertEquals(hist.getBlues().get(200), 1, 0);
    assertEquals(hist.getIntensities().get(33), 1, 0);
    assertEquals(hist.getIntensities().get(67), 1, 0);
    assertEquals(hist.getIntensities().get(117), 1, 0);
    assertEquals(hist.getIntensities().get(183), 1, 0);

  }

}