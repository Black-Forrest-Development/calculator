package de.sambalmueslie.math.calc.operation


import de.sambalmueslie.math.calc.api.ComplexNumber
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GroupOperation : BaseOperation() {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(GroupOperation::class.java)
    }

    override fun solve(): ComplexNumber {
        var real = 0.0
        var imaginary = 0.0

        operands.map { it.solve() }.forEachIndexed { index, value ->
            if (index == 0) {
                real = value.real
                imaginary = value.imaginary
            } else {
                real += value.real
                imaginary += value.imaginary
            }
        }

        val result = ComplexNumber(real, imaginary)
        logger.debug("Group operation results $result")
        return result
    }

}
