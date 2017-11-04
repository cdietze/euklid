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
 * Represents a circle on a plane.
 */
class Circle : AbstractCircle {

    /** The x-coordinate of the circle.  */
    override var x: Float = 0f

    /** The y-coordinate of the circle.  */
    override var y: Float = 0f

    /** The radius of the circle.  */
    override var radius: Float = 0f

    /**
     * Constructs a circle at (0, 0) with radius 0
     */
    constructor()

    /**
     * Constructs a circle with the specified properties
     */
    constructor(x: Float, y: Float, radius: Float) {
        set(x, y, radius)
    }

    /**
     * Constructs a circle with the specified properties
     */
    constructor(p: XY, radius: Float) : this(p.x, p.y, radius)

    /**
     * Constructs a circle with properties equal to the supplied circle.
     */
    constructor(c: ICircle) : this(c.x, c.y, c.radius)

    /** Sets the properties of this circle to be equal to those of the supplied circle.
     * @return a reference to this this, for chaining.
     */
    fun set(c: ICircle): Circle {
        return set(c.x, c.y, c.radius)
    }

    /** Sets the properties of this circle to the supplied values.
     * @return a reference to this this, for chaining.
     */
    fun set(x: Float, y: Float, radius: Float): Circle {
        this.x = x
        this.y = y
        this.radius = radius
        return this
    }

    companion object {
        private const val serialVersionUID = -4841212861047390886L
    }
}
