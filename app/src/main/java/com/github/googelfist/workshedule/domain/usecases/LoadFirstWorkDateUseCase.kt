package com.github.googelfist.workshedule.domain.usecases

import com.github.googelfist.workshedule.domain.Repository
import com.github.googelfist.workshedule.domain.formatter.DateFormatter
import javax.inject.Inject

class LoadFirstWorkDateUseCase @Inject constructor(
    private val repository: Repository,
    private val formatter: DateFormatter
) {
    suspend operator fun invoke(): String {
        val date = repository.loadFirstWorkDate()
        return formatter.formatDateToConfig(date)
    }
}