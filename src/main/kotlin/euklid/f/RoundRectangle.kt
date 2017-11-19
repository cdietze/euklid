/*
 * Copyright 2017 The Euklid Authors
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



package euklid.f

/**
 * Represents a rectangle with rounded corners, defined by an arc width and height.
 */
data class RoundRectangle(
        /** The x-coordinate of the framing rectangle.  */
        override var x: Float = 0f,
        /** The y-coordinate of the framing rectangle.  */
        override var y: Float = 0f,
        /** The width of the framing rectangle.  */
        override var width: Float = 0f,
        /** The height of the framing rectangle.  */
        override var height: Float = 0f,
        /** The width of the arc that defines the rounded corners.  */
        override var arcWidth: Float = 0f,
        /** The height of the arc that defines the rounded corners.  */
        override var arcHeight: Float = 0f
) : AbstractRectangularShape(), IRoundRectangle {

    /**
     * Sets the frame and corner dimensions of this rectangle to the specified values.
     * @return a reference to this this, for chaining.
     */
    fun setRoundRect(x: Float, y: Float, width: Float, height: Float,
                     arcwidth: Float, archeight: Float): RoundRectangle {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
        this.arcWidth = arcwidth
        this.arcHeight = archeight
        return this
    }

    /**
     * Sets the frame and corner dimensions of this rectangle to be equal to those of the supplied
     * rectangle.
     */
    fun setRoundRect(rr: IRoundRectangle) {
        setRoundRect(rr.x, rr.y, rr.width, rr.height,
                rr.arcWidth, rr.arcHeight)
    }

    override fun setFrame(x: Float, y: Float, width: Float, height: Float) = setRoundRect(x, y, width, height, arcWidth, arcHeight)
}
