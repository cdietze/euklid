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

/**
 * An interface implemented by [IShape] classes whose geometry is defined by a rectangular
 * frame. The framing rectangle *defines* the geometry, but may in some cases differ from
 * the *bounding* rectangle of the shape.
 */
interface IRectangularShape : IShape {
    /** Returns the x-coordinate of the upper-left corner of the framing rectangle.  */
    val x: Float

    /** Returns the y-coordinate of the upper-left corner of the framing rectangle.  */
    val y: Float

    /** Returns the width of the framing rectangle.  */
    val width: Float

    /** Returns the height of the framing rectangle.  */
    val height: Float

    /** Returns the minimum x,y-coordinate of the framing rectangle.  */
    val min: Point

    /** Returns the minimum x-coordinate of the framing rectangle.  */
    val minX: Float

    /** Returns the minimum y-coordinate of the framing rectangle.  */
    val minY: Float

    /** Returns the maximum x,y-coordinate of the framing rectangle.  */
    val max: Point

    /** Returns the maximum x-coordinate of the framing rectangle.  */
    val maxX: Float

    /** Returns the maximum y-coordinate of the framing rectangle.  */
    val maxY: Float

    /** Returns the center of the framing rectangle.  */
    val center: Point

    /** Returns the x-coordinate of the center of the framing rectangle.  */
    val centerX: Float

    /** Returns the y-coordinate of the center of the framing rectangle.  */
    val centerY: Float

    /** Returns a copy of this shape's framing rectangle.  */
    fun frame(): Rectangle

    /** Initializes the supplied rectangle with this shape's framing rectangle.
     * @return the supplied rectangle.
     */
    fun frame(target: Rectangle): Rectangle
}
