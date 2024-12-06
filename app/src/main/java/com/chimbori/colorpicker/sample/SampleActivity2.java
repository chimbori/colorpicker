package com.chimbori.colorpicker.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.chimbori.colorpicker.ColorPickerView;
import com.chimbori.colorpicker.OnColorChangedListener;
import com.chimbori.colorpicker.OnColorSelectedListener;

public class SampleActivity2 extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample2);

    ColorPickerView colorPickerView = findViewById(R.id.color_picker_view);
    colorPickerView.addOnColorChangedListener(new OnColorChangedListener() {
      @Override
      public void onColorChanged(int selectedColor) {
        // Handle on color change
        Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
      }
    });
    colorPickerView.addOnColorSelectedListener(new OnColorSelectedListener() {
      @Override
      public void onColorSelected(int selectedColor) {
        Toast.makeText(
            SampleActivity2.this,
            "selectedColor: " + Integer.toHexString(selectedColor).toUpperCase(),
            Toast.LENGTH_SHORT).show();
      }
    });
  }
}
