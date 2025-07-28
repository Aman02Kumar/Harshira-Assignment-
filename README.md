# Harshira-Assignment-
# 🔐 Shamir Secret Sharing - Polynomial Interpolation

This project is a Java-based implementation of a simplified version of the **Shamir's Secret Sharing** algorithm. It was developed as part of the **Catalog Placements Assignment**. The program recovers the constant term `c` (the hidden secret) from an encoded polynomial using **Lagrange Interpolation**.

---

## 📌 Problem Summary

You are given `n` encoded `(x, y)` points, where:

- `x` is the key (e.g. "2")
- `y` is a string representing a number in a certain base (e.g. `"111"` in base `2`)

Your task is to:

- Decode each y-value to base 10
- Pick any `k` such points (minimum required)
- Interpolate the unknown polynomial using **Lagrange interpolation**
- Recover the secret: the constant term `f(0)`

---

## ✅ Features

- Reads and parses hardcoded JSON test cases
- Decodes values from arbitrary bases (e.g. base-2, base-16, base-15)
- Supports high precision with `BigInteger`
- Calculates the secret using **Lagrange interpolation**
- Clean CLI output with step-by-step logs

---

## 🛠️ Tech Stack

- Java 17+
- org.json (JSON parser)
- No external files required — test cases are embedded

---

## 📂 File Structure

📁 ShamirSecretSharing
├── PolynomialSecretFinder.java # Main class with decoding and interpolation logic
└── README.md # This file

yaml
Copy
Edit

---

## ▶️ How to Run

### 1. Compile the code

Ensure you have `org.json` in your classpath. You can download it from [here](https://mvnrepository.com/artifact/org.json/json).

```bash
javac -cp .:json.jar PolynomialSecretFinder.java
2. Run the code
bash
Copy
Edit
java -cp .:json.jar PolynomialSecretFinder
📝 Output will display the decoded points and the computed secret for both test cases.

📈 Sample Output
sql
Copy
Edit
==== Secret Recovery using Polynomial Interpolation ====

[First Sample] Constant term (c) recovered: 3
[Second Sample] Constant term (c) recovered: 123456789012345678901234567890

===== Final Answers =====
Secret from Sample 1: 3
Secret from Sample 2: 123456789012345678901234567890
🤔 Why Lagrange Interpolation?
Given k points on a degree k-1 polynomial, Lagrange interpolation lets us compute the polynomial exactly, especially the value at x = 0, which corresponds to the hidden secret c.

🧠 Bonus Insights
The BigInteger class ensures we handle large numbers safely.

The JSON parsing is done directly from hardcoded strings (no file I/O needed).

Easily extensible for more test cases or integration with file-based input.

🔗 Submission Guidelines
Push this project to your GitHub repository

Submit the GitHub repo link in the assignment form

👨‍💻 Author
Name: Aman Kaushik

Language Used: Java

Assignment: Catalog Placements - Online Assessment

📄 License
This project is for educational and placement purposes only. Please do not share or reuse without permission.

yaml
Copy
Edit

---
