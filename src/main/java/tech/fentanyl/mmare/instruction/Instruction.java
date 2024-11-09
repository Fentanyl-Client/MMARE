/**
 * Copyright (C) 2023, darraghd493
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package tech.fentanyl.mmare.instruction;

import lombok.Getter;

import java.io.Serializable;

/**
 * Represents an instruction that can be executed by the MMARE engine.
 *
 * @see tech.fentanyl.mmare.engine.Engine
 *
 * @since 1.0
 * @version 1.0.2
 *
 * @license MIT License
 *
 * @author darraghd493
 */

@Getter
@SuppressWarnings("unused")
public class Instruction implements Serializable {
    private final int opcode;

    /**
     * Creates a new instruction with the specified operation.
     * @param operation The operation of the instruction
     */
    public Instruction(Operation operation) {
        this.opcode = operation.getOpcode();
    }

    /**
     * Creates a new instruction with the specified opcode.
     * @param opcode The opcode of the instruction
     */
    public Instruction(int opcode) {
        this.opcode = opcode;
    }

    /**
     * Runs the instruction with the specified values.
     * @param a The first value
     * @param b The second value
     * @return The result of the instruction
     */
    public long run(long a, long b) {
        // Prevent recessive values
        if (a == b) {
            a = a >> 4;
            b = b >> 6;
        }

        // Safety check
        a = a == 0 ? b == 0 ? 1 : b : a;
        b = b == 0 ? a == 1 ? 2 : a : b;

        // Run the instruction
        switch (this.opcode) {
            case 0x00: return a + b; // Addition
            case 0x01: return a - b; // Subtraction
            case 0x02: return a * b; // Multiplication
            case 0x03: return a > b ? a / b : b / a; // Division
            case 0x04: return a > b ? a % b : b % a; // Modulo
            case 0x05: return a << b; // Bitwise left shift
            case 0x06: return a >> b; // Bitwise right shift
            default:
                throw new UnsupportedOperationException("Unknown opcode: " + this.opcode);
        }
    }

    /**
     * Serializes the instruction to a byte array.
     * @return The serialized instruction
     */
    public byte[] serialize() {
        return new byte[]{(byte) this.opcode};
    }

    /**
     * Deserializes the instruction from a byte array.
     * @param data The data to deserialize
     * @return The deserialized instruction
     */
    public static Instruction deserialize(byte[] data) {
        return new Instruction(data[0]);
    }
}
