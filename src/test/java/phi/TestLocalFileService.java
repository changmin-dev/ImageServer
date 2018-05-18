package phi;

import org.junit.*;
import phi.properties.*;
import phi.service.*;

import java.util.*;

/**
 * Created by changmin on 2018. 5. 18..
 */
public class TestLocalFileService {
    LocalFileService localFileService;

    @Before
    public void before(){
        //일단 테스트의 경우 Spring 관련해서 설정하는 것을 몰라서 이런 방법으로 했습니다.
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setFileStorageDir("fileStorage");
        LocalFileService localFileService = new LocalFileService(fileStorageProperties);
    }

    @Test
    public void test_파일경로를_찾아내는지_확인(){
        List<String> names = new ArrayList<>();
        names.add("testSplitedFrames/frame_000.gif");
        names.add("animated.gif");
        localFileService.resolveFileNames(names);
    }
}
