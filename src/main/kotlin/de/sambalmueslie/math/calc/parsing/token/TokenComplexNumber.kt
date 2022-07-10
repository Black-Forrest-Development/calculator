package de.sambalmueslie.math.calc.parsing.token

import de.sambalmueslie.math.calc.api.ComplexNumber

data class TokenComplexNumber(
    val number: ComplexNumber
) : Token
