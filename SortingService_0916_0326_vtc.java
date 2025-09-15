// 代码生成时间: 2025-09-16 03:26:13
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortingService {

    /**
     * Sorts a list of integers using the insertion sort algorithm.
     *
     * @param numbers the list of integers to be sorted.
     * @return the sorted list of integers.
     * @throws IllegalArgumentException if the input list is null.
     */
    public List<Integer> insertionSort(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }
        for (int i = 1; i < numbers.size(); i++) {
            int current = numbers.get(i);
            int j = i - 1;
            while (j >= 0 && numbers.get(j) > current) {
                numbers.set(j + 1, numbers.get(j));
                j--;
            }
            numbers.set(j + 1, current);
        }
        return numbers;
    }

    /**
     * Sorts a list of integers in descending order using Java 8 streams.
     *
     * @param numbers the list of integers to be sorted.
     * @return the sorted list of integers in descending order.
     * @throws IllegalArgumentException if the input list is null.
     */
    public List<Integer> descendingSort(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }
        return numbers.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    }

    /**
     * Sorts a list of integers using Java 8 streams.
     *
     * @param numbers the list of integers to be sorted.
     * @return the sorted list of integers.
     * @throws IllegalArgumentException if the input list is null.
     */
    public List<Integer> streamSort(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }
        return numbers.stream()
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Tests the sorting functionality with a sample list.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SortingService service = new SortingService();
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> sortedNumbers = service.streamSort(numbers);
        System.out.println("Sorted Numbers: " + sortedNumbers);
    }
}
