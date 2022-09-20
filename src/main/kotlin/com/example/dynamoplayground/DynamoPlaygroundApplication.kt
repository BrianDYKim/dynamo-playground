package com.example.dynamoplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DynamoPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<DynamoPlaygroundApplication>(*args)
}
