package com.example.dynamoplayground.repository

import com.example.dynamoplayground.domain.Example
import com.example.dynamoplayground.vo.Location
import io.kotest.common.runBlocking
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
internal class ExampleRepositoryTest @Autowired constructor(
    private val exampleRepository: ExampleRepository
) {

    @Test
    fun createTest(): Unit = runBlocking {
        val example = generateExample("예제1")
        exampleRepository.createExample(example).awaitSingleOrNull()
    }

    private fun generateExample(name: String): Example = Example(
        id = UUID.randomUUID().toString(),
        name = name,
        locationList = listOf(Location()),
    )
}