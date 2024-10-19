package tech.icey.pl8

data class DynamicCharacter(
    val name: String,
    val image: String,
    val whatPrefix: String,
    val whatSuffix: String,
    // I don't understand what's this but let's just copy the design of Ren'Py for now
    val ctc: String,
    val ctcPosition: String
)
