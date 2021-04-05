
class Player (playerNumber: Int, numberOfFields: Int){
   // var name: String = "Player" + playerNumber.toString()
    var numberOfThrows: Int = 0
    var playerInput: String = ""
    val table = YahtzeeTable(playerNumber, numberOfFields)

    private fun rollDice(){
            if (numberOfThrows==0){
                DiceThrowing.throwDice()
                numberOfThrows++
                DiceThrowing.getDiceValues()
                DiceThrowing.printDiceValues()
            }
            else {

            println("Select the dice you want to lock")
            playerInput = readLine() ?: ""
            DiceThrowing.lockDiceByValue(InputConverter.convertInput(playerInput))

            println("Select the dice you want to unlock")
            playerInput = readLine() ?: ""
            DiceThrowing.unlockDiceByValue(InputConverter.convertInput(playerInput))

            DiceThrowing.throwDice()
            DiceThrowing.printDiceValues()
            DiceThrowing.getDiceValues()
            numberOfThrows++
            }

    }

    private fun updateScore() {
        var flag = true
        while (flag) {
            table.listAvailableOptions()
            playerInput = readLine() ?: ""
            val keys = InputConverter.convertInput(playerInput)
            if (table.updateScore(keys, DiceThrowing.getDiceValues()))
                flag = false
        }
    }

    fun takeTurn(){
        DiceThrowing.reloadDice()
        println("Player ${table.playerNumber} turn!")
        numberOfThrows = 0
        while (numberOfThrows < 3) {
            rollDice()
        }
        updateScore()
    }

    override fun toString(): String {
        return "${table.playerNumber}" +
                "Score: " +
                "${table.getScore()}"
    }

    fun getScore() = table.getScore()

}