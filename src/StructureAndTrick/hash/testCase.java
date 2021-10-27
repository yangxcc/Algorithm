package StructureAndTrick.hash;

public class testCase {


    public static void main(String[] args) {
        RandomPool randomPool = new RandomPool();
        randomPool.insert(1);
        randomPool.insert(2);
        randomPool.insert(3);
        randomPool.insert(4);
        randomPool.insert(5);
        System.out.println(randomPool.size);
        System.out.println(randomPool.getRandom());
        randomPool.delete(3);
        System.out.println(randomPool.size);
        System.out.println(randomPool.getRandom());

    }
}
