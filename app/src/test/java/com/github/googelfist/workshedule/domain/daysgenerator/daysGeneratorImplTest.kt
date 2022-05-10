//package com.github.googelfist.workshedule.domain.daysgenerator
//
//import com.github.googelfist.workshedule.domain.models.Day
//import com.github.googelfist.workshedule.domain.monthgenerator.DateNowContainer
//import com.github.googelfist.workshedule.domain.monthgenerator.def.daysgenerator.DefaultDaysGeneratorImpl
//import com.github.googelfist.workshedule.domain.monthgenerator.def.fabric.DefaultDaysFabricImpl
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito
//import org.mockito.kotlin.mock
//import java.time.LocalDate
//
//internal class daysGeneratorImplTest {
//
//    private val mockDateNowContainer = mock<DateNowContainer>()
//    private lateinit var expectedDayList: List<Day>
//
//    @BeforeEach
//    fun setUp() {
//        expectedDayList = listOf<Day>(
//            Day(day = 28, month = 3, year = 2022, today = false, currentMonth = false),
//            Day(day = 29, month = 3, year = 2022, today = false, currentMonth = false),
//            Day(day = 30, month = 3, year = 2022, today = false, currentMonth = false),
//            Day(day = 31, month = 3, year = 2022, today = false, currentMonth = false),
//            Day(day = 1, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 2, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 3, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 4, month = 4, year = 2022, today = true, currentMonth = true),
//            Day(day = 5, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 6, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 7, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 8, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 9, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 10, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 11, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 12, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 13, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 14, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 15, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 16, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 17, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 18, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 19, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 20, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 21, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 22, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 23, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 24, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 25, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 26, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 27, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 28, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 29, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 30, month = 4, year = 2022, today = false, currentMonth = true),
//            Day(day = 1, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 2, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 3, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 4, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 5, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 6, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 7, month = 5, year = 2022, today = false, currentMonth = false),
//            Day(day = 8, month = 5, year = 2022, today = false, currentMonth = false)
//        )
//    }
//
//    @AfterEach
//    fun tearDown() {
//        Mockito.reset(mockDateNowContainer)
//    }
//
//    @Test
//    fun `should generate correct default day list`() {
//        val defaultDaysFabric = DefaultDaysFabricImpl(mockDateNowContainer)
//        val daysGenerator = DefaultDaysGeneratorImpl(defaultDaysFabric)
//        val date = LocalDate.parse(TODAY_DATE)
//
//        Mockito.`when`(mockDateNowContainer.getDate()).thenReturn(date)
//
//        val actualDayList = daysGenerator.generateDays(date)
//
//        Assertions.assertEquals(expectedDayList, actualDayList)
//    }
//
//    companion object {
//        private const val TODAY_DATE = "2022-04-04"
//    }
//}