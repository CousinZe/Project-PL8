package tech.icey.pl8

fun interface GameplayStateTransformer {
    fun apply(state: GameplayState)
}
