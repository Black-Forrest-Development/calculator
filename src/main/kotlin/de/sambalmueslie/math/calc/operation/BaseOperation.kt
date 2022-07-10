package de.sambalmueslie.math.calc.operation


abstract class BaseOperation : Operation {

    val operands: List<Operation>
        get() = _operands

    private val _operands: MutableList<Operation> = mutableListOf()

    fun append(value: Operation) {
        _operands.add(value)
    }
}
