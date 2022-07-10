package de.sambalmueslie.math.calc.api

data class ComplexNumber(
    val real: Double,
    val imaginary: Double
) {
    fun format() = "($real, $imaginary)"
}
