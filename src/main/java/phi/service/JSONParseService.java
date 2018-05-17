package phi.service;

import org.springframework.stereotype.*;

/**
 * Created by changmin on 2018. 5. 17..
 */
@Service
public class JSONParseService {
    public String[] setTestData(){
        //
        String[] inputFileNames = new String[294];
        for(int i = 0; i < inputFileNames.length; ++i){
            inputFileNames[i] = String.format("./fileStorage/testSplitedFrames/frame_%03d.gif", i);
        }
        return inputFileNames;
    }
}
