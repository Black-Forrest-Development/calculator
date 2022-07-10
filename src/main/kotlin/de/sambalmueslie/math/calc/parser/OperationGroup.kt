package de.sambalmueslie.math.calc.parser

import de.sambalmueslie.math.calc.operation.BaseOperation
import de.sambalmueslie.math.calc.operation.GroupOperation

class OperationGroup {

    val operations: List<BaseOperation>
        get() = _operations

    private val _operations: MutableList<BaseOperation> = mutableListOf()
    fun add(operation: BaseOperation) {

        _operations.add(operation)
    }

    fun getOperation(): GroupOperation {
        val result = GroupOperation()
        _operations.forEach { result.append(it) }
        return result
    }

    fun finishProcessing() {
//        val operation = root.operands.lastOrNull() ?: throw IllegalStateException("Cannot parse equation")
//        val values = getAndClearTempValues()
//        values.forEach { (operation as BaseOperation).append(it) }
    }
}
