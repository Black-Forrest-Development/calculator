package de.sambalmueslie.math.calc.parser


import de.sambalmueslie.math.calc.operation.Operation
import de.sambalmueslie.math.calc.operation.ValueOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class OperationParser {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(OperationParser::class.java)
    }


    fun parse(equation: String): Operation {
        logger.info("Parse '$equation'")
        val context = ParserContext()

        val availableParser = listOf(
            ComplexNumberParser(context),
            AddOperationParser(context),
            SubtractOperationParser(context),
            GroupOperationParser(context)
        )

        equation.toCharArray().forEachIndexed { index, c ->
            if (c.isWhitespace()) return@forEachIndexed
            val parser = determineParser(c, availableParser)
            if (parser.isEmpty()) throw IllegalStateException("Cannot parse equation invalid symbol at $index $c")
            parser.forEach { it.process(index, c) }
        }
        context.finishProcessing()
        return context.root.getOperation()
    }

    private fun determineParser(c: Char, availableParser: List<Parser>): List<Parser> {
        val result = availableParser.filter { it.suitable(c) }
        logger.debug("Determined parser ${result.joinToString { it::class.java.simpleName }}")
        return result
    }

}
