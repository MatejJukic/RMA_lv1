

object DiceThrowing {
    val dice = listOf(Die(), Die(), Die(), Die(), Die(), Die())

    fun throwDice() = dice.forEach { Die -> if (!Die.locked) Die.throwDie()}

    fun lockDiceByValue (diceToLock: MutableList<String>) {
        dice.forEach {
            if (diceToLock.contains(it.value.toString())&&!it.locked){
                it.locked=true
                diceToLock.remove(it.value.toString())
             }
        }
    }

    /*fun lockDiceByIndex (diceToLock: MutableList<String>) {
        dice.forEach {
            if (diceToLock.contains((dice.indexOf(it)+1).toString())&&!it.locked){
                it.locked=true
                diceToLock.remove(it.value.toString())
            }
        }
    }*/


    fun unlockDiceByValue (diceToUnlock: MutableList<String>) {
        for( Die in dice ) {
            if (diceToUnlock.contains(Die.value.toString())&&Die.locked){
                Die.locked=false
                diceToUnlock.remove(Die.value.toString())
            }
        }
    }

    /*fun unlockDiceByIndex (diceToUnlock: MutableList<String>) {
        dice.forEach {
            if (diceToUnlock.contains((dice.indexOf(it)+1).toString())&&it.locked){
                it.locked=false
                diceToUnlock.remove(it.value.toString())
            }
        }
    }*/

    fun reloadDice() {
        dice.forEach{
            it.locked=false
        }
    }

    fun getDiceValues(): List<String> {
        val outList = mutableListOf<String>()
        dice.forEach { Die -> outList.add(Die.value.toString()) }
        return outList
    }

}

