package phi;

import magick.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnigifCmkApplicationTests {

	@Test
	public void JMagick이_로드되는지_테스트() throws MagickException{
		ImageInfo imageInfo = new ImageInfo("/Users/changmin/Projects/hackday/images/masterbaby");
		Assert.assertEquals(1, imageInfo.getAdjoin());
	}

}
