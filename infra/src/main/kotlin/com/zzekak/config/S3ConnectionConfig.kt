/*
 * Zzekak
 * Sir.LOIN Intellectual property. All rights reserved.
 */
package com.zzekak.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

/**
 * @since 2024-07-13
 */
@Configuration
class S3ConnectionConfig {
    @Bean
    fun s3Client(
        @Value("\${lia.external-api.aws.s3.api-key}")
        awsApiKey: String,
        @Value("\${lia.external-api.aws.s3.secret-key}")
        awsSecretKey: String
    ): S3Client =
        S3Client.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(
                StaticCredentialsProvider.create(AwsBasicCredentials.create(awsApiKey, awsSecretKey)),
            )
            .build()
}
