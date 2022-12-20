package com.example.rschir_final;

import com.example.rschir_final.models.FileNotUser;
import com.example.rschir_final.repo.FileNotUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RschirFinalApplicationTests
{

    @Autowired
    FileNotUserRepo fileNotUserRepo;

    @Test
    void contextLoads()
    {
    }

    @Test
    void clearData()
    {
        fileNotUserRepo.deleteAll();
    }

    @Test
    void deleteById(){
        fileNotUserRepo.deleteById(102l);
    }

    @Test
    void getAll(){
        System.out.println(fileNotUserRepo.findAll());
    }

}
