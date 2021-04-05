data class YahtzeeTable (val playerNumber: Int, val numberOfFields: Int) {
    private val playerResults = HashMap<String, HashMap<String, Int>>()

    init {

        // Consider implementing a builder but not today
        // All matchers should be uppercase

        val playerData = HashMap<String, Int>()
        playerData["PlayerNumber"] = playerNumber
        playerData["Total"] = -1

        playerResults["PlayerInfo"] = playerData
        playerResults["Downward"] = generateMap("Downward", numberOfFields)
        playerResults["Upward"] = generateMap("Upward", numberOfFields)
        playerResults["Free"] = generateMap("Free", numberOfFields)
        playerResults["Ann"] = generateMap("Ann", numberOfFields)
    }

    private fun generateMap(columnName: String, numberOfFields: Int): HashMap<String, Int> {
        val map = HashMap<String, Int>(numberOfFields)
        map["Scale"] = -1
        map["Poker"] = -1
        map["Yahtzee"] = -1
        map["Total"] = -1
        when (columnName) {
            "Downward" -> {
                map["Ones"] = -2
            }
            "Upward" -> {
                map["Yahtzee"] = -2
            }
            "Free" -> {
                for (item in map) {
                    map[item.key] = -2
                    map["Total"] = -1
                }
            }
        }
        return map
    }

    fun listAvailableOptions() {
        println("Select field to fill")
        println("Available options are: ")

        playerResults.forEach {
            if (it.key != "PlayerInfo") {
                println("For ${it.key}")
                it.value.forEach { (key, value) ->
                    if (value < 0 && value != -1) println(key)
                }
                println()
            }
        }
    }

    fun getScore(): Int = playerResults["PlayerInfo"]?.get("Total") ?: -1

    fun updateScore(keys: MutableList<String>, diceValues: List<String>): Boolean {
        var counter = 0
        if (keys.isEmpty()) return false
        val map: HashMap<String, Int> = playerResults[keys[0]] ?: return false
        when (keys[0]) {
            "Downward" -> {
                when (keys[1]) {
                    "Ones" -> {
                        diceValues.forEach { if (it == "1") counter++ }
                        map["Ones"] = counter
                        map["Twos"] = -2
                        return true
                    }
                    "Twos" -> {
                        diceValues.forEach { if (it == "2") counter++ }
                        map["Twos"] = counter * 2
                        map["Threes"] = -2
                        return true
                    }

                    "Scale" -> {
                        return when (diceValues.distinct().size) {
                            5 -> {
                                map["Scale"] = 35
                                true
                            }
                            6 -> {
                                map["Scale"] = 45
                                true
                            }
                            else -> {
                                map["Scale"] = 0; true
                            }
                        }
                    }


                }
            }
            "Upward" -> {
                return true
            }
            "Free" -> {
                return true
            }
            "Ann" -> {
                return true
            }
        }
        return false
    }
}