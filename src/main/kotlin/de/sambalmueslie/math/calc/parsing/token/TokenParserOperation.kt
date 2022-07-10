package de.sambalmueslie.math.calc.parsing.token


import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TokenParserOperation : TokenParser {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TokenParserOperation::class.java)
        private const val OPERATOR_ADD = '+'
        private const val OPERATOR_SUBTRACT = '-'
    }

    override fun suitable(index: Int, c: Char): Boolean {
        return c == OPERATOR_ADD || c == OPERATOR_SUBTRACT
    }

    override fun process(context: TokenizerContext, index: Int, c: Char) {
        val token = when (c) {
            OPERATOR_ADD -> TokenOperation(OPERATION_TYPE.ADDITION)
            OPERATOR_SUBTRACT -> TokenOperation(OPERATION_TYPE.SUBTRACTION)
            else -> throw IllegalArgumentException("Unknown operator type '$c'")
        }
        context.token.add(token)
    }


}
