package com.bleeper.upload_service.feature.bleep;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class VideoUpload {

    private MultipartFile file;
    private String caption;
}
