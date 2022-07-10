package de.sambalmueslie.math.calc.parser


import de.sambalmueslie.math.calc.operation.AddOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AddOperationParser(context: ParserContext) : BaseOperationParser(context) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(AddOperationParser::class.java)
        private const val OPERATOR_ADD = '+'
    }

    override fun suitable(c: Char): Boolean {
        return c == OPERATOR_ADD
    }

    override fun process(index: Int, c: Char) {
        when (c) {
            OPERATOR_ADD -> handleOperation(index) { AddOperation() }
        }
    }

}
