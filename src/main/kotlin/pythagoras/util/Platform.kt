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



package pythagoras.util

/**
 * Handles differences between the JDK and GWT platforms.
 */
object Platform {
    /**
     * Returns a hash code for the supplied float value.
     */
    fun hashCode(f1: Float): Int {
        return f1.hashCode()
    }

    /**
     * Returns a hash code for the supplied double value.
     */
    fun hashCode(d1: Double): Int {
        return d1.hashCode()
    }

    /**
     * Clones the supplied array of bytes.
     */
    fun clone(values: ByteArray): ByteArray {
        return values.copyOf()
    }

    /**
     * Clones the supplied array of ints.
     */
    fun clone(values: IntArray): IntArray {
        return values.copyOf()
    }

    /**
     * Clones the supplied array of floats.
     */
    fun clone(values: FloatArray): FloatArray {
        return values.copyOf()
    }

    /**
     * Clones the supplied array of doubles.
     */
    fun clone(values: DoubleArray): DoubleArray {
        return values.copyOf()
    }
}
