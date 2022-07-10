package de.sambalmueslie.math.calc.operation


import de.sambalmueslie.math.calc.api.ComplexNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ValueOperation(private val value: ComplexNumber) : Operation {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ValueOperation::class.java)
    }

    override fun solve(): ComplexNumber {
        return value
    }
}
