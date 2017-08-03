import org.junit.Test;
import static org.junit.Assert.*;
import java.math.*;

public class FeeCalculatorTest {
    @Test public void testStandardCharge() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        // $5,000 * 5% = $250.00
        assertEquals("Test if standard charge is calculated correctly", (new BigDecimal("250.00")), sampleCalculator.calcStandardCharge("5000"));
    }

    @Test public void testAdditionalChargeDays() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        // 11 - 10 = 1
        assertEquals("Test if additional charge days get calculated correctly", 1, sampleCalculator.calcAdditionalChargeDays(11));
    }

    @Test public void testNegativeChargeDays() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        // 9 - 10 = -1
        assertEquals("Test if total days less than standardRateDays gives a 0", 0, sampleCalculator.calcAdditionalChargeDays(9));
    }

    @Test public void testAdditionalCharge() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        // $10,000 * 1% * 5 = $500.00
        assertEquals("Test if additional charge is calculated correctly", (new BigDecimal("500.00")), sampleCalculator.calcAdditionalCharge("10000", 5));
    }

    @Test public void testNilAdditionalCharge() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        assertEquals("Test if additional charge is 0 when additionalChargeDays is 0", (new BigDecimal("0.00")), sampleCalculator.calcAdditionalCharge("10000", 0));
    }

    @Test public void testServiceFeeBaseRule1() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        assertEquals("Test if rule 1 selects the invoice amount as the base", (new BigDecimal("10000.00")), sampleCalculator.selectServiceFeeBase("10000", new BigDecimal("500.00")));
    }

    @Test public void testServiceFeeBaseRule2() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "discountRate", "0.02");
        assertEquals("Test if rule 2 selects the total charge as the base", (new BigDecimal("500.00")), sampleCalculator.selectServiceFeeBase("10000", new BigDecimal("500.00")));
    }

    @Test public void testServiceFee() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        // $10,000 * 2% = $200.00
        assertEquals("Test if service fee is calculated correctly", (new BigDecimal("200.00")), sampleCalculator.calcServiceFee(new BigDecimal("10000.00")));
    }

    @Test public void testAttributeStandardRateDays() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        sampleCalculator.setStandardRateDays(11);
        assertEquals("Test if attribute accessor and mutator works for standardRateDays", 11, sampleCalculator.getStandardRateDays());
    }

    @Test public void testAttributeStandardRate() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        sampleCalculator.setStandardRate("0.06");
        assertEquals("Test if attribute accessor and mutator works for standardRate", (new BigDecimal("0.06")), sampleCalculator.getStandardRate());
    }

    @Test public void testAttributeAdditionalRate() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        sampleCalculator.setAdditionalRate("0.02");
        assertEquals("Test if attribute accessor and mutator works for additionalRate", (new BigDecimal("0.02")), sampleCalculator.getAdditionalRate());
    }

    @Test public void testAttributeServiceFeeType() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        sampleCalculator.setServiceFeeType("discountRate");
        assertEquals("Test if attribute accessor and mutator works for serviceFeeType", "discountRate", sampleCalculator.getServiceFeeType());
    }

    @Test public void testAttributeServiceFeeRate() {
        FeeCalculator sampleCalculator = new FeeCalculator(10, "0.05", "0.01", "invoiceAmount", "0.02");
        sampleCalculator.setServiceFeeRate("0.03");
        assertEquals("Test if attribute accessor and mutator works for serviceFeeRate", (new BigDecimal("0.03")), sampleCalculator.getServiceFeeRate());
    }
}
