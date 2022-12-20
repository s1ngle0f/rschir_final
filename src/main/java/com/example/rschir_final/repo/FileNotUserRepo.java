package com.example.rschir_final.repo;

import com.example.rschir_final.models.FileNotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.List;

public interface FileNotUserRepo extends JpaRepository<FileNotUser, Long>
{
    FileNotUser findFileNotUserById(Long id);
    void deleteById(Long id);
}
