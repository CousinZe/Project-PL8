package tech.icey.pl8

interface Statement

data class Say(
    val speaker: String,
    val text: String,
    val pose: String? = null
) : Statement

data class SetValue(
    val variable: String,
    val value: Any
) : Statement

data class KotlinCode(
    val code: Function<Unit>
) : Statement

data class Label(
    val name: String,
    val statements: MutableList<Statement>
) {
    fun kt(code: Function<Unit>) {
        statements.add(KotlinCode(code))
    }

    infix fun DynamicCharacter.says(text: String) {
        statements.add(Say(this.name, text))
    }

    infix fun DynamicCharacter.pose(pose: String): SayStage1 {
        val targetLabel = self()
        return SayStage1(targetLabel, this.name, pose)
    }

    infix fun String.set(value: Any) {
        statements.add(SetValue(this, value))
    }

    private fun self(): Label {
        return this
    }
}

data class SayStage1(
    val targetLabel: Label,
    val speaker: String,
    val pose: String
) {
    infix fun says(text: String) {
        targetLabel.statements.add(Say(speaker, text, pose))
    }
}

fun label(name: String, block: Label.() -> Unit): Label {
    val label = Label(name, mutableListOf())
    label.block()
    return label
}
