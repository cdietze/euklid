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
 * An exception thrown if an operation is performed on a [Path] that is in an illegal state
 * with respect to the particular operation being performed. For example, appending a segment to a
 * path without an initial moveto.
 */
class IllegalPathStateException : RuntimeException {

    constructor() {}

    constructor(s: String) : super(s) {}

    companion object {
        private val serialVersionUID = 5494939619370624441L
    }
}
