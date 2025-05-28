package com.bleeper.upload_service.feature.bleep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BleepTempRepository extends JpaRepository<Bleep, Long> {
}
