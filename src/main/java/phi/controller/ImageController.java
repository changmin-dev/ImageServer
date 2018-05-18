package phi.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import phi.entity.*;
import phi.exception.InvalidRequestPathException;
import phi.exception.FileNotFoundException;
import phi.service.*;

import javax.servlet.http.*;
import java.text.*;
import java.util.*;

/**
 * Created by changmin on 2018. 5. 17..
 * 응답을 처리합니다.
 */
@RestController
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    private LocalFileService localFileService;
    private GIFProcessService gifProcessService;

    @Autowired
    public ImageController(LocalFileService localFileService, GIFProcessService gifProcessService) {
        this.localFileService = localFileService;
        this.gifProcessService = gifProcessService;
    }

    //응답은 201인가 200인가? db에 생성하는 건 아니지만 파일을 생성하므로 201
    @PostMapping("/merge-gif")
    public ResponseEntity<Resource> animatedGif(@RequestBody GifMergeRequest gifMergeRequest, HttpServletRequest request){

        if(gifMergeRequest == null){
            return ResponseEntity.badRequest()
                    .body(null);
        }

        try {
            List<String> inputFileNames = localFileService.resolveFileNames(gifMergeRequest.getPaths());
            int delay = Integer.parseInt(gifMergeRequest.getDelay());
            //생성할 파일명은 어떻게??? 일단 파일 경로 제공은 하지 않으니
            //String outputFileName = "animated.gif";
            String outputFileName = new SimpleDateFormat( "yyyy.MM.dd HH:mm:ss")
                                        .format(new Date())
                                        .toString() + ".gif";
            gifProcessService.generateAnimatedGif(inputFileNames, outputFileName, delay);

            Resource resource = localFileService.loadFile(outputFileName);

            String contentType = localFileService.getCotentType(request, resource);


            //201이 적절한거 같은데 사용법이..
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment: filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

            //좋은 방법인가?
        }catch (NumberFormatException ex){
            return ResponseEntity.badRequest()
                    .body(null);
        }catch (InvalidRequestPathException ex){
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }

}
