object Yahtzee {
    private var loopCounter = 0
    private const val numberOfFields = 3
    private const val numberOfColumns = 4

    private val players = mutableListOf<Player>()

    fun runGame(numberOfPlayers: Int) {

        generatePlayers(numberOfPlayers)

        while (loopCounter <= numberOfFields * numberOfPlayers * numberOfColumns) {
            for (player in players) {
                player.takeTurn()
            }
            loopCounter++
        }

        declareWinner()
    }

    private fun generatePlayers(numberOfPlayers: Int) {
        for (playerNumber in 1..numberOfPlayers) {
            players.add(Player(playerNumber, numberOfFields))
        }
    }

    private fun declareWinner() {
        var maxHighScore = 0
        var currentPlayerScore: Int
        var winnerIndex = 0
        for (player: Player in players) {
            currentPlayerScore = player.getScore()
            if (maxHighScore < currentPlayerScore) {
                maxHighScore = currentPlayerScore
                winnerIndex = players.indexOf(player)
            }
        }
        println("The winner is: " + players[winnerIndex].toString())
    }
}