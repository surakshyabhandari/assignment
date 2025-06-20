package com.example.assignment.service.impl;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@Service
public class ImageService {

    @Autowired
    private final MinioClient minioClient;

    public ImageService(MinioClient minioClient){
        this.minioClient = minioClient;
    }

    private final String bucketName = "images";

    public String uploadImage(MultipartFile file) throws Exception{
        try{
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if(!exists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            InputStream inputStream = file.getInputStream();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return "http://localhost:9000/" + bucketName + "/" + fileName;
        } catch (RuntimeException e) {
            throw new RuntimeException("Image upload failed: " + e.getMessage());
        }

        
    }

//    public InputStream downloadImage(String fileName) throws Exception{
//        return minioClient.getObject(
//                GetObjectArgs.builder()
//                        .bucket(bucketName)
//                        .object(fileName)
//                        .build()
//        );
//    }
}
