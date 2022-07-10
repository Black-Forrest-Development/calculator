package de.sambalmueslie.math.calc.parsing.token

import de.sambalmueslie.math.calc.api.ComplexNumber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class EquationTokenizerTest {

    @Test
    fun `tokenize a simple complex number`() {
        val equation = "(1,2)"
        val tokenizer = EquationTokenizer()
        val result = tokenizer.tokenize(equation)

        assertEquals(listOf(TokenComplexNumber(ComplexNumber(1.0, 2.0))), result)
    }

    @Test
    fun `tokenize the addition of two complex numbers`() {
        val equation = "(1,2) + (3,4)"
        val tokenizer = EquationTokenizer()
        val result = tokenizer.tokenize(equation)

        assertEquals(
            listOf(
                TokenComplexNumber(ComplexNumber(1.0, 2.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(3.0, 4.0)),
            ), result
        )
    }


    @Test
    fun `tokenize the addition of three complex numbers`() {
        val equation = "(1,2) + (3,4) + (5,6)"
        val tokenizer = EquationTokenizer()
        val result = tokenizer.tokenize(equation)

        assertEquals(
            listOf(
                TokenComplexNumber(ComplexNumber(1.0, 2.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(3.0, 4.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(5.0, 6.0)),
            ), result
        )
    }

    @Test
    fun `tokenize the addition of three complex numbers and subtract another one`() {
        val equation = "(1,2) + (3,4) + (5,6) - (7,8)"
        val tokenizer = EquationTokenizer()
        val result = tokenizer.tokenize(equation)

        assertEquals(
            listOf(
                TokenComplexNumber(ComplexNumber(1.0, 2.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(3.0, 4.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(5.0, 6.0)),
                TokenOperation(OPERATION_TYPE.SUBTRACTION),
                TokenComplexNumber(ComplexNumber(7.0, 8.0)),
            ), result
        )
    }


    @Test
    fun `tokenize the addition of three complex numbers and subtract another one with a group`() {
        val equation = "(1,2) + (3,4) + ((5,6) - (7,8))"
        val tokenizer = EquationTokenizer()
        val result = tokenizer.tokenize(equation)

        assertEquals(
            listOf(
                TokenComplexNumber(ComplexNumber(1.0, 2.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(3.0, 4.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenGroup(true),
                TokenComplexNumber(ComplexNumber(5.0, 6.0)),
                TokenOperation(OPERATION_TYPE.SUBTRACTION),
                TokenComplexNumber(ComplexNumber(7.0, 8.0)),
                TokenGroup(false),
            ), result
        )
    }

    @Test
    fun `tokenize the a little more equation and a some combined groups`() {
        val equation = "(((1,2) + (3,4)) + ((5,6) - (7,8)))"
        val tokenizer = EquationTokenizer()
        val result = tokenizer.tokenize(equation)

        assertEquals(
            listOf(
                TokenGroup(true),
                TokenGroup(true),
                TokenComplexNumber(ComplexNumber(1.0, 2.0)),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenComplexNumber(ComplexNumber(3.0, 4.0)),
                TokenGroup(false),
                TokenOperation(OPERATION_TYPE.ADDITION),
                TokenGroup(true),
                TokenComplexNumber(ComplexNumber(5.0, 6.0)),
                TokenOperation(OPERATION_TYPE.SUBTRACTION),
                TokenComplexNumber(ComplexNumber(7.0, 8.0)),
                TokenGroup(false),
                TokenGroup(false),
            ), result
        )
    }
}
