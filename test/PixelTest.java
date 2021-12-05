import org.junit.Test;

import model.GreyScalePixel;
import model.Pixel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for Pixel methods.
 */
public class PixelTest {

  @Test
  public void testPixelValue() {
    assertEquals(new RGBPixel(243, 147, 185).toGreyScale(
            Pixel.GreyScaleType.VALUE), new GreyScalePixel(243));
    assertEquals(new RGBPixel(45, 196, 255).toGreyScale(
            Pixel.GreyScaleType.VALUE), new GreyScalePixel(255));
    assertEquals(new RGBPixel(69, 242, 136).toGreyScale(
            Pixel.GreyScaleType.VALUE), new GreyScalePixel(242));
    assertEquals(new RGBPixel(9, 9, 9).toGreyScale(
            Pixel.GreyScaleType.VALUE), new GreyScalePixel(9));
    assertEquals(new RGBPixel(79, 162, 162).toGreyScale(
            Pixel.GreyScaleType.VALUE), new GreyScalePixel(162));
    assertEquals(new GreyScalePixel(128).toGreyScale(
            Pixel.GreyScaleType.VALUE), new GreyScalePixel(128));
  }

  @Test
  public void testPixelIntensity() {
    assertEquals(new RGBPixel(243, 147, 185).toGreyScale(
            Pixel.GreyScaleType.INTENSITY), new GreyScalePixel(192));
    assertEquals(new RGBPixel(45, 196, 255).toGreyScale(
            Pixel.GreyScaleType.INTENSITY), new GreyScalePixel(165));
    assertEquals(new RGBPixel(69, 242, 136).toGreyScale(
            Pixel.GreyScaleType.INTENSITY), new GreyScalePixel(149));
    assertEquals(new RGBPixel(9, 9, 9).toGreyScale(
            Pixel.GreyScaleType.INTENSITY), new GreyScalePixel(9));
    assertEquals(new RGBPixel(79, 162, 162).toGreyScale(
            Pixel.GreyScaleType.INTENSITY), new GreyScalePixel(134));
    assertEquals(new GreyScalePixel(128).toGreyScale(
            Pixel.GreyScaleType.INTENSITY), new GreyScalePixel(128));
  }

  @Test //* 0.2126r+0.7152g+0.0722b
  public void testPixelLuma() {
    assertEquals(new RGBPixel(243, 147, 185).toGreyScale(
            Pixel.GreyScaleType.LUMA), new GreyScalePixel(170));
    assertEquals(new RGBPixel(45, 196, 255).toGreyScale(
            Pixel.GreyScaleType.LUMA), new GreyScalePixel(168));
    assertEquals(new RGBPixel(69, 242, 136).toGreyScale(
            Pixel.GreyScaleType.LUMA), new GreyScalePixel(198));
    assertEquals(new RGBPixel(9, 9, 9).toGreyScale(
            Pixel.GreyScaleType.LUMA), new GreyScalePixel(9));
    assertEquals(new RGBPixel(79, 162, 162).toGreyScale(
            Pixel.GreyScaleType.LUMA), new GreyScalePixel(144));
    assertEquals(new GreyScalePixel(128).toGreyScale(
            Pixel.GreyScaleType.LUMA), new GreyScalePixel(128));
  }

  @Test
  public void testPixelRed() {
    assertEquals(new RGBPixel(243, 147, 185).toGreyScale(
            Pixel.GreyScaleType.RED), new GreyScalePixel(243));
    assertEquals(new RGBPixel(45, 196, 255).toGreyScale(
            Pixel.GreyScaleType.RED), new GreyScalePixel(45));
    assertEquals(new RGBPixel(69, 242, 136).toGreyScale(
            Pixel.GreyScaleType.RED), new GreyScalePixel(69));
    assertEquals(new RGBPixel(9, 9, 9).toGreyScale(
            Pixel.GreyScaleType.RED), new GreyScalePixel(9));
    assertEquals(new RGBPixel(79, 162, 162).toGreyScale(
            Pixel.GreyScaleType.RED), new GreyScalePixel(79));
    assertEquals(new GreyScalePixel(128).toGreyScale(
            Pixel.GreyScaleType.RED), new GreyScalePixel(128));
  }

  @Test
  public void testPixelGreen() {
    assertEquals(new RGBPixel(243, 147, 185).toGreyScale(
            Pixel.GreyScaleType.GREEN), new GreyScalePixel(147));
    assertEquals(new RGBPixel(45, 196, 255).toGreyScale(
            Pixel.GreyScaleType.GREEN), new GreyScalePixel(196));
    assertEquals(new RGBPixel(69, 242, 136).toGreyScale(
            Pixel.GreyScaleType.GREEN), new GreyScalePixel(242));
    assertEquals(new RGBPixel(9, 9, 9).toGreyScale(
            Pixel.GreyScaleType.GREEN), new GreyScalePixel(9));
    assertEquals(new RGBPixel(79, 162, 162).toGreyScale(
            Pixel.GreyScaleType.GREEN), new GreyScalePixel(162));
    assertEquals(new GreyScalePixel(128).toGreyScale(
            Pixel.GreyScaleType.GREEN), new GreyScalePixel(128));
  }

  @Test
  public void testPixelBlue() {
    assertEquals(new RGBPixel(243, 147, 185).toGreyScale(
            Pixel.GreyScaleType.BLUE), new GreyScalePixel(185));
    assertEquals(new RGBPixel(45, 196, 255).toGreyScale(
            Pixel.GreyScaleType.BLUE), new GreyScalePixel(255));
    assertEquals(new RGBPixel(69, 242, 136).toGreyScale(
            Pixel.GreyScaleType.BLUE), new GreyScalePixel(136));
    assertEquals(new RGBPixel(9, 9, 9).toGreyScale(
            Pixel.GreyScaleType.BLUE), new GreyScalePixel(9));
    assertEquals(new RGBPixel(79, 162, 162).toGreyScale(
            Pixel.GreyScaleType.BLUE), new GreyScalePixel(162));
    assertEquals(new GreyScalePixel(128).toGreyScale(
            Pixel.GreyScaleType.BLUE), new GreyScalePixel(128));
  }

  @Test
  public void testPixelPPM() {
    assertEquals(new RGBPixel(200, 442, 330).toPPMString(),
            "200\r\n442\r\n330");

    assertEquals(new RGBPixel(4, 15, 12).toPPMString(), "4\r\n15\r\n12");

    assertEquals(new GreyScalePixel(40).toPPMString(), "40\r\n40\r\n40");

    assertEquals(new GreyScalePixel(31).toPPMString(), "31\r\n31\r\n31");

    assertEquals(new GreyScalePixel(5).toPPMString(), "5\r\n5\r\n5");

    assertEquals(new GreyScalePixel(12).toPPMString(), "12\r\n12\r\n12");
  }

  @Test
  public void testFilter() {
    double[][] blurFilter = {{1.0 / 16, 1.0 / 8, 1.0 / 16}, {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}};
    double[][] sharpenKernel = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
    Pixel[][] pixelBlur = new Pixel[3][3];
    Pixel[][] pixelSharp = new Pixel[5][5];
    assertEquals(new RGBPixel(100, 100, 100).filter(blurFilter, pixelBlur, 8),
            new GreyScalePixel(170));
    assertEquals(new RGBPixel(100, 100, 100).filter(sharpenKernel, pixelSharp, 8),
            new GreyScalePixel(170));
  }

  @Test
  public void testTransform() {
    double[][] transformLuma = {{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
    double[][] transformSepia = {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    //This case will also test the new .equals() method comparing an RGBPixel to a GreyScalePixel
    assertEquals(new RGBPixel(75, 142, 200).transform(transformLuma, 8),
            new GreyScalePixel(132));
    assertEquals(new RGBPixel(75, 142, 200).transform(transformSepia, 8),
            new RGBPixel(176, 157, 122));

    //Sepia Matrix can saturate a dark image. So far this is the only saturation that can occur in
    //color transforms. Here is a test case.
    assertEquals(new RGBPixel(230, 250, 240).transform(transformSepia, 8),
            new RGBPixel(255, 255, 228));
  }


}