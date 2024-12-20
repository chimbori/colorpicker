# Color Picker

Color Picker component for Android with a Color Wheel and a Lightness Bar

## Screenshots

### Dialog

<img src="screenshots/dialog.webp" width="400">

### Dark Mode

<img src="screenshots/dark-mode.webp" width="400">

### Light Mode

<img src="screenshots/light-mode.webp" width="400">

### Wheel Type: CIRCLE

<img src="screenshots/wheel-type-circle.webp" width="400">

## Setup

This library is distributed via JitPack:

- Add Maven URL in `allprojects.repositories`

    ```groovy
    allprojects {
      repositories {
        maven { url "https://jitpack.io" }
      }
    }
    ```

- Add a library dependency:

    ```groovy
    dependencies {
      // Use the latest version from https://github.com/chimbori/colorpicker/releases .
      implementation 'com.github.chimbori:colorpicker:x.y.z'
    }
    ```

## Usage

### Use as a Dialog

```java
ColorPickerDialogBuilder.with(context)
  .setTitle("Choose color")
  .showColorEdit(true)
  .initialColor(currentBackgroundColor)
  .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
  .setOnColorChangedListener(selectedColor ->
      Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor)))
  .setOnColorSelectedListener(selectedColor ->
      Toast.makeText(this, "onColorSelected: 0x" + Integer.toHexString(selectedColor), LENGTH_SHORT).show())
  .setPositiveButton(android.R.string.ok, (dialog, selectedColor, allColors) -> {
      changeBackgroundColor(selectedColor);
  })
  .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
  .build()
  .show();
```

### Use as a Custom View

```xml
<com.chimbori.colorpicker.ColorPickerView
  android:id="@+id/color_picker_view"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  app:alphaSlider="true"
  app:lightnessSlider="true"
  app:wheelType="FLOWER"
  app:lightnessSliderView="@+id/v_lightness_slider"
  app:alphaSliderView="@+id/v_alpha_slider"/>

<com.chimbori.colorpicker.slider.LightnessSlider
  android:id="@+id/v_lightness_slider"
  android:layout_width="match_parent"
  android:layout_height="48dp"/>

<com.chimbori.colorpicker.slider.AlphaSlider
  android:id="@+id/v_alpha_slider"
  android:layout_width="match_parent"
  android:layout_height="48dp"/>
```

## License

```
Copyright 2024+ — Chimbori, makers of Hermit Lite Apps Browser
Copyright 2014-2017 — QuadFlask

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
