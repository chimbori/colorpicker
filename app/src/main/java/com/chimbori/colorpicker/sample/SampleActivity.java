package com.chimbori.colorpicker.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import com.chimbori.colorpicker.ColorPickerView;
import com.chimbori.colorpicker.builder.ColorPickerDialogBuilder;
import static android.widget.Toast.LENGTH_SHORT;

public class SampleActivity extends AppCompatActivity {
  private View root;
  private int currentBackgroundColor = 0xffffffff;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
    root = findViewById(R.id.color_screen);
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
    findViewById(R.id.btn_adapted_dialog).setOnClickListener(view ->
        new AdaptedDialogFragment().show(getSupportFragmentManager(), "adapted_dialog"));
    findViewById(R.id.btn_view).setOnClickListener(view ->
        startActivity(new Intent(this, SampleActivity2.class)));
    findViewById(R.id.btn_fragment).setOnClickListener(view ->
        startActivity(new Intent(this, SampleActivity3.class)));
  }

  private void changeBackgroundColor(int selectedColor) {
    currentBackgroundColor = selectedColor;
    root.setBackgroundColor(selectedColor);
  }

  public static class AdaptedDialogFragment extends AppCompatDialogFragment {
    public AdaptedDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_adapted_dialog, container);
    }
  }
}
