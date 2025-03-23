package com.olegpetrov.carservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import java.net.URI

@Configuration
class AwsConfig {

    @Value("\${aws.credentials.access-key}")
    private lateinit var accessKey: String

    @Value("\${aws.credentials.secret-key}")
    private lateinit var secretKey: String

    @Value("\${aws.region}")
    private lateinit var region: String

    @Value("\${aws.endpoint}")
    private lateinit var endpoint: String

    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
            .endpointOverride(URI.create(endpoint)) // LocalStack endpoint
            .region(Region.of(region))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)
            ))
            .serviceConfiguration(
                S3Configuration.builder()
                    .pathStyleAccessEnabled(true) // Required for LocalStack
                    .build()
            )
            .build()
    }
}
