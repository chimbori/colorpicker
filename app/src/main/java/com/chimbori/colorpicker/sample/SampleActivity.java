package com.chimbori.colorpicker.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.chimbori.colorpicker.ColorPickerView;
import com.chimbori.colorpicker.builder.ColorPickerDialogBuilder;
import static android.widget.Toast.LENGTH_SHORT;

public class SampleActivity extends AppCompatActivity {
  private View colorPreview;
  private int currentBackgroundColor = 0xffffffff;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_sample);
    colorPreview = findViewById(R.id.color_preview);
    changeBackgroundColor(currentBackgroundColor);

    findViewById(R.id.btn_dialog).setOnClickListener(v ->
        ColorPickerDialogBuilder.with(this)
            .setTitle(R.string.color_dialog_title)
            .initialColor(currentBackgroundColor)
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .setOnColorChangedListener(selectedColor ->
                Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor)))
            .setOnColorSelectedListener(selectedColor ->
                Toast.makeText(this, "onColorSelected: 0x" + Integer.toHexString(selectedColor), LENGTH_SHORT).show())
            .setPositiveButton(android.R.string.ok, (dialog, selectedColor, allColors) -> {
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
            .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
            })
            .showColorEdit(true)
            .setColorEditTextColor(Color.DKGRAY)
            .build()
            .show());

    ((ColorPickerView) findViewById(R.id.color_picker)).addOnColorChangedListener(this::changeBackgroundColor);
  }

  private void changeBackgroundColor(int selectedColor) {
    currentBackgroundColor = selectedColor;
    colorPreview.setBackgroundColor(selectedColor);
  }
}
