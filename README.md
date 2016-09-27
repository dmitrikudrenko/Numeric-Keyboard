Numeric keyboard
================

Beautiful keyboard for sales applications

##Screenshots
![Image](https://raw.githubusercontent.com/dmitrikudrenko/Numeric-Keyboard/master/media/screenshot_1.png)
![Image](https://raw.githubusercontent.com/dmitrikudrenko/Numeric-Keyboard/master/media/screenshot_2.png)

Download
--------
[ ![Download](https://api.bintray.com/packages/dmitrikudrenko/maven/Numeric-Keyboard/images/download.svg) ](https://bintray.com/dmitrikudrenko/maven/Numeric-Keyboard/_latestVersion)
[![Build Status](https://travis-ci.org/dmitrikudrenko/Numeric-Keyboard.svg?branch=master)](https://travis-ci.org/dmitrikudrenko/Numeric-Keyboard)

Maven
```xml
<dependency>
  <groupId>io.github.dmitrikudrenko</groupId>
  <artifactId>numeric-keyboard</artifactId>
  <version>$latestVersion</version>
  <type>pom</type>
</dependency>
```

Gradle
```groovy
compile 'io.github.dmitrikudrenko:numeric-keyboard:$latestVersion'
```

Customize
=========
```xml
<!-- button's symbol font -->
<item name="nk_button_fontFamily">sans-serif-light</item>
<!-- button's symbol color -->
<item name="nk_button_textColor">#37474f</item>
<!-- unlimited pressed button color -->
<item name="nk_button_enabledTextColor">#4cb5ab</item>
<!-- button's symbol text size -->
<item name="nk_button_textSize">32sp</item>
<!-- button background (state list) -->
<item name="nk_button_background">@drawable/sel_keyboard_symbol</item>
<!-- done button resource -->
<item name="nk_button_done_src"></item>
<!-- backspace button resource -->
<item name="nk_button_backspace_src"></item>
```

License
=======

    Copyright 2016 Dmitri Kudrenko.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.