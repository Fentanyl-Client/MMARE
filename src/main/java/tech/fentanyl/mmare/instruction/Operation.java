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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an operation that can be performed.
 *
 * @see tech.fentanyl.mmare.instruction.Instruction
 *
 * @since 1.0
 * @version 1.0
 *
 * @license MIT License
 *
 * @author darraghd493
 */

@Getter
@AllArgsConstructor
public enum Operation {
    ADD(0x00), // + (addition)
    SUBTRACT(0x01), // - (subtraction)
    MULTIPLY(0x02), // * (multiplication)
    DIVIDE(0x03), // / (division)
    MODULO(0x04), // % (modulo)
    EXPONENT(0x05), // ^ (exponent)
    BITWISE_AND(0x06), // & (bitwise and)
    BITWISE_OR(0x07), // | (bitwise or)
    BITWISE_XOR(0x08), // ^ (bitwise xor)
    BITWISE_NOT(0x09), // ~ (bitwise not)
    BITWISE_LEFT_SHIFT(0x0A), // << (bitwise left shift)
    BITWISE_RIGHT_SHIFT(0x0B), // >> (bitwise right shift)
    BITWISE_UNSIGNED_RIGHT_SHIFT(0x0C); // >>> (bitwise unsigned right shift)

    /**
     * The opcode of the operation
     */
    private final int opcode;
}