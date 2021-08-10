package com.maksimzotov.weatherhelper.domain.usecases

import com.maksimzotov.weatherhelper.domain.Repository
import javax.inject.Inject

class GetCurrentFilterUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getCurrentFilter() =
        repository.currentFilter
}