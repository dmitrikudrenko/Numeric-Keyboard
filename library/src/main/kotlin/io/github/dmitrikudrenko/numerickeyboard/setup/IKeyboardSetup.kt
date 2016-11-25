package io.github.dmitrikudrenko.numerickeyboard.setup

import android.widget.EditText
import io.github.dmitrikudrenko.numerickeyboard.editor.NumericEditText


interface IKeyboardSetup {
    fun setupSimpleEditTextViews(vararg views: EditText)

    fun setupNumericEditTextViews(vararg views: NumericEditText)

    interface Callback {
        fun onCompleted()
    }
}