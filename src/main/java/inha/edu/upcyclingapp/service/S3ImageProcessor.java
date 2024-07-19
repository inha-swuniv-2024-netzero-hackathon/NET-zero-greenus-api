package inha.edu.upcyclingapp.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@Service
public class S3ImageProcessor implements ImageProcessor {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void upload(String key, MultipartFile image) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());

        amazonS3Client.putObject(bucket, key, image.getInputStream(), metadata);
    }

    @Override
    public byte[] findByKey(String key) {
        try {
            return amazonS3Client.getObject(bucket, key).getObjectContent().readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
