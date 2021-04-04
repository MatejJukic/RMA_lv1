object InputConverter {

    private val convertedInput: MutableList<String> = mutableListOf()

    fun convertInput(playerInput: String): MutableList<String> {
        convertedInput.clear()
        convertedInput.addAll(playerInput.split(" ").filter { it != "" })
        return convertedInput
    }

}