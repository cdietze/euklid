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
 * A ray consisting of an origin point and a unit direction vector.
 */
class Ray3 : IRay3 {
    /** The ray's point of origin.  */
    override val origin = Vector3()

    /** The ray's unit direction vector.  */
    override val direction = Vector3()

    /**
     * Creates a ray with the values contained in the supplied origin point and unit direction
     * vector.
     */
    constructor(origin: Vector3, direction: Vector3) {
        set(origin, direction)
    }

    /**
     * Copy constructor.
     */
    constructor(other: Ray3) {
        set(other)
    }

    /**
     * Creates an empty (invalid) ray.
     */
    constructor()

    /**
     * Copies the parameters of another ray.

     * @return a reference to this ray, for chaining.
     */
    fun set(other: Ray3): Ray3 {
        return set(other.origin, other.direction)
    }

    /**
     * Sets the ray parameters to the values contained in the supplied vectors.

     * @return a reference to this ray, for chaining.
     */
    fun set(origin: Vector3, direction: Vector3): Ray3 {
        this.origin.set(origin)
        this.direction.set(direction)
        return this
    }

    // /**
    //  * Transforms this ray in-place.
    //  *
    //  * @return a reference to this ray, for chaining.
    //  */
    // public Ray3 transformLocal (Transform3D transform) {
    //     return transform(transform, this);
    // }

    // @Override // from IRay3
    // public Ray3 transform (Transform3D transform) {
    //     return transform(transform, new Ray3());
    // }

    // @Override // from IRay3
    // public Ray3 transform (Transform3D transform, Ray3 result) {
    //     transform.transformPoint(origin, result.origin);
    //     transform.transformVector(direction, result.direction).normalizeLocal();
    //     return result;
    // }

    override fun toString(): String {
        return "[origin=$origin, direction=$direction]"
    }
}
