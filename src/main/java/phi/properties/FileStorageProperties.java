package phi.properties;

import org.springframework.boot.context.properties.*;

/**
 * Created by changmin on 2018. 5. 17..
 * application.properties 파일에 지정한 저장소 위치를 불러오게 합니다
 */
@ConfigurationProperties()
public class FileStorageProperties {
    private String fileStorageDir;

    public String getFileStorageDir() {
        return fileStorageDir;
    }

    public void setFileStorageDir(String fileStorageDir) {
        this.fileStorageDir = fileStorageDir;
    }

}
