package com.example.dynamoplayground.repository

import com.example.dynamoplayground.domain.Example
import com.example.dynamoplayground.vo.Location
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema

/**
 * @author Doyeop Kim
 * @since 2022/09/21
 */
@Repository
class ExampleRepository(
    private val dynamoDbEnhancedAsyncClient: DynamoDbEnhancedAsyncClient
) {
    val asyncTable: DynamoDbAsyncTable<Example> =
        dynamoDbEnhancedAsyncClient.table("example", Example.exampleTableSchema)

    fun createExample(example: Example): Mono<Void> {
        val createItemFuture = asyncTable.putItem(example)
        return Mono.fromFuture(createItemFuture)
    }

    fun readExample(id: String, name: String): Mono<Example> {
        val key = generateKey(id, name)
        val itemFuture = asyncTable.getItem(key)

        return Mono.fromFuture(itemFuture)
    }

    private fun generateKey(id: String, name: String) = Key.builder()
        .partitionValue(id)
        .sortValue(name)
        .build()
}