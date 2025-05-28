package com.bleeper.upload_service.messaging;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class UploadController {
    private final UploadProducer uploadProducer;

    @PostMapping("/send")
    public String sendOrder(@Valid @RequestBody UploadMessage uploadMessage,
                            @AuthenticationPrincipal Jwt jwt) {
        uploadProducer.sendMessage(uploadMessage);
        return "Message Sent!";
    }
}
