package de.sambalmueslie.math.calc.parser

import de.sambalmueslie.math.calc.api.ComplexNumber
import de.sambalmueslie.math.calc.operation.BaseOperation
import de.sambalmueslie.math.calc.operation.ValueOperation

class ParserContext(
    val listener: MutableList<ParserChangeListener> = mutableListOf()
) {

    val root = OperationGroup()

    fun add(operation: BaseOperation) {
        root.add(operation)
    }

    val openGroups: MutableList<OperationGroup> = mutableListOf()

    private val tempValues: MutableList<ComplexNumber> = mutableListOf()
    fun add(value: ComplexNumber) {
        this.tempValues.add(value)
    }

    fun getAndClearTempValues(): List<ValueOperation> {
        val result = this.tempValues.map { ValueOperation(it) }
        this.tempValues.clear()
        return result
    }

    fun finishProcessing() {
        root.finishProcessing()
    }

}
