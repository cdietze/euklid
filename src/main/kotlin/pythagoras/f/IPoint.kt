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

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/**
 * Provides read-only access to a [Point].
 */
interface IPoint : XY {
    /** Returns the squared Euclidian distance between this point and the specified point.  */
    fun distanceSq(px: Float, py: Float): Float = Points.distanceSq(x, y, px, py)

    /** Returns the squared Euclidian distance between this point and the supplied point.  */
    fun distanceSq(p: XY): Float = Points.distanceSq(x, y, p.x, p.y)

    /** Returns the Euclidian distance between this point and the specified point.  */
    fun distance(px: Float, py: Float): Float = Points.distance(x, y, px, py)

    /** Returns the Euclidian distance between this point and the supplied point.  */
    fun distance(p: XY): Float = Points.distance(x, y, p.x, p.y)

    /** Returns the angle (in radians) of the vector starting at this point and ending at the
     * supplied other point.  */
    fun direction(other: XY): Float = atan2(other.y - y, other.x - x)

    /** Multiplies this point by a scale factor.
     * @return a new point containing the result.
     */
    fun mult(s: Float): Point = mult(s, Point())

    /** Multiplies this point by a scale factor and places the result in the supplied object.
     * @return a reference to the result, for chaining.
     */
    fun mult(s: Float, result: Point): Point = result.set(x * s, y * s)

    /** Translates this point by the specified offset.
     * @return a new point containing the result.
     */
    fun add(x: Float, y: Float): Point = Point(this.x + x, this.y + y)

    /** Translates this point by the specified offset and stores the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(x: Float, y: Float, result: Point): Point = result.set(this.x + x, this.y + y)

    /** Translates this point by the specified offset and stores the result in the object provided.
     * @return a reference to the result, for chaining.
     */
    fun add(other: XY, result: Point): Point = add(other.x, other.y, result)

    /** Subtracts the supplied point from `this`.
     * @return a new point containing the result.
     */
    fun subtract(x: Float, y: Float): Point = subtract(x, y, Point())

    /** Subtracts the supplied point from `this` and stores the result in `result`.
     * @return a reference to the result, for chaining.
     */
    fun subtract(x: Float, y: Float, result: Point): Point = result.set(this.x - x, this.y - y)

    /** Subtracts the supplied point from `this` and stores the result in `result`.
     * @return a reference to the result, for chaining.
     */
    fun subtract(other: XY, result: Point): Point = subtract(other.x, other.y, result)

    /** Rotates this point around the origin by the specified angle.
     * @return a new point containing the result.
     */
    fun rotate(angle: Float): Point = rotate(angle, Point())

    /** Rotates this point around the origin by the specified angle, storing the result in the
     * point provided.
     * @return a reference to the result point, for chaining.
     */
    fun rotate(angle: Float, result: Point): Point {
        val x = x
        val y = y
        val sina = sin(angle)
        val cosa = cos(angle)
        return result.set(x * cosa - y * sina, x * sina + y * cosa)
    }

    /** Returns a mutable copy of this point.  */
    fun copy(x: Float = this.x, y: Float = this.y): Point
}
