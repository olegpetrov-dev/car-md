package com.olegpetrov.carservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest
import software.amazon.awssdk.services.s3.model.HeadBucketRequest
import software.amazon.awssdk.services.s3.model.NoSuchBucketException
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.*

@Service
class S3Service(
    private val s3Client: S3Client,
    @Value("\${aws.s3.photos.bucket-name}") private val photosBucket: String,
    @Value("\${aws.region}") private val region: String
) {

    @EventListener(ApplicationReadyEvent::class)
    fun initializeS3Bucket() {
        createBucketIfNotExists(photosBucket)
    }

    private fun createBucketIfNotExists(bucketName: String) {
        try {
            s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build())
            println("Bucket already exists: $bucketName")
        } catch (e: NoSuchBucketException) {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build())
            println("Bucket created: $bucketName")
        }
    }

    fun uploadFile(file: MultipartFile): String {
        val key = UUID.randomUUID().toString()
        uploadToS3(file, key)
        return generateS3Url(key)
    }

    fun uploadFiles(files: List<MultipartFile>): List<String> {
        return files.map { file ->
            uploadFile(file)
        }
    }

    private fun uploadToS3(file: MultipartFile, key: String) {
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(photosBucket)
            .key(key)
            .contentType(file.contentType)
            .build()

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.inputStream, file.size))
    }

//    todo make it work with real s3
    private fun generateS3Url(key: String): String {
        return "http://localhost:4566/${photosBucket}/${key}"
    }
}
