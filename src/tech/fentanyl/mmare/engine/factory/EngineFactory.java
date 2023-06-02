/**
 * Copyright (C) 2023, darraghd493
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package tech.fentanyl.mmare.engine.factory;

import tech.fentanyl.mmare.engine.Engine;
import tech.fentanyl.mmare.instruction.Instruction;
import tech.fentanyl.mmare.instruction.Operation;

import java.util.Random;

/**
 * A factory class for creating engines
 *
 * @see tech.fentanyl.mmare.engine.Engine
 *
 * @since 1.0
 * @version 1.0
 *
 * @category tech.fentanyl.mmare.engine.factory
 * @package tech.fentanyl.mmare.engine.factory
 * @license MIT License
 *
 * @author darraghd493
 */
public class EngineFactory {
    /**
     * The random number generator used to generate random instructions
     */
    private static final Random RANDOM = new Random();

    /**
     * Creates an engine with a random number of instructions
     * @param instructions The number of instructions to add to the engine
     * @return The engine
     */
    public static Engine createEngine(int instructions) {
        Engine engine = new Engine();

        for (int i = 0; i < instructions; i++) {
            // Select random enum from Operation
            Operation operation = Operation.values()[RANDOM.nextInt(Operation.values().length)];
            Instruction instruction = new Instruction(operation);

            // Select a random integer value
            int value = 1111111111 + RANDOM.nextInt(999999999);

            // Add the instruction to the engine
            engine.instructions.put(instruction, value);
        }

        return engine;
    }
}
