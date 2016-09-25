package io.github.dmitrikudrenko.numerickeyboard.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.dmitrikudrenko.numerickeyboard.NumericKeyboard;
import io.github.dmitrikudrenko.numerickeyboard.editor.NumericEditText;

public class SampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sample);

        NumericEditText editText = (NumericEditText) findViewById(R.id.edit_text);
        NumericKeyboard keyboard = (NumericKeyboard) findViewById(R.id.keyboard);

        keyboard.setNumericEditor(editText);
    }
}
