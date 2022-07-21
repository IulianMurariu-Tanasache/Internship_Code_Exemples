import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class CalculatorTestClass {

    private Calculator calculator = null;

    // @BeforeAll -> static
    @BeforeEach
    void initCalculator() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Test addition 1 + 2")
    void testAddition() {
        assumeTrue(calculator != null);
        assertEquals(3.0, calculator.add(1, 2));
    }

    @Test
    void testBadSubstract() {
        assumeTrue(calculator != null);
        assertEquals(0, calculator.badSubstract(1, 1));
    }

    @Test
    void groupTestForMultiplicationWith1() {
        assumeTrue(calculator != null);
        assertAll("multiplication with 1",
            () -> assertEquals(1, calculator.multiply(1, 1)),
            () -> assertEquals(2, calculator.multiply(1, 2)),
            () -> assertEquals(3, calculator.multiply(1, 3)),
            () -> assertEquals(4, calculator.multiply(1, 4)),
            () -> assertEquals(5, calculator.multiply(1, 5)),
            () -> assertEquals(6, calculator.multiply(1, 6)),
            () -> assertEquals(7, calculator.multiply(1, 7)),
            () -> assertEquals(8, calculator.multiply(1, 8)),
            () -> assertEquals(9, calculator.multiply(1, 9))
        );
    }

    @Test
    void testDivisionBy0Exception() {
        assumeTrue(calculator != null);
        assertThrows(ArithmeticException.class, () -> calculator.divide(2,0));
    }

    @TestFactory
    Stream<DynamicTest> testMultiplicationBy0() {
        return IntStream.range(0,9)
            .mapToDouble(nr -> nr)
            .mapToObj(number ->
                DynamicTest.dynamicTest("Multiplication: 0 * " + number, () -> {
                    double res = calculator.multiply(0, number);
                    assertEquals(0, res);
                })
            );
    }

}
