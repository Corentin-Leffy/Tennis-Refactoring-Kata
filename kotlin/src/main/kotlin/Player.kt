data class Player(val name: String) {
    var points = 0
        private set

    fun wonPoint() {
        points++
    }

    fun isCalled(name: String): Boolean = this.name == name
}
