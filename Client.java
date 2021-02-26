import java.util.*;

public class Client {
  public static void main(String[] args) {

    List<Integer> numbers = new ArrayList<Integer>();

    // TC-1: Test cases with overflows -> averageOfAllNumbers 1.28849024E9
    numbers.add(Integer.MAX_VALUE);
    numbers.add(Integer.MAX_VALUE / 3);
    numbers.add(Integer.MAX_VALUE / 3);
    numbers.add(Integer.MAX_VALUE);
    numbers.add(Integer.MAX_VALUE / 3);
    // Hashmap entry -> 1:[2.14748365E9, 2.14748365E9, 7.158279E8] ; 2:[7.158279E8]

    /*
     * TC-2: Test cases with negative numbers -> averageOfAllNumbers -0.2
     * numbers.add(-1); numbers.add(2); numbers.add(-3); numbers.add(-4);
     * numbers.add(5);
     */
    // Hashmap entry-> 5:[-0.2]

    /*
     * TC-3: Test cases with underflows -> averageOfAllNumbers: -1.28849024E9
     * numbers.add(Integer.MIN_VALUE); numbers.add(Integer.MIN_VALUE/3);
     * numbers.add(Integer.MIN_VALUE/3); numbers.add(Integer.MIN_VALUE);
     * numbers.add(Integer.MIN_VALUE/3);
     */
    // Hashmap entry-> 1:[-2.14748365E9, -7.158279E8] ; 3:[-7.158279E8]

    int totalElements = numbers.size();
    HashMap<Integer, List<Float>> subsetAverageMap = new HashMap<>();

    Average average = new Average();
    Float averageOfAllNumbers = average.computeAverage(numbers, 0, totalElements, subsetAverageMap);
    System.out.println("averageOfAllNumbers " + averageOfAllNumbers);
  }
}