package pythagoras.system

expect fun arrayCopy(src: ByteArray, srcPos: Int, dest: ByteArray, destPos: Int, length: Int)

expect fun arrayCopy(src: IntArray, srcPos: Int, dest: IntArray, destPos: Int, length: Int)

expect fun arrayCopy(src: FloatArray, srcPos: Int, dest: FloatArray, destPos: Int, length: Int)

expect fun <T>arrayCopy(src: Array<T>, srcPos: Int, dest: Array<T>, destPos: Int, length: Int)

interface Random {
    fun nextFloat(): Float
}
