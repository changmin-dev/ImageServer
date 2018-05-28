package phi.service;

import magick.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import phi.exception.*;
import phi.properties.FileStorageProperties;

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

    //딜레이의 값? Int면 되나? 코드 중복 제거 필요
    public boolean generateAnimatedGif(List<String> inputFileNames, String outputFileName, int delay){
        try {
            ImageInfo imageInfo = new ImageInfo(fileStoragePath.resolve(inputFileNames.get(0)).toAbsolutePath().normalize().toString());
            //파일 정보, 이미지 정보가 다른 경우?
            MagickImage[] images = new MagickImage[inputFileNames.size()];
            for(int i = 0; i < inputFileNames.size(); ++i){
                String resovedFilename = fileStoragePath.resolve(inputFileNames.get(0)).toAbsolutePath().normalize().toString();
                imageInfo = new ImageInfo(resovedFilename);
                try {
                    images[i] = new MagickImage(imageInfo);
                }catch (MagickApiException ex){
                    //throw new InvalidRequestPathException("이미지를 불러오는데 문제가 있습니다. " + imageInfo.getFileName());
                    return false;
                }
            }

            MagickImage animatedGifImage = new MagickImage(images);
            String outputFileDir = fileStoragePath.resolve(outputFileName).toString();
            animatedGifImage.setDelay(delay);
            animatedGifImage.setFileName(outputFileDir);
            animatedGifImage.writeImage(imageInfo);

            return true;
        }catch (MagickException e) {
            //MagickException
            e.printStackTrace();
            return false;
        }
    }
}
