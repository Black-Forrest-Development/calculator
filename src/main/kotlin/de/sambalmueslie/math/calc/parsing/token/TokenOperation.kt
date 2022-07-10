package de.sambalmueslie.math.calc.parsing.token

data class TokenOperation(
    val type: OPERATION_TYPE
) : Token

enum class OPERATION_TYPE {
    ADDITION,
    SUBTRACTION
}
