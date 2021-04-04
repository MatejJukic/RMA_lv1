

object DiceThrowing {
    val dice = listOf(Die(), Die(), Die(), Die(), Die(), Die())

    fun throwDice() = dice.forEach { Die -> if (!Die.locked) Die.throwDie()}
    fun lockDice (diceToLock: String) {
        dice.forEach {
            if (diceToLock)
        }
    }
}

