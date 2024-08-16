package com.github.aliftrd.sei.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.aliftrd.sei.domain.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    
}
