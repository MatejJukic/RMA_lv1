
import kotlin.random.Random

class Die {
    var value : Int = 1
    var locked: Boolean = false

    fun throwDie(): Int = Random.nextInt(1, 7)
    //fun lockDie(): Boolean = true
    //fun unlockDie(): Boolean = false

    }
