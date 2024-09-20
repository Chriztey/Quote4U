package com.chris.quote4u.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepo {
    suspend fun saveOnBoardingState(isCompleted: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}