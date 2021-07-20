package com.example.s3test.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class S3Uploader implements Uploader {
    private final AmazonS3Client amazonS3Client;
    private final S3UploadComponent s3UploadComponent;

    @Value("${s3.bucket}")
    private String bucket;

    @Value(("cloudfront.url"))
    private String cloudfrontUrl;

    public S3Uploader(AmazonS3Client amazonS3Client, S3UploadComponent s3UploadComponent) {
        this.amazonS3Client = amazonS3Client;
        this.s3UploadComponent = s3UploadComponent;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        File file = convertToFile(multipartFile);
        String fileName = System.currentTimeMillis() + Base64.encodeAsString(multipartFile.getName().getBytes()) + multipartFile.getContentType();
        amazonS3Client.putObject(new PutObjectRequest(s3UploadComponent.getBucket(), fileName, file));
        file.delete();
        return cloudfrontUrl + "/" + fileName;
    }


    private File convertToFile(MultipartFile multipartFile) {
        File convertedFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("파일 변환 실패!");
        }
        return convertedFile;
    }

}
