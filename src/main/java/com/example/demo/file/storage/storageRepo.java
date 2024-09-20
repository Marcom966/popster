package com.example.demo.file.storage;

import com.example.demo.file.fileToUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface storageRepo extends JpaRepository <fileToUpload, Long> {
}
