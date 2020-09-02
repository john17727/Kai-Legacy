package com.example.kai.business.data.network

import com.example.kai.business.data.network.NetworkErrors.NETWORK_DATA_NULL
import com.example.kai.business.data.network.NetworkErrors.NETWORK_ERROR
import com.example.kai.business.domain.state.*

abstract class ApiResponseHandler<ViewState, Data>(
    private val response: ApiResult<Data?>,
    private val stateEvent: StateEvent?
) {

    suspend fun getResult(): DataState<ViewState> {
        return when (response) {
            is ApiResult.GenericError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage}",
                        uiComponentType = UIComponentType.Dialog,
                        messageType = MessageType.Error
                    ),
                    stateEvent = stateEvent
                )

            }
            is ApiResult.NetworkError -> {
                DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: $NETWORK_ERROR",
                        uiComponentType = UIComponentType.Dialog,
                        messageType = MessageType.Error
                    ),
                    stateEvent = stateEvent
                )

            }
            is ApiResult.Success -> {
                response.value?.let {
                    handleSuccess(resultObj = response.value)
                }?: DataState.error(
                    response = Response(
                        message = "${stateEvent?.errorInfo()}\n\nReason: $NETWORK_DATA_NULL",
                        uiComponentType = UIComponentType.Dialog,
                        messageType = MessageType.Error
                    ),
                    stateEvent = stateEvent
                )
            }
        }
    }

    abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>
}