/**
 * Copyright (C) 2023, darraghd493
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package tech.fentanyl.mmare.engine;

import lombok.Getter;
import tech.fentanyl.mmare.instruction.Instruction;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * The engine class is the main class of the MMARE engine. It contains the instructions and the seed.
 *
 * @see tech.fentanyl.mmare.instruction.Instruction
 *
 * @since 1.0
 * @version 1.0
 *
 * @category tech.fentanyl.mmare.engine
 * @package tech.fentanyl.mmare.engine
 * @license MIT License
 *
 * @author darraghd493
 */

@Getter
public class Engine implements Serializable {
    public Map<Instruction, Integer> instructions;
    public int seed;
    public boolean iterateSeed;

    /**
     * Creates a new engine.
     */
    public Engine() {
        this.instructions = new HashMap<>();
        this.seed = (int) System.nanoTime();
        this.iterateSeed = true;
    }

    /**
     * Creates a new engine with the specified seed.
     * @param seed The seed to use
     */
    public Engine(int seed) {
        this.instructions = new HashMap<>();
        this.seed = seed;
        this.iterateSeed = false;
    }

    /**
     * Runs the engine with the current seed.
     * This method is private because it is only used internally.
     *
     * @return The result of the engine
     */
    private int run() {
        int result = this.seed;
        Map.Entry<Instruction, Integer> previous = null;

        // Iterate through the instructions
        for (Map.Entry<Instruction, Integer> entry : this.instructions.entrySet()) {
            result = entry.getKey().run(result, entry.getValue());

            // Update the value of the current entry if the previous entry is not null
            if (previous != null) {
                entry.setValue(previous.getKey().run(entry.getValue(), previous.getValue()));
            } else { // Otherwise run the instruction with the seed
                entry.setValue(entry.getKey().run(entry.getValue(), result));
            }

            // Set previous to current entry
            previous = entry;
        }

        if (this.iterateSeed) this.seed = result;
        return result;
    }

    /**
     * Runs the engine with the current seed and returns the result as an integer.
     * @return The result of the engine
     */
    public int nextInt() {
        return run();
    }

    /**
     * Runs the engine with the current seed and returns the result as an integer.
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public int nextInt(int max) {
        return run() % max;
    }

    /**
     * Runs the engine with the current seed and returns the result as an integer.
     * @param min The minimum value of the result
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public int nextInt(int min, int max) {
        return run() % (max - min) + min;
    }

    /**
     * Runs the engine with the current seed and returns the result as a float.
     * @return The result of the engine
     */
    public float nextFloat() {
        return run() / (float) (1 << 31);
    }

    /**
     * Runs the engine with the current seed and returns the result as a float.
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public float nextFloat(float max) {
        return run() % max;
    }

    /**
     * Runs the engine with the current seed and returns the result as a float.
     * @param min The minimum value of the result
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public float nextFloat(float min, float max) {
        return run() % (max - min) + min;
    }

    /**
     * Runs the engine with the current seed and returns the result as a long.
     * @return The result of the engine
     */
    public long nextLong() {
        return run() & 0xFFFFFFFFL;
    }

    /**
     * Runs the engine with the current seed and returns the result as a long.
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public long nextLong(long max) {
        return run() % max;
    }

    /**
     * Runs the engine with the current seed and returns the result as a long.
     * @param min The minimum value of the result
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public long nextLong(long min, long max) {
        return run() % (max - min) + min;
    }

    /**
     * Runs the engine with the current seed and returns the result as a double.
     * @return The result of the engine (double)
     */
    public double nextDouble() {
        return nextLong() / (double) (1L << 63);
    }

    /**
     * Runs the engine with the current seed and returns the result as a double.
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public double nextDouble(double max) {
        return nextLong() % max;
    }

    /**
     * Runs the engine with the current seed and returns the result as a double.
     * @param min The minimum value of the result
     * @param max The maximum value of the result
     * @return The result of the engine
     */
    public double nextDouble(double min, double max) {
        return nextLong() % (max - min) + min;
    }

    /**
     * Runs the engine with the current seed and returns the result as a boolean.
     * @return The result of the engine
     */
    public boolean nextBoolean() {
        return run() % 2 == 0;
    }

    /**
     * Runs the engine with the current seed and returns the result as a boolean.
     * @param chance The chance of the result being true
     * @return The result of the engine
     */
    public boolean nextBoolean(float chance) {
        return nextFloat() < chance;
    }

    /**
     * Serializes the engine to a byte array.
     * @return The serialized engine as a byte array
     * @throws IOException If an I/O error occurs
     */
    public byte[] serialize() throws IOException {
        // Serialize the instructions map
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this.instructions);
        oos.close();

        // Serialize the seed and iterateSeed
        ByteBuffer buffer = ByteBuffer.allocate(9);
        buffer.putInt(this.seed);
        buffer.put((byte) (this.iterateSeed ? 1 : 0));
        buffer.put(baos.toByteArray());
        return buffer.array();
    }

    /**
     * Deserializes the engine from a byte array.
     * @param bytes The byte array to deserialize
     * @return The deserialized engine
     * @throws IOException If an I/O error occurs
     * @throws ClassNotFoundException If the class of a serialized object cannot be found
     */
    public static Engine deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        // Deserialize the seed and iterateSeed
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int seed = buffer.getInt();
        boolean iterateSeed = buffer.get() == 1;

        // Deserialize the instructions map
        byte[] instructionsBytes = new byte[buffer.remaining()];
        buffer.get(instructionsBytes);
        ByteArrayInputStream bais = new ByteArrayInputStream(instructionsBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Map<Instruction, Integer> instructions = (Map<Instruction, Integer>) ois.readObject();
        ois.close();

        // Create the engine
        Engine engine = new Engine(seed);
        engine.iterateSeed = iterateSeed;
        engine.instructions = instructions;
        return engine;
    }
}
