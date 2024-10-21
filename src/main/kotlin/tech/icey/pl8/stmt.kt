package tech.icey.pl8

interface Statement {
    fun execute(state: GameplayState)
}

data class Say(
    val speaker: String,
    val text: String,
    val pose: String? = null
) : Statement {
    override fun execute(state: GameplayState) {
        val speakerName = state.variables[speaker] ?: throw IllegalStateException("variable '$speaker' not found")
        print("$speakerName: $text")
        readLine()
    }
}

data class SetValue(
    val variable: String,
    val value: Any
) : Statement {
    override fun execute(state: GameplayState) {
        state.variables[variable] = value
    }
}

data class Jump(val target: String) : Statement {
    override fun execute(state: GameplayState) {
        val label = program[target] ?: throw IllegalStateException("label '$target' not found")
        state.currentLabel = label
        state.currentStmt = -1
    }
}

data class KotlinCode(val code: GameplayStateTransformer) : Statement {
    override fun execute(state: GameplayState) {
        code.apply(state)
    }
}

val program: MutableMap<String, Label> = mutableMapOf()

data class Label(
    val name: String,
    val statements: MutableList<Statement>
) {
    fun kt(code: GameplayStateTransformer) {
        statements.add(KotlinCode(code))
    }

    infix operator fun DynamicCharacter.compareTo(text: String): Int {
        statements.add(Say(this.name, text))
        return 42
    }

    infix operator fun DynamicCharacter.plus(pose: String): SayStage1 {
        val targetLabel = self()
        return SayStage1(targetLabel, this.name, pose)
    }

    fun set(key: String, value: Any) {
        statements.add(SetValue(key, value))
    }

    fun jump(target: String) {
        statements.add(Jump(target))
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
    infix operator fun compareTo(text: String): Int {
        targetLabel.statements.add(Say(speaker, text, pose))
        return 42
    }
}

fun label(name: String, block: Label.() -> Unit): Label {
    val label = Label(name, mutableListOf())
    label.block()

    if (program.containsKey(name)) {
        throw IllegalStateException("label $name already exists")
    }
    program[name] = label
    return label
}
