package io.github.dmitrikudrenko.numerickeyboard.editor

import io.github.dmitrikudrenko.numerickeyboard.Key
import io.github.dmitrikudrenko.numerickeyboard.Mode

interface INumericEditor {
    fun getMode(): Mode
    fun isUnlimited(): Boolean
    fun onKeyDown(key: Key, ch: CharSequence?)
}
