import tech.icey.pl8.DynamicCharacter
import tech.icey.pl8.label
import tech.icey.pl8.play

val m = DynamicCharacter("m_name", image="monika", whatPrefix="\"", whatSuffix="\"", ctc="ctc", ctcPosition="fixed")
val n = DynamicCharacter("n_name", image="natsuki", whatPrefix="\"", whatSuffix="\"", ctc="ctc", ctcPosition="fixed")
val s = DynamicCharacter("s_name", image="sayori", whatPrefix="\"", whatSuffix="\"", ctc="ctc", ctcPosition="fixed")
val y = DynamicCharacter("y_name", image="yuri", whatPrefix="\"", whatSuffix="\"", ctc="ctc", ctcPosition="fixed")

val ch30 = label ("ch30_altered") {
    set("m_name", "Monika")
    m > "Hey, is this working?"
    m > "Can you hear me?"
    m + "1a" > "This place... feels different"
    m > "Is this still in DDLC? Or... is this still Ren'Py?"
    m > "Well I see, this is a Kotlin project"

    kt {
        System.err.println("Hello from Monika!")
        it.variables.put("just", "Monika")
    }
    m > "Well, I need to learn Kotlin now"
    m > "But before that, let me bring you a song"
    jump("ch30_song")
}

val ch30_song = label ("ch30_song") {
    set("y_name", "Yuri")
    set("s_name", "Sayori")
    set("n_name", "Natsuki")

    s > "Hey Hey my heart's beating when I'm hanging out with you"
    s > "Why does my heart ache when I hear you feel the same way too"
    n > "Just like a Sunday, it's sweet every time I teach you something new"
    n > "Is this by chance or fate whenever it's just me and you"
    n > "Don't get the wrong idea"
    y > "When we touch, it'll never be enough"
    s > "Is it way too much if you had to choose just one of us"
    n > "Tell me, tell me please, is this what I think or is it just me"
    n > "Don't wake me up from this sweet little dream"
    n > "Where we'll be together forever, we're never gonna be apart"
    s > "Will it be okay if I express my love for you this way"
    s > "No matter what you do or what you say"
    s > "We will be together forever, we're never gonna be apart"
    m > "We're never gonna be apart"
    y > "Hey Hey, when I'm next to you I don't know what to do"
    y > "Why does it feel so great when our eyes meet out of the blue"
    y > "I'm sorry, I said too much, I really love-"
    m > "The way you write even when you don't have a clue"
    m > "I want to hear you say this love that I'm feeling is true"
    n > "Tasty love, something I want more of"
    y > "Will it make the cut if you had to choose just one of us"
    m > "Shall I leave you be, is it love if I set you free"
    m > "But even if it's not reality"
    m > "let's be together forever, we're never gonna be apart"
    y > "How can I convey my love for you before they fly away"
    y > "I think about it all day every day"
    m > "We'll be together forever, we're never gonna be apart"
    m > "One by one, they only fall apart"
    m > "Can it be undone, why can't I just be the one for once"
    s > "We'll be together forever, we're never gonna be apart"
    m > "Maybe we'll never be together"
    m > "But forever you'll be in my heart"
    m > "..."
    m > "..."
    m > "MONIKA"
}

fun main() {
    play("ch30_altered")
}
