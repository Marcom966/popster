package com.example.demo.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface storageRepo extends JpaRepository <fileToUpload, String> {
}
