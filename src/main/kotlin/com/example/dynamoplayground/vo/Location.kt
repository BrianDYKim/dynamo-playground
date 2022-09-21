package com.example.dynamoplayground.vo

import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import java.io.Serializable

/**
 * @author Doyeop Kim
 * @since 2022/09/21
 */
@DynamoDbBean
data class Location(
    @get:DynamoDbAttribute("latitude")
    var latitude: Double = 0.0,
    @get:DynamoDbAttribute("longitude")
    var longitude: Double = 0.0
): Serializable {

    companion object {
        // Location의 tableSchema
        val tableSchema = TableSchema.fromBean(Location::class.java)

        // Location을 Embedded로 사용하기 위한 enhancedType 정의
        val enhancedType = EnhancedType.documentOf(Location::class.java, tableSchema)
    }
}