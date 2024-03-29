package com.example.kai.business.domain.state

import android.view.View
//import com.codingwithmitch.cleannotes.util.TodoCallback


data class StateMessage(val response: Response)

data class Response(
    val message: String?,
    val uiComponentType: UIComponentType,
    val messageType: MessageType
)

sealed class UIComponentType{

    object Toast : UIComponentType()

    object Dialog : UIComponentType()

//    class AreYouSureDialog(
//        val callback: AreYouSureCallback
//    ): UIComponentType()

//    class SnackBar(
//        val undoCallback: SnackBarUndoCallback? = null,
//        val onDismissCallback: TodoCallback? = null
//    ): UIComponentType()

    object None : UIComponentType()
}

sealed class MessageType{

    object Success : MessageType()

    object Error : MessageType()

    object Info : MessageType()

    object None : MessageType()
}


interface StateMessageCallback{

    fun removeMessageFromStack()
}


interface AreYouSureCallback {

    fun proceed()

    fun cancel()
}

interface SnackBarUndoCallback {

    fun undo()
}

class SnackBarUndoListener
constructor(
    private val snackBarUndoCallback: SnackBarUndoCallback?
): View.OnClickListener {

    override fun onClick(v: View?) {
        snackBarUndoCallback?.undo()
    }

}


interface DialogInputCaptureCallback {

    fun onTextCaptured(text: String)
}