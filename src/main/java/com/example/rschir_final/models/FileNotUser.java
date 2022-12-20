package com.example.rschir_final.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.io.File;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileNotUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String type;
    private Long size;
    @Lob
    private byte[] data;

    public FileNotUser(String name, String type, Long size, byte[] data)
    {
        this.name = name;
        this.type = type;
        this.size = size;
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "FileNotUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
