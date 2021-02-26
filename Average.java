import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class Average {
    final Float computeAverage(List<Integer> numbers, int startIndex, int totalElements,
            HashMap<Integer, List<Float>> subsetAverageMap) {

        if (startIndex > totalElements - 1) {
            float totalAverage = computeWeightedAverage(subsetAverageMap, totalElements);
            return totalAverage;
        }

        float sumSoFar, subsetAverage;
        sumSoFar = subsetAverage = 0;

        int currentIndex = startIndex;
        for (currentIndex = startIndex; currentIndex <= totalElements - 1; currentIndex++) {
            // check for overflow and underflow while summing
            if ((numbers.get(currentIndex) + sumSoFar <= Integer.MAX_VALUE)
                    && (numbers.get(currentIndex) + sumSoFar >= Integer.MIN_VALUE)) {
                sumSoFar += numbers.get(currentIndex);
            } else {
                break;
            }
        }

        int elementsProcessed = currentIndex - startIndex;
        subsetAverage = sumSoFar / elementsProcessed;

        addSubsetAverageToMap(subsetAverageMap, elementsProcessed, subsetAverage);

        return computeAverage(numbers, currentIndex, totalElements, subsetAverageMap);
    }

    private float computeWeightedAverage(HashMap<Integer, List<Float>> subsetAverageMap, int N) {
        // function to compute weighted average
        if (subsetAverageMap == null || subsetAverageMap.size() == 0)
            return 0;

        float average = 0;
        Iterator<Map.Entry<Integer, List<Float>>> hashMapIterator = subsetAverageMap.entrySet().iterator();

        while (hashMapIterator.hasNext()) {
            Map.Entry<Integer, List<Float>> entry = hashMapIterator.next();
            int numberOfElementsInSet = entry.getKey();
            List<Float> averageOfNumbersInSet = entry.getValue();
            System.out.println(numberOfElementsInSet + ":" + averageOfNumbersInSet);
            for (int i = 0; i < averageOfNumbersInSet.size(); i++) {
                average += ((numberOfElementsInSet) * (averageOfNumbersInSet.get(i))) / N;
            }
        }

        return average;
    }

    private void addSubsetAverageToMap(HashMap<Integer, List<Float>> subsetAverageMap, int elementsProcessed,
            float subsetAverage) {

        if (subsetAverageMap.containsKey(elementsProcessed)) {
            List<Float> existingAverages = subsetAverageMap.get(elementsProcessed);
            existingAverages.add(subsetAverage);
            subsetAverageMap.put(elementsProcessed, existingAverages);
        } else {
            List<Float> averages = new ArrayList<>();
            averages.add(subsetAverage);
            subsetAverageMap.put(elementsProcessed, averages);
        }
    }
}