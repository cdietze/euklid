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
 * Represents a rectangle with rounded corners, defined by an arc width and height.
 */
class RoundRectangle : AbstractRoundRectangle {

    /** The x-coordinate of the framing rectangle.  */
    override var x: Float = 0.toFloat()

    /** The y-coordinate of the framing rectangle.  */
    override var y: Float = 0.toFloat()

    /** The width of the framing rectangle.  */
    override var width: Float = 0.toFloat()

    /** The height of the framing rectangle.  */
    override var height: Float = 0.toFloat()

    /** The width of the arc that defines the rounded corners.  */
    override var arcWidth: Float = 0.toFloat()

    /** The height of the arc that defines the rounded corners.  */
    override var arcHeight: Float = 0.toFloat()

    /**
     * Creates a rounded rectangle with frame (0x0+0+0) and corners of size (0x0).
     */
    constructor() {}

    /**
     * Creates a rounded rectangle with the specified frame and corner dimensions.
     */
    constructor(x: Float, y: Float, width: Float, height: Float,
                arcwidth: Float, archeight: Float) {
        setRoundRect(x, y, width, height, arcwidth, archeight)
    }

    /**
     * Sets the frame and corner dimensions of this rectangle to the specified values.
     */
    fun setRoundRect(x: Float, y: Float, width: Float, height: Float,
                     arcwidth: Float, archeight: Float) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
        this.arcWidth = arcwidth
        this.arcHeight = archeight
    }

    /**
     * Sets the frame and corner dimensions of this rectangle to be equal to those of the supplied
     * rectangle.
     */
    fun setRoundRect(rr: IRoundRectangle) {
        setRoundRect(rr.x, rr.y, rr.width, rr.height,
                rr.arcWidth, rr.arcHeight)
    }

    override // from RoundRectangle
    fun setFrame(x: Float, y: Float, width: Float, height: Float) {
        setRoundRect(x, y, width, height, arcWidth, arcHeight)
    }

    companion object {
        private const val serialVersionUID = 5850741513376725608L
    }
}
