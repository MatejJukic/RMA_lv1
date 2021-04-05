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

        var counter1= 0
        var counter2= 0
        var counter3= 0
        var counter4= 0
        var counter5= 0
        var counter6= 0

        if (keys.isEmpty()) return false
        val map: HashMap<String, Int> = playerResults[keys[0]] ?: return false
        when (keys[0]) {
            "Downward" -> {
                when (keys[1]) {
                    "Poker" -> {
                        diceValues.forEach {
                            if (it=="1") counter1++
                            if (it=="2") counter2++
                            if (it=="3") counter3++
                            if (it=="4") counter4++
                            if (it=="5") counter5++
                            if (it=="6") counter6++
                        }
                        if (counter1>3) map["Poker"] = 44
                        if (counter2>3) map["Poker"] = 48
                        if (counter3>3) map["Poker"] = 52
                        if (counter4>3) map["Poker"] = 56
                        if (counter5>3) map["Poker"] = 60
                        if (counter6>3) map["Poker"] = 64

                        counter1= 0
                        counter2= 0
                        counter3= 0
                        counter4= 0
                        counter5= 0
                        counter6= 0

                    }

                    "Yahtzee" -> {
                        diceValues.forEach {
                            if (it=="1") counter1++
                            if (it=="2") counter2++
                            if (it=="3") counter3++
                            if (it=="4") counter4++
                            if (it=="5") counter5++
                            if (it=="6") counter6++
                        }
                        if (counter1>4) map["Poker"] = 55
                        if (counter2>4) map["Poker"] = 60
                        if (counter3>4) map["Poker"] = 65
                        if (counter4>4) map["Poker"] = 70
                        if (counter5>4) map["Poker"] = 75
                        if (counter6>4) map["Poker"] = 80

                        counter1= 0
                        counter2= 0
                        counter3= 0
                        counter4= 0
                        counter5= 0
                        counter6= 0


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