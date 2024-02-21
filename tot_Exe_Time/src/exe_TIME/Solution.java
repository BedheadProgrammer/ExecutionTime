package exe_TIME;

import java.util.*;

public class Solution {
    public static List<Integer> getTotalExecutionTime(int n, List<String> logs) {
        if (n <= 0 || logs == null || logs.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> exclusiveTimes = new ArrayList<>(Collections.nCopies(n, 0));
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;

        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String action = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (action.equals("start")) {
                if (!stack.isEmpty()) {
                    int prevId = stack.peek();
                    exclusiveTimes.set(prevId, exclusiveTimes.get(prevId) + time - prevTime);
                }
                stack.push(id);
                prevTime = time;
            } else {
                if (stack.isEmpty()) {
                    continue; // Ignore invalid end actions
                }
                int poppedId = stack.pop();
                exclusiveTimes.set(poppedId, exclusiveTimes.get(poppedId) + time - prevTime + 1);
                prevTime = time + 1;
            }
        }

        return exclusiveTimes;
    }

    public static void main(String[] args) {
        // Sample input
        int n = 4;
        List<String> logs = Arrays.asList("0:start:0", "1:start:2", "2:start:3", "2:end:4", "1:end:5", "0:end:6");

        List<Integer> result = getTotalExecutionTime(n, logs);

        // Print execution times for all functions from id 0 to n-1
        for (int i = 0; i < n; i++) {
            System.out.println("Function " + i + ": " + result.get(i));
        }
    }
}
