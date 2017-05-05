/*
 * Copyright 2017 The Pythagoras.kt Authors
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

import java.lang.Math

/**
 * Utility methods and constants for single-precision floating point math. Extends [MathUtil]
 * with shim methods that call through to [Math] and convert the results to float.
 */
class FloatMath : MathUtil() {
    companion object {
        /** The ratio of a circle's circumference to its diameter.  */
        val PI = Math.PI.toFloat()

        /** The base value of the natural logarithm.  */
        val E = Math.E.toFloat()

        /**
         * Computes and returns the sine of the given angle.

         * @see Math.sin
         */
        fun sin(a: Float): Float {
            return Math.sin(a.toDouble()).toFloat()
        }

        /**
         * Computes and returns the cosine of the given angle.

         * @see Math.cos
         */
        fun cos(a: Float): Float {
            return Math.cos(a.toDouble()).toFloat()
        }

        /**
         * Computes and returns the tangent of the given angle.

         * @see Math.tan
         */
        fun tan(a: Float): Float {
            return Math.tan(a.toDouble()).toFloat()
        }

        /**
         * Computes and returns the arc sine of the given value.

         * @see Math.asin
         */
        fun asin(a: Float): Float {
            return Math.asin(a.toDouble()).toFloat()
        }

        /**
         * Computes and returns the arc cosine of the given value.

         * @see Math.acos
         */
        fun acos(a: Float): Float {
            return Math.acos(a.toDouble()).toFloat()
        }

        /**
         * Computes and returns the arc tangent of the given value.

         * @see Math.atan
         */
        fun atan(a: Float): Float {
            return Math.atan(a.toDouble()).toFloat()
        }

        /**
         * Computes and returns the arc tangent of the given values.

         * @see Math.atan2
         */
        fun atan2(y: Float, x: Float): Float {
            return Math.atan2(y.toDouble(), x.toDouble()).toFloat()
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
         */
        fun sqrt(v: Float): Float {
            return Math.sqrt(v.toDouble()).toFloat()
        }

        /**
         * Returns the cube root of the supplied value.

         * @see Math.cbrt
         */
        fun cbrt(v: Float): Float {
            return Math.cbrt(v.toDouble()).toFloat()
        }

        /**
         * Computes and returns sqrt(x*x + y*y).

         * @see Math.hypot
         */
        fun hypot(x: Float, y: Float): Float {
            return Math.hypot(x.toDouble(), y.toDouble()).toFloat()
        }

        /**
         * Returns e to the power of the supplied value.

         * @see Math.exp
         */
        fun exp(v: Float): Float {
            return Math.exp(v.toDouble()).toFloat()
        }

        /**
         * Returns the natural logarithm of the supplied value.

         * @see Math.log
         */
        fun log(v: Float): Float {
            return Math.log(v.toDouble()).toFloat()
        }

        /**
         * Returns the base 10 logarithm of the supplied value.

         * @see Math.log10
         */
        fun log10(v: Float): Float {
            return Math.log10(v.toDouble()).toFloat()
        }

        /**
         * Returns v to the power of e.

         * @see Math.pow
         */
        fun pow(v: Float, e: Float): Float {
            return Math.pow(v.toDouble(), e.toDouble()).toFloat()
        }

        /**
         * Returns the floor of v.

         * @see Math.floor
         */
        fun floor(v: Float): Float {
            return Math.floor(v.toDouble()).toFloat()
        }

        /**
         * Returns the ceiling of v.

         * @see Math.ceil
         */
        fun ceil(v: Float): Float {
            return Math.ceil(v.toDouble()).toFloat()
        }
    }
}
