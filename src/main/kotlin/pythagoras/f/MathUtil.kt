/*
 * Copyright 2017 The Pythagoras-kt Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package pythagoras.f

import kotlin.math.*

/**
 * Math utility methods and constants for single-precision floating point math.
 */
object MathUtil {
    /** A small number.  */
    val EPSILON = 0.00001f

    /** The circle constant, Tau (&#964;) http://tauday.com/  */
    val TAU = (kotlin.math.PI * 2).toFloat()

    /** The ratio of a circle's circumference to its diameter.  */
    val PI = kotlin.math.PI.toFloat()

    /** Twice Pi.  */
    val TWO_PI = TAU

    /** Pi times one half.  */
    val HALF_PI = (kotlin.math.PI * 0.5).toFloat()

    /** The base value of the natural logarithm.  */
    val E = kotlin.math.E.toFloat()

    /**
     * A cheaper version of [Math.round] that doesn't handle the special cases.
     */
    fun round(v: Float): Int {
        return if (v < 0f) (v - 0.5f).toInt() else (v + 0.5f).toInt()
    }

    /**
     * Returns the floor of v as an integer without calling the relatively expensive
     * [Math.floor].
     */
    fun ifloor(v: Float): Int {
        val iv = v.toInt()
        return if (v >= 0f || iv.toFloat() == v || iv == Int.MIN_VALUE) iv else iv - 1
    }

    /**
     * Returns the ceiling of v as an integer without calling the relatively expensive
     * [Math.ceil].
     */
    fun iceil(v: Float): Int {
        val iv = v.toInt()
        return if (v <= 0f || iv.toFloat() == v || iv == Int.MAX_VALUE) iv else iv + 1
    }

    /**
     * Clamps a value to the range [lower, upper].
     */
    fun clamp(v: Float, lower: Float, upper: Float): Float {
        if (v < lower)
            return lower
        else if (v > upper)
            return upper
        else
            return v
    }

    /**
     * Rounds a value to the nearest multiple of a target.
     */
    fun roundNearest(v: Float, target: Float): Float {
        var target = target
        target = abs(target)
        if (v >= 0) {
            return target * floor((v + 0.5f * target) / target)
        } else {
            return target * ceil((v - 0.5f * target) / target)
        }
    }

    /**
     * Checks whether the value supplied is in [lower, upper].
     */
    fun isWithin(v: Float, lower: Float, upper: Float): Boolean {
        return v in lower..upper
    }

    /**
     * Returns a random value according to the normal distribution with the provided mean and
     * standard deviation.

     * @param normal a normally distributed random value.
     * *
     * @param mean the desired mean.
     * *
     * @param stddev the desired standard deviation.
     */
    fun normal(normal: Float, mean: Float, stddev: Float): Float {
        return stddev * normal + mean
    }

    /**
     * Returns a random value according to the exponential distribution with the provided mean.

     * @param random a uniformly distributed random value.
     * *
     * @param mean the desired mean.
     */
    fun exponential(random: Float, mean: Float): Float {
        return -log(1f - random) * mean
    }

    /**
     * Linearly interpolates between two angles, taking the shortest path around the circle.
     * This assumes that both angles are in [-pi, +pi].
     */
    fun lerpa(a1: Float, a2: Float, t: Float): Float {
        val ma1 = mirrorAngle(a1)
        val ma2 = mirrorAngle(a2)
        val d = abs(a2 - a1)
        val md = abs(ma1 - ma2)
        return if (d <= md) lerp(a1, a2, t) else mirrorAngle(lerp(ma1, ma2, t))
    }

    /**
     * Linearly interpolates between v1 and v2 by the parameter t.
     */
    fun lerp(v1: Float, v2: Float, t: Float): Float {
        return v1 + t * (v2 - v1)
    }

    /**
     * Determines whether two values are "close enough" to equal.
     */
    fun epsilonEquals(v1: Float, v2: Float): Boolean {
        return abs(v1 - v2) < EPSILON
    }

    /**
     * Returns the (shortest) distance between two angles, assuming that both angles are in
     * [-pi, +pi].
     */
    fun angularDistance(a1: Float, a2: Float): Float {
        val ma1 = mirrorAngle(a1)
        val ma2 = mirrorAngle(a2)
        return min(abs(a1 - a2), abs(ma1 - ma2))
    }

    /**
     * Returns the (shortest) difference between two angles, assuming that both angles are in
     * [-pi, +pi].
     */
    fun angularDifference(a1: Float, a2: Float): Float {
        val ma1 = mirrorAngle(a1)
        val ma2 = mirrorAngle(a2)
        val diff = a1 - a2
        val mdiff = ma2 - ma1
        return if (abs(diff) < abs(mdiff)) diff else mdiff
    }

    /**
     * Returns an angle in the range [-pi, pi).
     */
    fun normalizeAngle(a: Float): Float {
        var a = a
        while (a < -PI) {
            a += TWO_PI
        }
        while (a >= PI) {
            a -= TWO_PI
        }
        return a
    }

    /**
     * Returns an angle in the range [0, 2pi).
     */
    fun normalizeAnglePositive(a: Float): Float {
        var a = a
        while (a < 0f) {
            a += TWO_PI
        }
        while (a >= TWO_PI) {
            a -= TWO_PI
        }
        return a
    }

    /**
     * Returns the mirror angle of the specified angle (assumed to be in [-pi, +pi]). The angle is
     * mirrored around the PI/2 if it is positive, and -PI/2 if it is negative. One can visualize
     * this as mirroring around the "y-axis".
     */
    fun mirrorAngle(a: Float): Float {
        return (if (a > 0f) PI else -PI) - a
    }

    /**
     * Sets the number of decimal places to show when formatting values. By default, they are
     * formatted to three decimal places.
     */
    fun setToStringDecimalPlaces(places: Int) {
        if (places < 0) throw IllegalArgumentException("Decimal places must be >= 0.")
        TO_STRING_DECIMAL_PLACES = places
    }

    /**
     * Formats the supplied floating point value, truncated to the given number of decimal places.
     * The value is also always preceded by a sign (e.g. +1.0 or -0.5).
     */
    fun toString(value: Float, decimalPlaces: Int = TO_STRING_DECIMAL_PLACES): String {
        var value = value
        if (value.isNaN()) return "NaN"

        val buf = StringBuilder()
        if (value >= 0)
            buf.append("+")
        else {
            buf.append("-")
            value = -value
        }
        var ivalue = value.toInt()
        buf.append(ivalue)
        if (decimalPlaces > 0) {
            buf.append(".")
            for (ii in 0..decimalPlaces - 1) {
                value = (value - ivalue) * 10
                ivalue = value.toInt()
                buf.append(ivalue)
            }

            // trim trailing zeros
            var endIndex = buf.length
            for (ii in 0 until decimalPlaces - 1) {
                if (buf[buf.length - 1 - ii] == '0') {
                    endIndex--
                }
            }
            return buf.substring(0, endIndex)
        }
        return buf.toString()
    }

    /**
     * Computes and returns the sine of the given angle.

     * @see Math.sin
     * TODO: inline
     */
    fun sin(a: Float): Float {
        return sin(a.toDouble()).toFloat()
    }

    /**
     * Computes and returns the cosine of the given angle.

     * @see Math.cos
     * TODO: inline
     */
    fun cos(a: Float): Float {
        return cos(a.toDouble()).toFloat()
    }

    /**
     * Computes and returns the tangent of the given angle.

     * @see Math.tan
     * TODO: inline
     */
    fun tan(a: Float): Float {
        return tan(a.toDouble()).toFloat()
    }

    /**
     * Computes and returns the arc sine of the given value.

     * @see Math.asin
     * TODO: inline
     */
    fun asin(a: Float): Float {
        return asin(a.toDouble()).toFloat()
    }

    /**
     * Computes and returns the arc cosine of the given value.

     * @see Math.acos
     * TODO: inline
     */
    fun acos(a: Float): Float {
        return acos(a.toDouble()).toFloat()
    }

    /**
     * Computes and returns the arc tangent of the given value.

     * @see Math.atan
     * TODO: inline
     */
    fun atan(a: Float): Float {
        return atan(a.toDouble()).toFloat()
    }

    /**
     * Computes and returns the arc tangent of the given values.

     * @see Math.atan2
     * TODO: inline
     */
    fun atan2(y: Float, x: Float): Float {
        return atan2(y.toDouble(), x.toDouble()).toFloat()
    }

    /**
     * Converts from radians to degrees.

     * @see Math.toDegrees
     */
    fun toDegrees(a: Float): Float {
        return a * (180f / PI)
    }

    /**
     * Converts from degrees to radians.

     * @see Math.toRadians
     */
    fun toRadians(a: Float): Float {
        return a * (PI / 180f)
    }

    /**
     * Returns the square root of the supplied value.

     * @see Math.sqrt
     * TODO: inline
     */
    fun sqrt(v: Float): Float {
        return sqrt(v.toDouble()).toFloat()
    }

    /**
     * Computes and returns sqrt(x*x + y*y).

     * @see Math.hypot
     * TODO: inline
     */
    fun hypot(x: Float, y: Float): Float {
        return hypot(x.toDouble(), y.toDouble()).toFloat()
    }

    /**
     * Returns e to the power of the supplied value.

     * @see Math.exp
     * TODO: inline
     */
    fun exp(v: Float): Float {
        return exp(v.toDouble()).toFloat()
    }

    /**
     * Returns the natural logarithm of the supplied value.
     * @see Math.log
     * TODO: inline
     */
    fun log(v: Float): Float {
        return ln(v)
    }

    /**
     * Returns the base 10 logarithm of the supplied value.

     * @see Math.log10
     */
    fun log10(v: Float): Float {
        return log10(v.toDouble()).toFloat()
    }

    /**
     * Returns v to the power of e.

     * @see Math.pow
     * TODO: inline
     */
    fun pow(v: Float, e: Float): Float {
        return v.pow(e)
    }

    /**
     * Returns the floor of v.
     * @see Math.floor
     * TODO: inline
     */
    fun floor(v: Float): Float {
        return floor(v.toDouble()).toFloat()
    }

    /**
     * Returns the ceiling of v.

     * @see Math.ceil
     * TODO: inline
     */
    fun ceil(v: Float): Float {
        return ceil(v.toDouble()).toFloat()
    }

    /**
     * Returns the cube root of the supplied value.
     */
    fun cbrt(a: Float): Float = if (a == 0.0f || !a.isFinite()) a else a.pow(1.0f / 3.0f)

    private var TO_STRING_DECIMAL_PLACES = 3
}