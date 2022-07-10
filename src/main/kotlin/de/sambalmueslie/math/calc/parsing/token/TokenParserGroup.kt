package de.sambalmueslie.math.calc.parsing.token


import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TokenParserGroup : TokenParser {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(TokenParserGroup::class.java)
        private const val START_OF_GROUP = '('
        private const val END_OF_GROUP = ')'
    }

    override fun suitable(index: Int, c: Char): Boolean {
        return c == START_OF_GROUP || c == END_OF_GROUP
    }

    override fun process(context: TokenizerContext, index: Int, c: Char) {
        val token = when (c) {
            START_OF_GROUP -> TokenGroup(true)
            END_OF_GROUP -> TokenGroup(false)
            else -> throw IllegalArgumentException("Unknown group type '$c'")
        }
        context.token.add(token)
    }


}
