package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
public class NumberRangeSummarizerImplement implements NumberRangeSummarizer {
    public NumberRangeSummarizerImplement() {
    }

    //collect the input
    @Override
    public Collection<Integer> collect(String input) {
        List<Integer> numbers = new ArrayList<>();

        if (input == null || input.isEmpty()) {
            return numbers;
        }

        String[] tokens = input.split(",");
        for (String token : tokens) {
            try {
                numbers.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                // ignore non-numeric tokens
            }
        }

        return numbers;
    }

    //get the summarized string
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        List<Integer> sortedNumbers = new ArrayList<>(input);
        sortedNumbers.sort(Integer::compare);

        StringBuilder sb = new StringBuilder();
        int start = sortedNumbers.get(0);
        int prev = start;
        for (int i = 1; i < sortedNumbers.size(); i++) {
            int current = sortedNumbers.get(i);
            if (current != prev + 1) {
                if (start == prev) {
                    sb.append(start).append(", ");
                } else {
                    sb.append(start).append("-").append(prev).append(", ");
                }
                start = current;
            }
            prev = current;
        }
        // append the last range
        if (start == prev) {
            sb.append(start);
        } else {
            sb.append(start).append("-").append(prev);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        NumberRangeSummarizer numberRangeSummarizer = new NumberRangeSummarizerImplement();
        System.out.println(numberRangeSummarizer.summarizeCollection(numbers));

    }
}
