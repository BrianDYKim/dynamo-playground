package com.example.dynamoplayground.repository

import com.example.dynamoplayground.domain.Example
import com.example.dynamoplayground.vo.Info
import com.example.dynamoplayground.vo.Location
import io.kotest.common.runBlocking
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.junit.jupiter.api.Assertions.assertNotNull
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

    @Test
    fun readTest(): Unit = runBlocking {
        val example = exampleRepository.readExample("26318a99-2fe2-4e57-acdf-543e310b5a74", "예제1")
        val targetExample = example.awaitSingleOrNull()

        assertNotNull(targetExample)
        targetExample?.let {
            println(it)
        }
    }

    private fun generateExample(name: String): Example = Example(
        id = UUID.randomUUID().toString(),
        name = name,
        locationList = listOf(Location()),
        info = Info(email = "qwe")
    )
}