package com.chimbori.colorpicker.sample;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.fragment.app.DialogFragment;
import com.chimbori.colorpicker.ColorPickerView;
import com.chimbori.colorpicker.OnColorChangedListener;
import com.chimbori.colorpicker.OnColorSelectedListener;
import com.chimbori.colorpicker.builder.ColorPickerClickListener;
import com.chimbori.colorpicker.builder.ColorPickerDialogBuilder;

public class SampleActivity extends AppCompatActivity {
  private View root;
  private int currentBackgroundColor = 0xffffffff;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
    root = findViewById(R.id.color_screen);
    changeBackgroundColor(currentBackgroundColor);

    findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final Context context = SampleActivity.this;

        ColorPickerDialogBuilder
            .with(context)
            .setTitle(R.string.color_dialog_title)
            .initialColor(currentBackgroundColor)
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(12)
            .setOnColorChangedListener(new OnColorChangedListener() {
              @Override
              public void onColorChanged(int selectedColor) {
                // Handle on color change
                Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
              }
            })
            .setOnColorSelectedListener(new OnColorSelectedListener() {
              @Override
              public void onColorSelected(int selectedColor) {
                toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
              }
            })
            .setPositiveButton("ok", new ColorPickerClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                changeBackgroundColor(selectedColor);
                if (allColors != null) {
                  StringBuilder sb = null;

                  for (Integer color : allColors) {
                    if (color == null)
                      continue;
                    if (sb == null)
                      sb = new StringBuilder("Color List:");
                    sb.append("\r\n#" + Integer.toHexString(color).toUpperCase());
                  }

                  if (sb != null)
                    Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                }
              }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
              }
            })
            .showColorEdit(true)
            .setColorEditTextColor(ContextCompat.getColor(SampleActivity.this, android.R.color.holo_blue_bright))
            .build()
            .show();
      }
    });
    findViewById(R.id.btn_adapted_dialog).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment dialogFragment = new AdaptedDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "adapted_dialog");
      }
    });
    findViewById(R.id.btn_prefs).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(SampleActivity.this, PrefsActivity.class));
      }
    });
    findViewById(R.id.btn_view).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(SampleActivity.this, SampleActivity2.class);
        startActivity(intent);
      }
    });
    findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final Intent intent = new Intent(SampleActivity.this, SampleActivity3.class);
        startActivity(intent);
      }
    });
  }

  private void changeBackgroundColor(int selectedColor) {
    currentBackgroundColor = selectedColor;
    root.setBackgroundColor(selectedColor);
  }

  private void toast(String text) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
