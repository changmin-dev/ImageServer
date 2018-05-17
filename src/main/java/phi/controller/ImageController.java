package phi.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import phi.service.*;

import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

/**
 * Created by changmin on 2018. 5. 17..
 * 응답을 처리합니다.
 */
@RestController
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    private LocalFileService localFileService;
    private GIFProcessService gifProcessService;
    private JSONParseService jsonParseService;

    @Autowired
    public ImageController(LocalFileService localFileService, GIFProcessService gifProcessService, JSONParseService jsonParseService) {
        this.localFileService = localFileService;
        this.gifProcessService = gifProcessService;
        this.jsonParseService = jsonParseService;
    }

    //응답은 201인가 200인가? db에 생성하는 건 아니지만 파일을 생성하므로 201
    @PostMapping("/merge-gif")
    //경로를 못찾음
    public ResponseEntity<Resource> animatedGif(HttpServletRequest request){
        //생성할 파일명은 어떻게???
        String outputFileName = "animated.gif";

        //일단 테스트로만
        String[] inputFileNames = jsonParseService.setTestData();

        gifProcessService.generateAnimatedGif(inputFileNames, outputFileName);

        Resource resource = localFileService.loadFile(outputFileName);

        //다른 서비스를 만들어야?
        String contentType = getCotentType(request, resource);

        //201이 적절한거 같은데 사용법이..
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment: filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private String getCotentType(HttpServletRequest request, Resource resource) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            logger.info("Could not determine file type");
        }

        if(contentType == null){
            contentType = "application/octet-stream";
        }
        return contentType;
    }
}
