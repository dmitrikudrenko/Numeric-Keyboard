package io.github.dmitrikudrenko.numerickeyboard.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import io.github.dmitrikudrenko.numerickeyboard.NumericKeyboard;
import io.github.dmitrikudrenko.numerickeyboard.editor.NumericEditText;
import io.github.dmitrikudrenko.numerickeyboard.setup.IKeyboardSetup;
import io.github.dmitrikudrenko.numerickeyboard.setup.KeyboardSetup;

public class SampleActivity extends AppCompatActivity {

    private ViewGroup content;
    private View space;
    private NumericKeyboard keyboard;

    private NumericEditText numericEditText;
    private EditText simpleEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sample);
        injectViews();
        settingKeyboard(content, space, keyboard);
    }

    private void settingKeyboard(ViewGroup content, View space, NumericKeyboard keyboard) {
        IKeyboardSetup keyboardSetup = new KeyboardSetup(content, space, keyboard);
        keyboardSetup.setupSimpleEditTextViews(simpleEditText);
        keyboardSetup.setupNumericEditTextViews(numericEditText);
    }

    private void injectViews() {
        content = (ViewGroup) findViewById(R.id.content);
        space = findViewById(R.id.space);
        keyboard = (NumericKeyboard) findViewById(R.id.keyboard);
        numericEditText = (NumericEditText) findViewById(R.id.numeric_edit_text);
        simpleEditText = (EditText) findViewById(R.id.simple_edit_text);
    }
}
