package phi;

import magick.*;
import org.junit.*;

public class AnigifCmkApplicationTests {

	@Test
	public void test_JMagick로드() throws MagickException{
		ImageInfo imageInfo = new ImageInfo("./fileStorage/logo.png");
		Assert.assertEquals(1, imageInfo.getAdjoin());
	}

}
