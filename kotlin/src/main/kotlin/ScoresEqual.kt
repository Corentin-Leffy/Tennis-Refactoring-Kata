class ScoresEqual(
        private val points: Int
) : Score {
    override val score: String
        get() = when (points) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
}