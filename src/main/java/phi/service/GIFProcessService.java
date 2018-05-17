package phi.service;

import magick.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import phi.controller.*;
import phi.properties.*;

import java.nio.file.*;

/**
 * Created by changmin on 2018. 5. 17..
 */
@Service
public class GIFProcessService {
    private static final Logger logger = LoggerFactory.getLogger(GIFProcessService.class);

    private Path fileStoragePath;

    @Autowired
    public GIFProcessService(FileStorageProperties fileStorageProperties) {
        fileStoragePath = Paths.get(fileStorageProperties.getFileStorageDir())
                .toAbsolutePath()
                .normalize();
    }

    public void generateAnimatedGif(String[] inputFileNames, String outputFileName){
        try {
            //일단 imageInfo로 가지고 오는 정보는 동일하다고 가정 - 추후 바꿔야 할꺼 같은데 일단 기본 기능 구현에 집중

            ImageInfo imageInfo = new ImageInfo(inputFileNames[0]);

            MagickImage[] images = new MagickImage[inputFileNames.length];
            for(int i = 0; i < inputFileNames.length; ++i){
                imageInfo = new ImageInfo(inputFileNames[i]);
                images[i] = new MagickImage(imageInfo);
            }

            MagickImage animatedGifImage = new MagickImage(images);
            String outputFileDir = fileStoragePath.resolve(outputFileName).toString();
            animatedGifImage.setFileName(outputFileDir);
            animatedGifImage.writeImage(imageInfo);

        } catch (MagickException e) {
            e.printStackTrace();
        }

    }
}
