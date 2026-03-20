package com.dogi.a3calc;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.InputMismatchException;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText UserX = (EditText)findViewById(R.id.editText);
        TextView res = (TextView)findViewById(R.id.textView);
        Button abc = (Button)findViewById(R.id.button4);

        UserX.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String resultText = dest.subSequence(0, dstart) + source.toString() + dest.subSequence(dend, dest.length());

                // Запрещаем "00" в начале
                if (resultText.startsWith("00")) {
                    return "";
                }
                if (resultText.startsWith("-00")) {
                    return "";
                }
                // Если стоит "0", а вводят цифру (не точку) — заменяем 0 на эту цифру
                if (dest.toString().equals("0") && !source.toString().equals(".")) {
                    // Если курсор стоит после нуля (dstart == 1), заменяем 0 на новую цифру
                    if (dstart == 1) {
                        return "source";
                    }
                }
                return null;
            }
        }});

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void OnClick(View view) {
        EditText UserX = (EditText)findViewById(R.id.editText);
        TextView res = (TextView)findViewById(R.id.textView);
        String x = UserX.getText().toString().trim();
        if (x.isEmpty()) {
            res.setText("Результат: пустое поле ввода");
        }
        else {
            double y = Double.parseDouble(x);
            double a = 0.0;
            double b = 0.0;
            double c = 0.0;
            double d = 0.0;
            a = 2 * y + 5;
            b = Math.pow(y, 3) + 2;
            c = a / b;
            d = Math.cbrt(c);
            d = Math.abs(d);
            String str = String.valueOf(d);
            res.setText("Результат: " + str);
        }
    }

    public void clear(View view) {
        EditText UserX = (EditText)findViewById(R.id.editText);
        TextView res = (TextView)findViewById(R.id.textView);
        UserX.setText("");
    }
}