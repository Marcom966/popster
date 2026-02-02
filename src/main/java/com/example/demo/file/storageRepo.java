package com.example.demo.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface storageRepo extends JpaRepository <fileToUpload, String> {
    List<fileToUpload> findByUsernName(String username);
}
