# Solution to TradeLedger Test (Java Version)

## Summary
- Created a FeeCalculator class which the constructor takes in the following arguments:
  > standardRateDays - days which the standardRate applies
  > standardRate - rate in % for the first standardRateDays
  > additionalRate - rate in % charged per day after standardRateDays
  > serviceFeeType - how service fee is applied (e.g. on full invoice or on discount)
  > serviceFeeRate - rate in % charged as service fees

- Once a FeeCalculator object is created, run the calcFee method which takes in the invoice amount and the total days until repayment as arguments to calculate the total fee

- The selectServiceFeeBase method stores the different ways of calculating the base on which the service fees are worked out from which can add on more rules

- All fees generated are exclusive of GST

- src/main/java/App.java includes a sample use of the class which calculates and prints out the results for the 4 test cases provided which can be run by typing the command below:

```
./gradlew run
```

## Testing
- JUnit was utilized for writing the tests in src/test/java/FeeCalculatorTest.java
- Running the command below will run the tests and generate a report in build/reports/test/test/index.html:

```
./gradlew test
```
