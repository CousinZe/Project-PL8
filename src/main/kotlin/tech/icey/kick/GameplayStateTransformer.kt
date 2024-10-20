package tech.icey.kick

import tech.icey.pl8.GameplayState

fun interface GameplayStateTransformer {
    fun apply(state: GameplayState?)
}
