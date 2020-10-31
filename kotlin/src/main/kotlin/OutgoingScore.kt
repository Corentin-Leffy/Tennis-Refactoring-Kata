class OutgoingScore(
        private val score1: Int,
        private val score2: Int
) : Score {
    private val scoreByPoints = mapOf(
            0 to "Love",
            1 to "Fifteen",
            2 to "Thirty",
            3 to "Forty"
    )

    override val score: String
        get() = "${scoreByPoints[score1]}-${scoreByPoints[score2]}"
}