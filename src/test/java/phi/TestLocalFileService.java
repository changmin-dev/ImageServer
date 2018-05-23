package phi;

import org.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.context.properties.*;
import phi.properties.*;
import phi.service.*;

import java.util.*;

/**
 * Created by changmin on 2018. 5. 18..
 */
@ConfigurationProperties("FileStorageProperties.class")
public class TestLocalFileService {

    LocalFileService localFileService;

    @Test
    public void test_파일경로를_찾아내는지_확인(){
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setFileStorageDir("fileStorage");
        LocalFileService localFileService = new LocalFileService(fileStorageProperties);
        List<String> names = new ArrayList<>();
        names.add("testFrames/frame_000.gif");
        localFileService.resolveFileNames(names);
    }
}
