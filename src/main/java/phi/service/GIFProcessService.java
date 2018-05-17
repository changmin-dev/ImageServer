package phi.service;

import magick.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import phi.controller.*;
import phi.properties.*;

import java.nio.file.*;
import java.util.*;

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
    //과연 리스트로 들어오는게 순서대로 인지 확인 필요
    public void generateAnimatedGif(List<String> inputFileNames, String outputFileName){
        try {
            //일단 imageInfo로 가지고 오는 정보는 동일하다고 가정 - 추후 바꿔야 할꺼 같은데 일단 기본 기능 구현에 집중
            //resolve 하기 fileStoragePath.resolve();
            ImageInfo imageInfo = new ImageInfo(inputFileNames.get(0));

            MagickImage[] images = new MagickImage[inputFileNames.size()];
            for(int i = 0; i < inputFileNames.size(); ++i){
                imageInfo = new ImageInfo(inputFileNames.get(i));
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
    //딜레이의 값? Int면 되나? 코드 중복 제거 필요
    public void generateAnimatedGif(List<String> inputFileNames, String outputFileName, int delay){
        try {
            ImageInfo imageInfo = new ImageInfo(inputFileNames.get(0));

            MagickImage[] images = new MagickImage[inputFileNames.size()];
            for(int i = 0; i < inputFileNames.size(); ++i){
                imageInfo = new ImageInfo(inputFileNames.get(i));
                images[i] = new MagickImage(imageInfo);
            }

            MagickImage animatedGifImage = new MagickImage(images);
            animatedGifImage.setDelay(delay);
            String outputFileDir = fileStoragePath.resolve(outputFileName).toString();
            animatedGifImage.setFileName(outputFileDir);
            animatedGifImage.writeImage(imageInfo);

        } catch (MagickException e) {
            e.printStackTrace();
        }
    }
}
