sealed class Score {
    class Equal(private val points: Int) : Score() {
        override fun toString(): String = when {
            points == 0 -> "Love-All"
            points == 1 -> "Fifteen-All"
            points == 2 -> "Thirty-All"
            points >= 3 -> "Deuce"
            else -> throw IllegalArgumentException("The value $points can not be negative")
        }
    }

    class AdvantageOrWin(
            private val pointsPlayer1: Int,
            private val pointsPlayer2: Int
    ) : Score() {
        override fun toString(): String = when {
            advantageForPlayer1() -> "Advantage player1"
            advantageForPlayer2() -> "Advantage player2"
            winForPlayer1() -> "Win for player1"
            else -> "Win for player2"
        }

        private fun winForPlayer1() = pointsPlayer1 >= 4 && pointsPlayer1 - pointsPlayer2 >= 2

        private fun advantageForPlayer2() =
                pointsPlayer2 > pointsPlayer1 && pointsPlayer2 - pointsPlayer1 == 1

        private fun advantageForPlayer1() = pointsPlayer1 >= 4 && pointsPlayer1 - pointsPlayer2 == 1
    }

    class Outgoing(
            private val pointsPlayer1: Int,
            private val pointsPlayer2: Int
    ) : Score() {
        override fun toString(): String = "${score(pointsPlayer1)}-${score(pointsPlayer2)}"

        private fun score(points: Int): String = when (points) {
            0 -> "Love"
            1 -> "Fifteen"
            2 -> "Thirty"
            3 -> "Forty"
            else -> throw IllegalArgumentException("The value $points can not be translate to a score")
        }
    }
}