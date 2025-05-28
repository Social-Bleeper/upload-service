package com.bleeper.upload_service.feature.bleep;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bleeps")
@Getter
@Setter
public class Bleep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String caption;
    private String bucket;
    private String fileName;
}
