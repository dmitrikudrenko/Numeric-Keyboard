package io.github.dmitrikudrenko.numerickeyboard.setup

import android.widget.EditText
import io.github.dmitrikudrenko.numerickeyboard.editor.NumericEditText


interface IKeyboardManager {
    fun setupSimpleEditTextViews(vararg views: EditText)

    fun setupNumericEditTextViews(vararg views: NumericEditText)

    fun onBackPressed(): Boolean

    interface Callback {
        fun onCompleted()
    }
}