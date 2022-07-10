package de.sambalmueslie.math.calc.parser


import de.sambalmueslie.math.calc.api.ComplexNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ComplexNumberParser(private val context: ParserContext) : Parser {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ComplexNumberParser::class.java)
        private const val START_OF_NUMBER = '('
        private const val END_OF_NUMBER = ')'
        private const val REAL_IMAGINARY_SEPARATOR = ','
    }

    private var realNumber: String = ""
    private var imaginaryNumber: String = ""
    private var parsingImaginaryPart: Boolean = false
    private var processing: Boolean = false

    override fun suitable(c: Char): Boolean {
        return c == START_OF_NUMBER || processing
    }

    override fun process(index: Int, c: Char) {
        when (c) {
            START_OF_NUMBER -> handleStartOfNumber(index)
            END_OF_NUMBER -> handleEndOfNumber(index, c)
            REAL_IMAGINARY_SEPARATOR -> handleRealImaginarySeparator(index)
            else -> handleNumberPart(index, c)
        }
    }


    private fun handleNumberPart(index: Int, c: Char) {
        if (c.isDigit() || c == '.') {
            when (parsingImaginaryPart) {
                false -> realNumber += c
                true -> imaginaryNumber += c
            }
        }
    }

    private fun handleRealImaginarySeparator(index: Int) {
        if (parsingImaginaryPart) throw IllegalStateException("Cannot parse equation invalid symbol at $index")
        parsingImaginaryPart = true
    }

    private fun handleEndOfNumber(index: Int, c: Char) {
        if (!processing) throw IllegalStateException("Cannot parse equation invalid symbol at $index")
        val number = parseComplexNumber(realNumber, imaginaryNumber) ?: throw IllegalStateException("Cannot parse equation invalid symbol at $index")

        context.add(number)

        logger.debug("Finished processing with result ${number.format()}")

        processing = false
        parsingImaginaryPart = false
        realNumber = ""
        imaginaryNumber = ""
        context.listener.forEach { it.parserFinished(index, c) }
    }

    private fun handleStartOfNumber(index: Int) {
        // cheat to avoid error while struggling with double meaning of ( symbol
        if (processing && realNumber.isNotBlank()) {
            throw IllegalStateException("Cannot parse equation invalid symbol at $index")
        }
        processing = true
    }

    private fun parseComplexNumber(realNumber: String, imaginaryNumber: String): ComplexNumber? {
        if (realNumber.isBlank()) return null
        val real = parseNumber(realNumber) ?: return null
        if (imaginaryNumber.isBlank()) return ComplexNumber(real, 0.0)
        val imaginary = parseNumber(imaginaryNumber) ?: return null
        return ComplexNumber(real, imaginary)
    }

    private fun parseNumber(number: String) = number.toDoubleOrNull() ?: number.toIntOrNull()?.toDouble()


}
