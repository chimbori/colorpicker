package com.chimbori.colorpicker.builder;

import android.content.DialogInterface;

public interface ColorPickerClickListener {
  void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors);
}
