package de.sambalmueslie.math.calc.parsing.token


import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EquationTokenizer {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(EquationTokenizer::class.java)
    }

    private val parser = listOf(
        // careful with the order, process group first cause the group token is going to be removed by adding a complex number
        TokenParserGroup(),
        TokenParserComplexNumber(),
        TokenParserOperation(),
    )

    fun tokenize(equation: String): List<Token> {
        logger.info("Tokenize '$equation'")
        val context = TokenizerContext()

        // split the equation into tokens
        equation.toCharArray().filter { !it.isWhitespace() }
            .forEachIndexed { index, c -> tokenize(context, index, c) }
        return context.token
    }

    private fun tokenize(context: TokenizerContext, index: Int, c: Char) {
        val parser = findTokenParser(index, c)
        if (parser.isEmpty()) throw IllegalStateException("Cannot tokenize equation invalid symbol at $index $c")
        parser.forEach { it.process(context, index, c) }
    }

    private fun findTokenParser(index: Int, c: Char): List<TokenParser> {
        return parser.filter { it.suitable(index, c) }
    }

}
