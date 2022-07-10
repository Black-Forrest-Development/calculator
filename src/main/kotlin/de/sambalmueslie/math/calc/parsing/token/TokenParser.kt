package de.sambalmueslie.math.calc.parsing.token

interface TokenParser {
    fun suitable(index: Int, c: Char): Boolean
    fun process(context: TokenizerContext, index: Int, c: Char)
}
