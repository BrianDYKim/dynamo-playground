package com.example.dynamoplayground.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

/**
 * @author Doyeop Kim
 * @since 2022/09/21
 */
@Configuration
class DynamoDBConfig(
    @Value("\${aws.dynamodb.credentials.access-key}")
    val accessKey: String,
    @Value("\${aws.dynamodb.credentials.secret-key}")
    val secretKey: String
) {

    // dynamoDB Client Bean 생성
    @Bean
    fun dynamoDbClient(): DynamoDbClient = DynamoDbClient.builder()
        .region(Region.AP_NORTHEAST_2)
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)
            )
        )
        .build()

    @Bean
    fun dynamoDbAsyncClient(): DynamoDbAsyncClient = DynamoDbAsyncClient.builder()
        .region(Region.AP_NORTHEAST_2)
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey)
            )
        )
        .build()

    // dynamoDB Enhanced Client 생성
    @Bean
    fun dynamoDbEnhancedClient(
        @Qualifier("dynamoDbClient") dynamoDbClient: DynamoDbClient
    ): DynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
        .dynamoDbClient(dynamoDbClient)
        .build()

    // Async가 적용된 DynamoDbEnhancedAsyncClient 생성
    @Bean(name = ["dynamoDbEnhancedAsyncClient"])
    fun dynamoDbEnhancedAsyncClient(
        @Qualifier("dynamoDbAsyncClient") dynamoDbAsyncClient: DynamoDbAsyncClient
    ): DynamoDbEnhancedAsyncClient = DynamoDbEnhancedAsyncClient.builder()
        .dynamoDbClient(dynamoDbAsyncClient)
        .build()
}
