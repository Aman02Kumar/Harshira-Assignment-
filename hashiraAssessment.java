import java.io.*;
import java.math.BigInteger;
import java.util.*;
import org.json.JSONObject;

public class PolynomialSecretFinder {

    private static final String SAMPLE_ONE = """
    {
        "keys": { "n": 4, "k": 3 },
        "1": { "base": "10", "value": "4" },
        "2": { "base": "2", "value": "111" },
        "3": { "base": "10", "value": "12" },
        "6": { "base": "4", "value": "213" }
    }
    """;

    private static final String SAMPLE_TWO = """
    {
        "keys": { "n": 10, "k": 7 },
        "1":  { "base": "6",  "value": "13444211440455345511" },
        "2":  { "base": "15", "value": "aed7015a346d63" },
        "3":  { "base": "15", "value": "6aeeb69631c227c" },
        "4":  { "base": "16", "value": "e1b5e05623d881f" },
        "5":  { "base": "8",  "value": "316034514573652620673" },
        "6":  { "base": "3",  "value": "2122212201122002221120200210011020220200" },
        "7":  { "base": "3",  "value": "20120221122211000100210021102001201112121" },
        "8":  { "base": "6",  "value": "20220554335330240002224253" },
        "9":  { "base": "12", "value": "45153788322a1255483" },
        "10": { "base": "7",  "value": "1101613130313526312514143" }
    }
    """;

    public static void main(String[] args) {
        System.out.println("\n==== Secret Recovery using Polynomial Interpolation ====\n");

        BigInteger result1 = evaluateCase(SAMPLE_ONE, "First Sample");
        BigInteger result2 = evaluateCase(SAMPLE_TWO, "Second Sample");

        System.out.println("\n===== Final Answers =====");
        System.out.println("Secret from Sample 1: " + result1);
        System.out.println("Secret from Sample 2: " + result2);
    }

    private static BigInteger evaluateCase(String rawJson, String label) {
        BigInteger outcome = BigInteger.ZERO;

        try {
            JSONObject parsed = new JSONObject(rawJson);
            JSONObject limits = parsed.getJSONObject("keys");
            int total = limits.getInt("n");
            int required = limits.getInt("k");

            TreeMap<Integer, BigInteger> dataPoints = new TreeMap<>();

            for (String identifier : parsed.keySet()) {
                if (identifier.equals("keys")) continue;

                int x = Integer.parseInt(identifier);
                JSONObject node = parsed.getJSONObject(identifier);

                int base = Integer.parseInt(node.getString("base"));
                String encoded = node.getString("value");

                BigInteger decodedY = new BigInteger(encoded, base);
                dataPoints.put(x, decodedY);
            }

            outcome = computeUsingLagrange(dataPoints, required);

            System.out.println("[" + label + "] Constant term (c) recovered: " + outcome);
        } catch (Exception ex) {
            System.err.println("Error while decoding " + label + ": " + ex.getMessage());
        }

        return outcome;
    }

    private static BigInteger computeUsingLagrange(Map<Integer, BigInteger> all, int howMany) {
        List<Integer> x = new ArrayList<>();
        List<BigInteger> y = new ArrayList<>();

        all.entrySet().stream().limit(howMany).forEach(e -> {
            x.add(e.getKey());
            y.add(e.getValue());
        });

        BigInteger finalSum = BigInteger.ZERO;

        for (int i = 0; i < howMany; i++) {
            BigInteger currentX = BigInteger.valueOf(x.get(i));
            BigInteger currentY = y.get(i);

            BigInteger top = BigInteger.ONE;
            BigInteger bottom = BigInteger.ONE;

            for (int j = 0; j < howMany; j++) {
                if (i == j) continue;

                BigInteger otherX = BigInteger.valueOf(x.get(j));
                top = top.multiply(otherX.negate());
                bottom = bottom.multiply(currentX.subtract(otherX));
            }

            finalSum = finalSum.add(currentY.multiply(top).divide(bottom));
        }

        return finalSum;
    }
}
