package de.sambalmueslie.math.calc.parser

interface Parser {
    fun suitable(c: Char): Boolean
    fun process(index: Int, c: Char)
}
