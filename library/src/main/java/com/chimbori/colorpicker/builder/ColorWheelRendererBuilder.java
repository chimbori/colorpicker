package com.chimbori.colorpicker.builder;

import com.chimbori.colorpicker.ColorPickerView;
import com.chimbori.colorpicker.renderer.ColorWheelRenderer;
import com.chimbori.colorpicker.renderer.FlowerColorWheelRenderer;
import com.chimbori.colorpicker.renderer.SimpleColorWheelRenderer;

public class ColorWheelRendererBuilder {
  public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheelType) {
    switch (wheelType) {
      case CIRCLE:
        return new SimpleColorWheelRenderer();
      case FLOWER:
        return new FlowerColorWheelRenderer();
    }
    throw new IllegalArgumentException("wrong WHEEL_TYPE");
  }
}
