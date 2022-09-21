package com.example.dynamoplayground.domain

import com.example.dynamoplayground.vo.Location
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticTableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
import java.io.Serializable

/**
 * @author Doyeop Kim
 * @since 2022/09/21
 */
@DynamoDbBean
class Example(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("id")
    var id: String = "",
    @get:DynamoDbSortKey
    @get:DynamoDbAttribute("name")
    var name: String = "",
    @get:DynamoDbAttribute("email")
    var email: String = "",
    @get:DynamoDbAttribute("locationList")
    var locationList: List<Location> = emptyList()
): Serializable {
    companion object {
        // 테이블 스키마를 정의
        val exampleTableSchema: StaticTableSchema<Example> = TableSchema.builder(Example::class.java)
            .newItemSupplier(::Example)
            .addAttribute(String::class.java) {
                it.name("id").getter(Example::id::get).setter(Example::id::set)
                    .tags(primaryPartitionKey())
            }
            .addAttribute(String::class.java) {
                it.name("name").getter(Example::name::get).setter(Example::name::set)
                    .tags(primarySortKey())
            }
            .addAttribute(EnhancedType.listOf(Location.enhancedType)) {
                it.name("locationList")
                    .getter(Example::locationList::get)
                    .setter(Example::locationList::set)
            }
            .build()

        val table = TableSchema.fromBean(Example::class.java)
    }
}