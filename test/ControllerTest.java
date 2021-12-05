import controller.IMEController;
import controller.IMEControllerImp;
import model.MockModel;

import org.junit.Test;

import view.IMEView;
import view.MockView;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the controller.
 */
public class ControllerTest {

  IMEController tester;
  Appendable appendable;
  IMEView mockView;

  private void buildMock(String commands) {
    this.appendable = new StringBuilder();
    this.mockView = new MockView(this.appendable);
    this.tester = new IMEControllerImp(new StringReader(commands),
            this.mockView, new MockModel(this.appendable));
  }

  @Test
  public void testImageLoad() {
    this.buildMock("load-image images/Koala.ppm koala");
    this.tester.runIME();
    assertEquals(this.appendable.toString(),
            "Welcome to this Image Processor\n" +
                    "loaded images/Koala.ppm as koala" +
                    "Loaded Image: images/Koala.ppm as koala\n" +
                    "Images in this session:\n" +
                    "koala");
  }

  @Test
  public void testSmoothRun() {

    //This test will show a controller that runs smoothly creating one image
    // for each possible command, and then saving each image;
    this.buildMock("load-image res/Koala.ppm koala\n" + //load image

            //Manipulate images
            "red-component-image koala koalaRed\n" +
            "green-component-image koala koalaGreen\n" +
            "blue-component-image koala koalaBlue\n" +
            "value-component-image koala koalaValue\n" +
            "intensity-component-image koala koalaIntensity\n" +
            "luma-component-image koala koalaLuma\n" +
            "horizontal-flip koala koalaHoFlip\n" +
            "vertical-flip koala koalaVertFlip\n" +
            "vertical-flip koala koalaHoVertFlip\n" +
            "horizontal-flip koala koalaVertHoFlip\n" +
            "brighten 50 koala koalaBrighten50\n" +
            "blur koala koalaBlur\n" +
            "sharpen koala koalaSharp\n" +
            "greyscale koala koalaLuma\n" +
            "sepia koala koalaSepia\n" +

            //save images
            "save-image res/koala-red-greyscale.png koalaRed\n" +
            "save-image res/koala-green-greyscale.png koalaGreen\n" +
            "save-image res/koala-blue-greyscale.png koalaBlue\n" +
            "save-image res/koala-value-greyscale.png koalaValue\n" +
            "save-image res/koala-intensity-greyscale.png koalaIntensity\n" +
            "save-image res/koala-luma-greyscale.png koalaLuma\n" +
            "save-image res/koala-horizontal.png koalaHoFlip\n" +
            "save-image res/koala-vertical.png koalaVertFlip\n" +
            "save-image res/koala-horizontal-vertical.png koalaHoVertFlip\n" +
            "save-image res/koala-vertical-horizontal.png koalaVertHoFlip\n" +
            "save-image res/koala-brighter-by-50.png koalaBrighten50\n" +
            "save-image res/koala-blur.png koalaBlur\n" +
            "save-image res/koala-sharpen.png koalaSharp\n" +
            "save-image res/koala-sepia.png koalaSepia");

    this.tester.runIME();

    assertEquals(this.appendable.toString(), "Welcome to this Image Processor\n" +
            "loaded res/Koala.ppm as koala" +
            "Loaded Image: res/Koala.ppm as koala\n" +
            "Images in this session:\n" +
            "koala\n" +
            "Grey scale image koala with component red, returned as koalaRed\n" +
            "Image created: koalaRed\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "Grey scale image koala with component green, returned as koalaGreen\n" +
            "Image created: koalaGreen\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "Grey scale image koala with component blue, returned as koalaBlue\n" +
            "Image created: koalaBlue\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "Grey scale image koala with component value, returned as koalaValue\n" +
            "Image created: koalaValue\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "Grey scale image koala with component intensity, returned as koalaIntensity\n" +
            "Image created: koalaIntensity\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "Grey scale image koala with component luma, returned as koalaLuma\n" +
            "Image created: koalaLuma\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "Image koala flipped horizontally, returned as koalaHoFlip\n" +
            "Image created: koalaHoFlip\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "Image koala flipped vertically, returned as koalaVertFlip\n" +
            "Image created: koalaVertFlip\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "Image koalaHoFlip flipped vertically, returned as koalaHoVertFlip\n" +
            "Image created: koalaHoVertFlip\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "Image koalaVertFlip flipped horizontally, returned as koalaVertHoFlip\n" +
            "Image created: koalaVertHoFlip\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "Brighten image koala by amount 50 returned as koalaBrighten50. Image has 8 bits\n" +
            "Image created: koalaBrighten50\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "Image koala filtered returned as koalaBlur.\n" +
            "Kernel used:\n" +
            "[[0.0625, 0.125, 0.0625], [0.125, 0.25, 0.125], [0.0625, 0.125, 0.0625]\n" +
            "Image created: koalaBlur\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "Image koala filtered returned as koalaSharp.\n" +
            "Kernel used:\n" +
            "[[-0.125, -0.125, -0.125, -0.125, -0.125]," +
            " [-0.125, 0.25, 0.25, 0.25, -0.125]," +
            " [-0.125, 0.25, 1.0, 0.25, -0.125]" +
            " [-0.125, 0.25, 0.25, 0.25, -0.125]" +
            " [-0.125, -0.125, -0.125, -0.125, -0.125]]\n" +
            "Image created: koalaSharp\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "Image koalaLuma will be overwritten.\n" +
            "Image koala transformed returned as koalaLuma.\n" +
            "Matrix used:\n" +
            "[[0.2126, 0.7152, 0.0722]," +
            " [0.2126, 0.7152, 0.0722]," +
            " [0.2126, 0.7152, 0.0722]]\n" +
            "Image created: koalaLuma\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "Image koala transformed returned as koalaSepia.\n" +
            "Matrix used:\n" +
            "[[0.393, 0.769, 0.189]," +
            " [0.349, 0.686, 0.168]," +
            " [0.272, 0.534, 0.131]]\n" +
            "Image created: koalaSepia\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaRed was written to res/koala-red-greyscale.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaGreen was written to res/koala-green-greyscale.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaBlue was written to res/koala-blue-greyscale.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaValue was written to res/koala-value-greyscale.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaIntensity was written to res/koala-intensity-greyscale.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaLuma was written to res/koala-luma-greyscale.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaHoFlip was written to res/koala-horizontal.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaVertFlip was written to res/koala-vertical.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaHoVertFlip was written to res/koala-horizontal-vertical.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaVertHoFlip was written to res/koala-vertical-horizontal.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaBrighten50 was written to res/koala-brighten-by-50.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaBlur was written to res/koala-blur.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaSharp was written to res/koala-sharpen.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n" +
            "The image koalaSepia was written to res/koala-sepia.png\n" +
            "Images in this session:\n" +
            "koala\n" +
            "koalaRed\n" +
            "koalaGreen\n" +
            "koalaBlue\n" +
            "koalaValue\n" +
            "koalaIntensity\n" +
            "koalaLuma\n" +
            "koalaHoFlip\n" +
            "koalaVertFlip\n" +
            "koalaHoVertFlip\n" +
            "koalaVertHoFlip\n" +
            "koalaBrighten50\n" +
            "koalaBlur\n" +
            "koalaSharp\n" +
            "koalaSepia\n");
  }
}
