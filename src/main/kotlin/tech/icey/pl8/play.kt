package tech.icey.pl8

data class GameplayState(
    var currentLabel: Label? = null,
    var currentStmt: Int = 0,
    val variables: MutableMap<String, Any> = mutableMapOf()
)

fun play(startingLabel: String) {
    val state = GameplayState()
    val label = program[startingLabel] ?: throw IllegalStateException("label '$startingLabel' not found")
    state.currentLabel = label

    while (state.currentLabel != null) {
        val stmt = state.currentLabel!!.statements[state.currentStmt]
        stmt.execute(state)
        state.currentStmt++
        if (state.currentStmt >= state.currentLabel!!.statements.size) {
            state.currentLabel = null
        }
    }
}
