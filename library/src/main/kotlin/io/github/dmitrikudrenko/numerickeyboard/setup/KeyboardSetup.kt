package io.github.dmitrikudrenko.numerickeyboard.setup


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import io.github.dmitrikudrenko.numerickeyboard.NumericKeyboard
import io.github.dmitrikudrenko.numerickeyboard.R
import io.github.dmitrikudrenko.numerickeyboard.editor.NumericEditText
import java.util.concurrent.CopyOnWriteArrayList

class KeyboardSetup(private val content: ViewGroup, private val space: View, private val keyboard: NumericKeyboard) : IKeyboardSetup {

    private val inputMethodManager: InputMethodManager
    private val handler = Handler()
    private val keyboardHeight: Float
    private val keyboardAnimationDuration: Int

    private var state: State? = null

    private val mClosedCallbacks = CopyOnWriteArrayList<IKeyboardSetup.Callback>()
    private val mOpenedCallbacks = CopyOnWriteArrayList<IKeyboardSetup.Callback>()

    private enum class State {
        OPENED, OPENING, CLOSED, CLOSING
    }

    init {
        this.inputMethodManager = content.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.keyboardHeight = content.context.resources.getDimension(R.dimen.keyboardHeight)
        this.keyboardAnimationDuration = content.context.resources.getInteger(R.integer.keyboardAnimationDuration)
        state = State.CLOSED
    }

    private fun closeKeyboard(callback: IKeyboardSetup.Callback?) {
        registerClosedCallback(callback)
        if (state == State.OPENED || state == State.OPENING) {
            space.visibility = View.GONE

            closeKeyboardInternal()

            state = State.CLOSING
        } else if (state == State.CLOSED) dispatchClosedEvent()
    }

    private fun openKeyboard(callback: IKeyboardSetup.Callback?) {
        registerOpenedCallback(callback)
        if (state == State.CLOSED || state == State.CLOSING) {
            val wasSoftKeyboardOpened = inputMethodManager.hideSoftInputFromWindow(content.windowToken, 0, object : ResultReceiver(handler) {
                override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                    openKeyboardInternal()
                }
            })
            if (!wasSoftKeyboardOpened) {
                openKeyboardInternal()
            }

            state = State.OPENING
        } else if (state == State.OPENED) dispatchOpenedEvent()
    }

    private fun openKeyboardInternal() {
        handler.postDelayed({
            space.visibility = View.VISIBLE
            startOpening()
        }, 300)
    }

    private fun closeKeyboardInternal() {
        space.visibility = View.GONE
        val closingAnimation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val height = (keyboardHeight - 0.25 * interpolatedTime.toDouble() * keyboardHeight.toDouble()).toInt()
                val alpha = 1 - interpolatedTime
                keyboard.layoutParams.height = height
                keyboard.alpha = alpha
                keyboard.requestLayout()
            }
        }
        closingAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                dispatchClosedEvent()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })
        closingAnimation.duration = keyboardAnimationDuration.toLong()
        keyboard.startAnimation(closingAnimation)
    }

    private fun startOpening() {
        keyboard.layoutParams.height = (keyboardHeight * 0.75).toInt()
        keyboard.alpha = 0.5f
        val openingAnimation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                val height = (keyboardHeight * (0.75 + 0.25 * interpolatedTime)).toInt()
                val alpha = interpolatedTime
                keyboard.layoutParams.height = height
                keyboard.alpha = alpha
                keyboard.requestLayout()
            }
        }
        openingAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                dispatchOpenedEvent()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })
        openingAnimation.duration = keyboardAnimationDuration.toLong()
        keyboard.startAnimation(openingAnimation)
    }

    override fun setupSimpleEditTextViews(vararg views: EditText) {
        if (views.size > 0) {
            for (editText in views) {
                val inputType = editText.inputType
                editText.setOnTouchListener { v, event ->
                    editText.inputType = InputType.TYPE_NULL
                    editText.onTouchEvent(event)
                    editText.inputType = inputType
                    editText.isFocusable = true

                    true
                }
                editText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        closeKeyboard(object : IKeyboardSetup.Callback {
                            override fun onCompleted() {
                                editText.inputType = inputType
                                inputMethodManager.showSoftInput(editText, 0)
                            }
                        })
                    } else
                        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
                }
            }
        }
    }

    override fun setupNumericEditTextViews(vararg views: NumericEditText) {
        if (views.size > 0) {
            for (view in views) {
                view.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        keyboard.setNumericEditor(view)
                        openKeyboard(null)
                    } else
                        closeKeyboard(null)
                }
            }
        }
    }

    private fun registerOpenedCallback(callback: IKeyboardSetup.Callback?) {
        if (callback != null)
            mOpenedCallbacks.add(callback)
    }

    private fun registerClosedCallback(callback: IKeyboardSetup.Callback?) {
        if (callback != null)
            mClosedCallbacks.add(callback)
    }

    private fun unregisterOpenedCallback(callback: IKeyboardSetup.Callback) {
        mOpenedCallbacks.remove(callback)
    }

    private fun unregisterClosedCallback(callback: IKeyboardSetup.Callback) {
        mClosedCallbacks.remove(callback)
    }

    private fun dispatchOpenedEvent() {
        state = State.OPENED
        for (callback in mOpenedCallbacks) {
            callback.onCompleted()
            unregisterOpenedCallback(callback)
        }
    }

    private fun dispatchClosedEvent() {
        state = State.CLOSED
        for (callback in mClosedCallbacks) {
            callback.onCompleted()
            unregisterClosedCallback(callback)
        }
    }
}
