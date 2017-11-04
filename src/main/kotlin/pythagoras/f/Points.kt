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
 * Point-related utility methods.
 */
object Points {
    /** The point at the origin.  */
    val ZERO: IPoint = Point(0f, 0f)

    /**
     * Returns the squared Euclidean distance between the specified two points.
     */
    fun distanceSq(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        var x2 = x2
        var y2 = y2
        x2 -= x1
        y2 -= y1
        return x2 * x2 + y2 * y2
    }

    /**
     * Returns the Euclidean distance between the specified two points.
     */
    fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return MathUtil.sqrt(distanceSq(x1, y1, x2, y2))
    }

    /**
     * Returns true if the supplied points' x and y components are equal to one another within
     * `epsilon`.
     */
    fun epsilonEquals(p1: IPoint, p2: IPoint, epsilon: Float = MathUtil.EPSILON): Boolean {
        return abs(p1.x - p2.x) < epsilon && abs(p1.y - p2.y) < epsilon
    }

    /** Transforms a point as specified, storing the result in the point provided.
     * @return a reference to the result point, for chaining.
     */
    fun transform(x: Float, y: Float, sx: Float, sy: Float, rotation: Float,
                  tx: Float, ty: Float, result: Point): Point {
        return transform(x, y, sx, sy, MathUtil.sin(rotation), MathUtil.cos(rotation), tx, ty,
                result)
    }

    /** Transforms a point as specified, storing the result in the point provided.
     * @return a reference to the result point, for chaining.
     */
    fun transform(x: Float, y: Float, sx: Float, sy: Float, sina: Float, cosa: Float,
                  tx: Float, ty: Float, result: Point): Point {
        return result.set((x * cosa - y * sina) * sx + tx, (x * sina + y * cosa) * sy + ty)
    }

    /** Inverse transforms a point as specified, storing the result in the point provided.
     * @return a reference to the result point, for chaining.
     */
    fun inverseTransform(x: Float, y: Float, sx: Float, sy: Float, rotation: Float,
                         tx: Float, ty: Float, result: Point): Point {
        var x = x
        var y = y
        x -= tx
        y -= ty // untranslate
        val sinnega = MathUtil.sin(-rotation)
        val cosnega = MathUtil.cos(-rotation)
        val nx = x * cosnega - y * sinnega // unrotate
        val ny = x * sinnega + y * cosnega
        return result.set(nx / sx, ny / sy) // unscale
    }

    /**
     * Returns a string describing the supplied point, of the form `+x+y`,
     * `+x-y`, `-x-y`, etc.
     */
    fun pointToString(x: Float, y: Float): String {
        return MathUtil.toString(x) + MathUtil.toString(y)
    }
}
/**
 * Returns true if the supplied points' x and y components are equal to one another within
 * [MathUtil.EPSILON].
 */
