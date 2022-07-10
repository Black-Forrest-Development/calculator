package de.sambalmueslie.math.calc


import de.sambalmueslie.math.calc.parser.OperationParser
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun main() {
    val calc = Calculator()
    println("Enter a complex equation e.g. (5,2) + (3,4) or nothing for sample")
    val equation = readln().ifBlank { "(1,2) + (3,4) + ((5,6) - (7,8))" }
    val result = calc.solve(equation)
    println("$equation = $result")
}

class Calculator {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(Calculator::class.java)
    }

    private val parser = OperationParser()

    fun solve(equation: String): String {
        val operation = parser.parse(equation)
        return operation.solve().format()
    }
}
