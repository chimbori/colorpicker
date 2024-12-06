package com.chimbori.colorpicker.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.chimbori.colorpicker.ColorPickerView;
import com.chimbori.colorpicker.builder.ColorPickerDialogBuilder;
import static android.widget.Toast.LENGTH_SHORT;

public class SampleActivity extends AppCompatActivity {
  private View colorPreview;
  private int currentBackgroundColor = 0xffffffff;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
    colorPreview = findViewById(R.id.color_preview);
    changeBackgroundColor(currentBackgroundColor);

    findViewById(R.id.btn_dialog).setOnClickListener(v ->
        ColorPickerDialogBuilder
            .with(this)
            .setTitle(R.string.color_dialog_title)
            .initialColor(currentBackgroundColor)
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(12)
            .setOnColorChangedListener(selectedColor ->
                Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor)))
            .setOnColorSelectedListener(selectedColor ->
                Toast.makeText(this, "onColorSelected: 0x" + Integer.toHexString(selectedColor), LENGTH_SHORT).show())
            .setPositiveButton("ok", (dialog, selectedColor, allColors) -> {
              changeBackgroundColor(selectedColor);
              if (allColors != null) {
                StringBuilder sb = null;

                for (Integer color : allColors) {
                  if (color == null)
                    continue;
                  if (sb == null)
                    sb = new StringBuilder("Color List:");
                  sb.append("\r\n#").append(Integer.toHexString(color).toUpperCase());
                }

                if (sb != null)
                  Toast.makeText(getApplicationContext(), sb.toString(), LENGTH_SHORT).show();
              }
            })
            .setNegativeButton("cancel", (dialog, which) -> {
            })
            .showColorEdit(true)
            .setColorEditTextColor(ContextCompat.getColor(SampleActivity.this, android.R.color.holo_blue_bright))
            .build()
            .show());
    findViewById(R.id.btn_view).setOnClickListener(view ->
        startActivity(new Intent(this, SampleActivity2.class)));

    ((ColorPickerView) findViewById(R.id.color_picker)).addOnColorChangedListener(this::changeBackgroundColor);
  }

  private void changeBackgroundColor(int selectedColor) {
    currentBackgroundColor = selectedColor;
    colorPreview.setBackgroundColor(selectedColor);
  }
}
