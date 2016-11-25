package io.github.dmitrikudrenko.numerickeyboard.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import io.github.dmitrikudrenko.numerickeyboard.NumericKeyboard;
import io.github.dmitrikudrenko.numerickeyboard.editor.NumericEditText;
import io.github.dmitrikudrenko.numerickeyboard.setup.IKeyboardManager;
import io.github.dmitrikudrenko.numerickeyboard.setup.KeyboardManager;

public class SampleActivity extends AppCompatActivity {

    private ViewGroup content;
    private View space;
    private NumericKeyboard keyboard;

    private EditText simpleEditText1;
    private EditText simpleEditText2;
    private EditText simpleEditText3;
    private EditText simpleEditText4;
    private NumericEditText numericEditText1;
    private NumericEditText numericEditText2;
    private NumericEditText numericEditText3;
    private NumericEditText numericEditText4;

    private IKeyboardManager keyboardSetup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sample);
        injectViews();
        settingKeyboard(content, space, keyboard);
    }

    private void settingKeyboard(ViewGroup content, View space, NumericKeyboard keyboard) {
        keyboardSetup = new KeyboardManager(content, space, keyboard);
        keyboardSetup.setupSimpleEditTextViews(simpleEditText1, simpleEditText2, simpleEditText3, simpleEditText4);
        keyboardSetup.setupNumericEditTextViews(numericEditText1, numericEditText2, numericEditText3, numericEditText4);
    }

    private void injectViews() {
        content = (ViewGroup) findViewById(R.id.content);
        space = findViewById(R.id.space);
        keyboard = (NumericKeyboard) findViewById(R.id.keyboard);
        numericEditText1 = (NumericEditText) findViewById(R.id.numeric_edit_text_1);
        numericEditText2 = (NumericEditText) findViewById(R.id.numeric_edit_text_2);
        numericEditText3 = (NumericEditText) findViewById(R.id.numeric_edit_text_3);
        numericEditText4 = (NumericEditText) findViewById(R.id.numeric_edit_text_4);
        simpleEditText1 = (EditText) findViewById(R.id.simple_edit_text_1);
        simpleEditText2 = (EditText) findViewById(R.id.simple_edit_text_2);
        simpleEditText3 = (EditText) findViewById(R.id.simple_edit_text_3);
        simpleEditText4 = (EditText) findViewById(R.id.simple_edit_text_4);
    }

    @Override
    public void onBackPressed() {
        if (!keyboardSetup.onBackPressed())
            super.onBackPressed();
    }
}
