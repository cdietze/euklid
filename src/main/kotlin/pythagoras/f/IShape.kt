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

/**
 * An interface provided by all shapes.
 */
interface IShape {
    /** Returns true if this shape encloses no area.  */
    val isEmpty: Boolean

    /** Returns true if this shape contains the specified point.  */
    fun contains(x: Float, y: Float): Boolean

    /** Returns true if this shape contains the supplied point.  */
    operator fun contains(point: XY): Boolean

    /** Returns true if this shape completely contains the specified rectangle.  */
    fun contains(x: Float, y: Float, width: Float, height: Float): Boolean

    /** Returns true if this shape completely contains the supplied rectangle.  */
    operator fun contains(rect: IRectangle): Boolean

    /** Returns true if this shape intersects the specified rectangle.  */
    fun intersects(x: Float, y: Float, width: Float, height: Float): Boolean

    /** Returns true if this shape intersects the supplied rectangle.  */
    fun intersects(rect: IRectangle): Boolean

    /** Returns a copy of the bounding rectangle for this shape.  */
    fun bounds(): Rectangle

    /** Initializes the supplied rectangle with this shape's bounding rectangle.
     * @return the supplied rectangle.
     */
    fun bounds(target: Rectangle): Rectangle

    /**
     * Returns an iterator over the path described by this shape.

     * @param transform if supplied, the points in the path are transformed using this.
     */
    fun pathIterator(transform: Transform?): PathIterator

    /**
     * Returns an iterator over the path described by this shape.

     * @param transform if supplied, the points in the path are transformed using this.
     * *
     * @param flatness when approximating curved segments with lines, this controls the maximum
     * *        distance the lines are allowed to deviate from the approximated curve, thus a higher
     * *        flatness value generally allows for a path with fewer segments.
     */
    fun pathIterator(transform: Transform?, flatness: Float): PathIterator
}
