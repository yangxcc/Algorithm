package 差分数组;

/**
 * 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶
 * （也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
 *
 * 这儿有一份乘客行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了第 i 组乘客的行程信息：
 *
 *      必须接送的乘客数量；
 *      乘客的上车地点；
 *      以及乘客的下车地点。
 *
 */
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        // 根据题意，可以看到最多有1000个车站
        int[] stations = new int[1001];
        Difference d = new Difference(stations);

        for (int[] trip : trips) {
            int val = trip[0];
            int left = trip[1];
            int right = trip[2];
            // 这里需要注意，乘客上车是在left到right-1这个区间内的，因为他在right的时候下车了
            d.update(left, right - 1, val);
        }

        // 一定不要忘记还原回来
        stations = d.recover();

        for (int i = 0; i < 1001; i++) {
            if (stations[i] > capacity) {
                return false;
            }
        }
        return true;
    }
}
