import tech.icey.pl8.DynamicCharacter
import tech.icey.pl8.label

val m = DynamicCharacter("m_name", image="monika", whatPrefix="\"", whatSuffix="\"", ctc="ctc", ctcPosition="fixed")

fun main() {
    val l = label ("ch30_altered") {
        "m_name" set "Monika"
        m says "Hey, is this working?"
        m says "Can you hear me?"
        m pose "1a" says "This place... feels different"
        m says "Is this still in DDLC? Or... is this still Ren'Py?"
        m says "Well I see, this is a Kotlin project"

        kt { System.err.println("Hello from Monika!") }
        m says "Well, I need to learn Kotlin now"
    }

    println(l)
}
