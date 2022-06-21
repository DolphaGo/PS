import java.util.*

var dy = intArrayOf(-1, 1, 0, 0)
var dx = intArrayOf(0, 0, -1, 1)

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())

    val h = Integer.parseInt(st.nextToken())
    val w = Integer.parseInt(st.nextToken())
    val k = Integer.parseInt(st.nextToken())

    val map = Array(h) { CharArray(w) }
    for (i in 0 until h) {
        val line = readLine()
        for (j in 0 until w) {
            map[i][j] = line[j]
        }
    }

    val visit = Array(h) { BooleanArray(w) }
    var count = 0
    visit[h - 1][0] = true
    count += dfs(map, visit, h - 1, 0, k - 1)
    print(count)
}

fun dfs(map: Array<CharArray>, visit: Array<BooleanArray>, y: Int, x: Int, k: Int): Int {
    var sum = 0
    if (k == 0) {
        return if (y == 0 && x == map[0].size - 1) 1 else 0
    }

    for (i in 0..3) {
        val ny = y + dy[i]
        val nx = x + dx[i]
        if (ny >= 0 && nx >= 0 && ny < map.size && nx < map[0].size && !visit[ny][nx] && map[ny][nx] != 'T') {
            visit[ny][nx] = true
            sum += dfs(map, visit, ny, nx, k - 1)
            visit[ny][nx] = false
        }
    }

    return sum
}
