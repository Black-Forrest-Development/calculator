package de.sambalmueslie.math.calc.parser

interface ParserChangeListener {
    fun parserFinished(index: Int, c: Char)
}
