import java.io.IOException;


/**
 * Represents a testing model to test a base image.
 */
public class BaseImageTest extends AbstractImageTest {

  @Override
  protected void setTestImage(String modelName, String fileName) throws IOException {


    this.baseImage = imageutils.ImageUtil.readPPM(fileName, modelName);
  }

  @Override
  protected void setActualImage(String modelName, String fileName) throws IOException {
    this.testImage = imageutils.ImageUtil.readPPM(fileName, modelName);
  }
}
