package phi.service;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import phi.properties.*;
import phi.exception.FileNotFoundException;

import javax.servlet.http.*;

/**
 * Created by changmin on 2018. 5. 17..
 */
@Service
public class LocalFileService {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileService.class);

    private Path fileStoragePath;

    @Autowired
    public LocalFileService(FileStorageProperties fileStorageProperties) {
        this.fileStoragePath = Paths.get(fileStorageProperties.getFileStorageDir())
                .toAbsolutePath()
                .normalize();
    }

    /**
     * resovle를 통해서 정확한 파일 위치를 찾아 냅니다.
     * @param fileNames
     * @return 찾아진 파일의 경로들
     */
    public List<String> resolveFileNames(List<String> fileNames){
        List<String> output = new ArrayList<>();

        //Exception InvalidPathException Bad Request하는
        for(String fileName : fileNames){
            Path filePath = this.fileStoragePath.resolve(fileName).normalize();
            output.add(filePath.toString());
        }

        return output;
    }


    /**
     * 저장소에 있는 파일을 불러 옵니다.
     * @param fileName
     * @return
     */
    public Resource loadFile(String fileName){
        Path filePath = this.fileStoragePath.resolve(fileName).normalize();
        logger.info(filePath.toString());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                logger.info("!resource.exist()");
                throw new FileNotFoundException("FileNotFound!");
            }
        } catch (MalformedURLException e) {
            logger.info("MalformedURLException");
            throw new FileNotFoundException("FileNotFound!");
        }
    }

    /**
     * MimeType을 찾습니다. 로컬에 있는 파일 타입을 찾으므로 해당서비스라고 판단했습니다.
     * @param request HTTPServletResuqest의 객체
     * @param resource 추상화된 파일 정보를 나타내는 객체
     * @return
     */
    public String getCotentType(HttpServletRequest request, Resource resource) {
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
