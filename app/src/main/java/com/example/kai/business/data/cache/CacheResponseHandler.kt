package com.example.kai.business.data.cache

import com.example.kai.business.data.cache.CacheErrors.CACHE_DATA_NULL
import com.example.kai.business.domain.state.*

abstract class CacheResponseHandler<ViewState, Data>(
    private val response: CacheResult<Data?>,
    private val stateEvent: StateEvent?
) {

    suspend fun getResult(): DataState<ViewState>? {
        return when (response) {
            is CacheResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage}",
                        uiComponentType = UIComponentType.Dialog,
                        messageType = MessageType.Error
                    ),
                    stateEvent = stateEvent
                )
            }

            is CacheResult.Success -> {
                response.value?.let {
                    handleSuccess(resultObj = response.value)
                }?: DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: $CACHE_DATA_NULL",
                        uiComponentType = UIComponentType.Dialog,
                        messageType = MessageType.Error
                    ),
                    stateEvent = stateEvent
                )
            }
        }
    }

    abstract fun handleSuccess(resultObj: Data): DataState<ViewState>
}