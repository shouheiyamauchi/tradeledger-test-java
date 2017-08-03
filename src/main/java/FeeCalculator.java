import java.math.*;

public class FeeCalculator {
  private int standardRateDays;     // days which the standardRate applies
  private BigDecimal standardRate;  // rate in % charged for the first standardRateDays
  private BigDecimal additionalRate;// rate in % charged per day after standardRateDays
  private String serviceFeeType;    // the basis of calculating the service fee (e.g. on full invoice or on discount)
  private BigDecimal serviceFeeRate;// rate in % charged as service fees

  FeeCalculator(int standardRateDays, String standardRate, String additionalRate, String serviceFeeType, String serviceFeeRate) {
    this.standardRateDays = standardRateDays;
    this.standardRate = new BigDecimal(standardRate);
    this.additionalRate = new BigDecimal(additionalRate);
    this.serviceFeeType = serviceFeeType;
    this.serviceFeeRate = new BigDecimal(serviceFeeRate);
  }

  // calculate the total fee based on invoice amount and total days until repayment
  BigDecimal calcFee(String invoiceAmount, int totalDays) {
    BigDecimal standardCharge = calcStandardCharge(invoiceAmount);
    int additionalDays = calcAdditionalChargeDays(totalDays);
    BigDecimal additionalCharge = calcAdditionalCharge(invoiceAmount, additionalDays);
    BigDecimal totalCharge = standardCharge.add(additionalCharge); // sum up the standard fee and fees on the additional days to get the total charges
    BigDecimal serviceFeeBase = selectServiceFeeBase(invoiceAmount, totalCharge);
    BigDecimal serviceFee = calcServiceFee(serviceFeeBase); // add the service fee to the total charges to arrive at the total fees
    BigDecimal totalFee = totalCharge.add(serviceFee);

    // ensure all amounts returned are displayed to 2 decimal points
    return totalFee.setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  // calculate the standard fee for the first "standardRateDays"
  BigDecimal calcStandardCharge(String invoiceAmount) {
    return (new BigDecimal(invoiceAmount)).multiply(standardRate).setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  // calculate how many additional days on top of the standardRateDays will be charged
  int calcAdditionalChargeDays(int totalDays) {
    int additionalChargeDays = totalDays - standardRateDays;
    return (additionalChargeDays > 0) ? additionalChargeDays : 0;
  }

  // calculate the fee on the additional days
  BigDecimal calcAdditionalCharge(String invoiceAmount, int additionalDays) {
    return (new BigDecimal(invoiceAmount)).multiply(additionalRate.multiply(new BigDecimal(additionalDays))).setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  // select the base amount for calculating the service fee based on the selected rule
  BigDecimal selectServiceFeeBase(String invoiceAmount, BigDecimal totalCharge) {
    BigDecimal serviceFeeBase = new BigDecimal(0);

    if (serviceFeeType == "invoiceAmount") {
      // apply service fee on invoice amount
      serviceFeeBase = new BigDecimal(invoiceAmount);
    } else if (serviceFeeType == "discountRate") {
      // apply service fee rate on standard and additional charges
      serviceFeeBase = totalCharge;
    }

    return serviceFeeBase.setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  // apply the service fee rate to the calculated service fee base
  BigDecimal calcServiceFee(BigDecimal serviceFeeBase) {
    return serviceFeeBase.multiply(serviceFeeRate).setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  // attribute accessors and mutators
  int getStandardRateDays() {
    return standardRateDays;
  }

  BigDecimal getStandardRate() {
    return standardRate;
  }

  BigDecimal getAdditionalRate() {
    return additionalRate;
  }

  String getServiceFeeType() {
    return serviceFeeType;
  }

  BigDecimal getServiceFeeRate() {
    return serviceFeeRate;
  }

  void setStandardRateDays(int standardRateDays) {
    this.standardRateDays = standardRateDays;
  }

  void setStandardRate(String standardRate) {
    this.standardRate = new BigDecimal(standardRate);
  }

  void setAdditionalRate(String additionalRate) {
    this.additionalRate = new BigDecimal(additionalRate);
  }

  void setServiceFeeType(String serviceFeeType) {
    this.serviceFeeType = serviceFeeType;
  }

  void setServiceFeeRate(String serviceFeeRate) {
    this.serviceFeeRate = new BigDecimal(serviceFeeRate);
  }
}
