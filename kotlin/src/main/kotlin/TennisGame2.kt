import Score.*

class TennisGame2(private val player1Name: String, private val player2Name: String) : TennisGame {
    var pointsPlayer1: Int = 0
    var pointsPlayer2: Int = 0

    override fun getScore(): String {
        if (pointsPlayer1 == pointsPlayer2)
            return Equal(pointsPlayer1).toString()
        if (pointsPlayer1 < 4 && pointsPlayer2 < 4)
            return Outgoing(pointsPlayer1, pointsPlayer2).toString()
        return AdvantageOrWin(pointsPlayer1, pointsPlayer2).toString()
    }

    override fun wonPoint(playerName: String) {
        if (playerName == player1Name)
            pointsPlayer1++
        else
            pointsPlayer2++
    }
}

