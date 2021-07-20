package com.example.s3test.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.aws.s3")
public class S3UploadComponent {

    private String bucket;

    public String getBucket() {
        return bucket;
    }
}
