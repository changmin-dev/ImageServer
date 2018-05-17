package phi;

import org.junit.*;
import phi.service.*;
import phi.properties.*;

import java.util.*;

/**
 * Created by changmin on 2018. 5. 17..
 * 스프링 테스트나 유닛 테스트에 대해서 잘 모르지만 기능 테스트를 위해서 작성했습니다.
 */
public class TestGifProcessingService {
    GIFProcessService gifProcessService;
    @org.junit.Before
    public void before(){
        //일단 테스트의 경우 Spring 관련해서 설정하는 것을 몰라서 이런 방법으로 했습니다.
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setFileStorageDir("fileStorage");
        gifProcessService = new GIFProcessService(fileStorageProperties);
    }
    @Test
    public void test_Gif생성(){
        String[] inputFileNames = new String[294];
        for(int i = 0; i < inputFileNames.length; ++i){
            inputFileNames[i] = String.format("./fileStorage/testSplitedFrames/frame_%03d.gif", i);
        }
        String outputFileDir = "./fileStorage/testOutputs/animated.gif";
        gifProcessService.generateAnimatedGif(Arrays.asList(inputFileNames), outputFileDir);

        //결과는 직접 확인해야하는 ㅠ 테스트 입니다.
    }
}
