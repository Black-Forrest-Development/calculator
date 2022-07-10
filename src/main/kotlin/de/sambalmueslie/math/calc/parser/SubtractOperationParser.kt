package de.sambalmueslie.math.calc.parser


import de.sambalmueslie.math.calc.operation.SubtractOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SubtractOperationParser(context: ParserContext) : BaseOperationParser(context) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SubtractOperationParser::class.java)
        private const val OPERATOR_SUBTRACT = '-'
    }

    override fun suitable(c: Char): Boolean {
        return c == OPERATOR_SUBTRACT
    }

    override fun process(index: Int, c: Char) {
        when (c) {
            OPERATOR_SUBTRACT -> handleOperation(index) { SubtractOperation() }
        }
    }


}
