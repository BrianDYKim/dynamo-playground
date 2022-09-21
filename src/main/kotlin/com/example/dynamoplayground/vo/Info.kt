package com.example.dynamoplayground.vo

import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import java.io.Serializable

@DynamoDbBean
data class Info(
    @get:DynamoDbAttribute("email")
    var email: String = ""
): Serializable {

    companion object {
        val tableSchema = TableSchema.fromBean(Info::class.java)

        val enhancedType = EnhancedType.documentOf(Info::class.java, tableSchema)
    }
}
