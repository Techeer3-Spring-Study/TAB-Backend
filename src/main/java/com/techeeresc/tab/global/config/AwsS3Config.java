package com.techeeresc.tab.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

// code reference from: https://develop-writing.tistory.com/129
@Configuration
// @PropertySource(value = "s3.yml")
public class AwsS3Config {
   @Value("${cloud.aws.region.static}")
    private String region;

   @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

   @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

   public static final String imageFolder = "images";
   public static final String fileFolder = "files";

   @Bean
    public AwsCredentials basicAwsCredentials() {
       return AwsBasicCredentials.create(accessKey, secretKey);
   }

   @Bean
    public S3Client s3Client(AwsCredentials awsCredentials) {
       return S3Client.builder()
               .region(Region.of(region))
               .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
               .build();
   }

   @Bean
    public S3Presigner s3Presigner(AwsCredentials awsCredentials) {
       return S3Presigner.builder()
               .region(Region.of(region))
               .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
               .build();
   }
}
