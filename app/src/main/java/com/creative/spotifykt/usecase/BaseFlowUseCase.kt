package com.creative.spotifykt.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

abstract class BaseFlowUseCase<In,Out> {
    protected abstract fun create(input: In): Flow<Out>
    fun execute(input: In): Flow<Out> {
        return create(input)
    }
}