package de.sambalmueslie.math.calc.parser


import de.sambalmueslie.math.calc.operation.BaseOperation
import de.sambalmueslie.math.calc.operation.ValueOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class BaseOperationParser(private val context: ParserContext) : Parser {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(BaseOperationParser::class.java)
    }

    protected fun handleOperation(index: Int, factory: () -> BaseOperation) {
        val operation = factory.invoke()

        val values = context.getAndClearTempValues()
        if (context.openGroups.isEmpty()) {
            handleOperationOnGroup(operation, values, context.root)
        } else {
            context.openGroups.forEach { handleOperationOnGroup(operation, values, it) }
        }
    }

    private fun handleOperationOnGroup(operation: BaseOperation, values: List<ValueOperation>, group: OperationGroup) {
        val last = group.operations.lastOrNull()
        if (last == null || last::class != operation::class) {
            group.add(operation)
        }


        if (last == null) {
            values.forEach { operation.append(it) }
        } else {
            values.forEach { last.append(it) }
            operation.append(last)
        }
    }


}
