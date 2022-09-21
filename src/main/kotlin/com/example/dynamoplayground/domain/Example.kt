package com.example.dynamoplayground.domain

import com.example.dynamoplayground.vo.Location
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

/**
 * @author Doyeop Kim
 * @since 2022/09/21
 */
@DynamoDbBean
class Example(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("id")
    var id: String,
    @get:DynamoDbAttribute("name")
    var name: String,
    @get:DynamoDbAttribute("location")
    var location: Location
) {
}