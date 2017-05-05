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

package java.lang

/**
 * Implementation needed for JS backend.
 */
object System {
    /** Not quite the original signature which uses `java.lang.Object`, but we really just want to copy arrays */
    fun arraycopy(src: FloatArray, srcPos: Int, dest: FloatArray, destPos: Int, length: Int): Unit {
        TODO("NYI")
    }

    fun arraycopy(src: IntArray, srcPos: Int, dest: IntArray, destPos: Int, length: Int): Unit {
        TODO("NYI")
    }

    fun arraycopy(src: ByteArray, srcPos: Int, dest: ByteArray, destPos: Int, length: Int): Unit {
        TODO("NYI")
    }
}
