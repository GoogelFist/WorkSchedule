package com.github.googelfist.workschedule.domain.schedulegenerator.formatter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class DateFormatterImplTest {

    @Test
    fun `should return correct formatted string`() {
        val date = LocalDate.parse(DATE)

        val dateFormatter = DateFormatterImpl()
        val actualString = dateFormatter.format(date)

        Assertions.assertEquals(EXPECTED_STRING, actualString)
    }

    companion object {
        private const val DATE = "2022-01-01"
        private const val EXPECTED_STRING = "January 2022"
    }
}
