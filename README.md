# MMARE
MMARE (Make Me A Random Engine!) is a Java library which provides a randomly generated random number engine which can be serialised and deserialised.<br><br>
It provides a way to generate unbiased numbers completely random with a progressively updating seed and variables to ensure that you never get the same number repeatedly.

## Installation
You can install this package using [Jitpack](https://jitpack.io/#Fentanyl-Client/MMARE).

### Gradle
```gradle
dependencies {
    implementation 'com.github.Fentanyl-Client:MMARE:1.0.2'
}
```

### Maven
```xml
<dependencies>
    <dependency>
        <groupId>com.github.Fentanyl-Client</groupId>
        <artifactId>MMARE</artifactId>
        <version>1.0.2</version>
    </dependency>
</dependencies>
```

or you can check the [releases](https://github.com/Fentanyl-Client/MMARE/releases/latest) for a compiled .jar.

## Usage
```java
import tech.fentanyl.mmare.engine.Engine;
import tech.fentanyl.mmare.engine.factory.EngineFactory;

public class Main {
    public static void main(String[] args) {
        Engine engine = EngineFactory.createEngine(1000); // Create an engine with 1000 instructions
        System.out.println(engine.nextInt()); // Run the engine and print the result
    }
}
```

## Changelog
### 1.0.0
- Initial release

## License
This project is licensed under the MIT Licence - see the [LICENCE](LICENSE) file for details
```
Copyright (C) 2023, darraghd493

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
