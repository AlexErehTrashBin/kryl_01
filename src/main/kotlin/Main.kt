package ru.alexereh

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import kotlin.math.abs
import kotlin.math.sin

fun findRoots(
    function: (x: Double) -> Double,
    precision: Double = 1E-4,
    lowerBound: Double,
    higherBound: Double,
): List<Double> {
    val list: MutableList<Double> = mutableListOf()
    var i = lowerBound
    val step = precision * 100
    while (i + step <= higherBound) {
        val root = findRootOnInterval(
            function = function,
            lowerBound = i,
            higherBound = i + step
        )
        if (root.isNone()) {
            i += step
            continue
        }
        list += root.getOrNull()!!
        i += step
    }
    return list
}

fun findRootOnInterval(
    function: (x: Double) -> Double,
    lowerBound: Double,
    higherBound: Double,
    epsilon: Double = 0.0001
): Option<Double> {
    var x: Double
    var a = lowerBound
    var b = higherBound
    var root: Double = Double.NaN
    var fa = function(lowerBound)
    var fb = function(higherBound)

    if (fa * fb >= epsilon) {
        return none()
    }
    while (abs(b - a) >= epsilon) {
        x = (a + b) / 2
        val fx = function(x)

        if (fx <= epsilon) {
            root = x
            break
        } else if (fa * fb < 0) {
            a = x
            fa = fx
        } else {
            b = x
            fb = fx
        }
    }
    if (root.isNaN()) {
        return none()
    }
    return root.some()
}

fun main() {
    /*val roots = findRoots(
        function = { x: Double ->
            return@findRoots x*x*x*x - 4.3 * x*x*x - 1.29 * x*x + 15.1 * sin(x) - 9.84
        },
        lowerBound = 0.0,
        higherBound = 4.0
    )*/
    val roots = findRoots(
        function = { x: Double ->
            return@findRoots (x-2)*(x+2)
        },
        lowerBound = -4.0,
        higherBound = 4.0
    )
    println(roots)
}