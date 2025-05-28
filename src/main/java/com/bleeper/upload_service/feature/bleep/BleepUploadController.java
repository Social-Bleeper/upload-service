package com.bleeper.upload_service.feature.bleep;

import com.bleeper.upload_service.upload.SupabaseS3UploadService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
public class BleepUploadController {
    private static final Logger logger = LoggerFactory.getLogger(BleepUploadController.class);

    private final SupabaseS3UploadService uploadService;
    private final BleepTempRepository bleepTempRepository;

    @PostMapping()
    public ResponseEntity<String> handleVideoUpload(@RequestParam("video") MultipartFile file,
                                                    @RequestParam("caption") String caption) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        Bleep newBleep = new Bleep();
        newBleep.setCaption(caption);
        Bleep bleep = bleepTempRepository.save(newBleep);
        bleep.setFileName(bleep.getId() + "-" + file.getOriginalFilename());
        bleep.setBucket("public-videos");
        bleep = bleepTempRepository.save(bleep);

        try {
            //String key = file.getOriginalFilename(); // Or generate unique name
            uploadService.uploadFile("public-videos", bleep.getFileName(), file);
            logger.info("File uploaded successfully: " + bleep.getFileName() + " - " + caption);
            return ResponseEntity.ok("Uploaded: " + bleep.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed: " + e.getMessage());
        }
    }
}
