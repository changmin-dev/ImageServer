package phi.service;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;

import java.net.*;
import java.nio.file.*;

import phi.properties.*;
import phi.exception.FileNotFoundException;
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

    public Resource loadFile(String fileName){
        Path filePath = this.fileStoragePath.resolve(fileName).normalize();
        logger.debug(filePath.toString());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                logger.debug("!resource.exist()");
                throw new FileNotFoundException("FileNotFound!");
            }
        } catch (MalformedURLException e) {
            logger.debug("MalformedURLException");
            throw new FileNotFoundException("FileNotFound!");
        }
    }
}
