package pythagoras.system

actual fun arrayCopy(src: ByteArray, srcPos: Int, dest: ByteArray, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}

actual fun arrayCopy(src: IntArray, srcPos: Int, dest: IntArray, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}

actual fun arrayCopy(src: FloatArray, srcPos: Int, dest: FloatArray, destPos: Int, length: Int) {
    java.lang.System.arraycopy(src, srcPos, dest, destPos, length)
}
