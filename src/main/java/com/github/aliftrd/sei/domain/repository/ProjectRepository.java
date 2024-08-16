package com.github.aliftrd.sei.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.aliftrd.sei.domain.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
