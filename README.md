# Solution to TradeLedger Test (Java Version)

## Summary

- Created a FeeCalculator class (src/main/java/FeeCalculator.java) which has a constructor taking in the following arguments:

  > standardRateDays - days which the standardRate applies

  > standardRate - rate in % charged for the first standardRateDays

  > additionalRate - rate in % charged per day after standardRateDays

  > serviceFeeType - the basis of calculating the service fee (e.g. on full invoice or on discount)

  > serviceFeeRate - rate in % charged as service fees

- Once a FeeCalculator object is created, run the calcFee method which takes in the invoice amount and the total days until repayment as arguments to calculate and return the total fee

- The selectServiceFeeBase method stores the different ways (and isn't restricted to what's in there currently) of obtaining the base amount used in calculating the service fee

- All fees generated are exclusive of GST

- src/main/java/App.java includes a sample use of the class which calculates and prints out the results for the 4 test cases provided and can be run by typing the command below:

```
./gradlew run
```

## Testing

- JUnit was utilized for writing the tests which can be found in src/test/java/FeeCalculatorTest.java

- Running the command below will run the tests and generate a report in build/reports/test/test/index.html:

```
./gradlew test
```
