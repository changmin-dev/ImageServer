package phi;

import magick.*;
import org.junit.*;
import phi.service.*;
import phi.properties.*;

import java.nio.file.*;
import java.util.*;

/**
 * Created by changmin on 2018. 5. 17..
 * 스프링 테스트나 유닛 테스트에 대해서 잘 모르지만 기능 테스트를 위해서 작성했습니다.
 */
public class TestGifProcessingService {
    GIFProcessService gifProcessService;

    @Test
    public void test_Gif_딜레이를_넣어서_생성() throws MagickException {
        String storage = "fileStorage";
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setFileStorageDir(storage);
        gifProcessService = new GIFProcessService(fileStorageProperties);
        Path pathResolver = Paths.get(storage)
                .toAbsolutePath()
                .normalize();

        List<String> inputFileNames = new ArrayList<>();
        for(int i = 0; i < 10; ++i){
            String inputFileName = pathResolver.resolve(String.format("/testFrames/frame_%03d.gif", i))
                                                .toString();
            inputFileNames.add(inputFileName);
        }

        int delay = 10;
        String outputFileName = "testAnimated.gif";
        gifProcessService.generateAnimatedGif(inputFileNames, outputFileName, delay);

        String outputFileDir = pathResolver.resolve(outputFileName).toString();
        ImageInfo imageInfo = new ImageInfo(outputFileDir);
        MagickImage magickImage = new MagickImage(imageInfo);

        Assert.assertEquals(delay, magickImage.getDelay());
    }
}
