package com.azamovhudstc.animalwiki.repo

import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    fun networkState(): Boolean
}