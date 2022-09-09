package com.check.demo.util

import kotlinx.coroutines.CoroutineDispatcher

data class DispatcherManager(
    val Main: CoroutineDispatcher,
    val IO: CoroutineDispatcher,
    val Default: CoroutineDispatcher
)
