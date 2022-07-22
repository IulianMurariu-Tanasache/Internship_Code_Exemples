import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

// unit test vs integration test -> REST controller and calling other REST controllers

class NumbersProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
        return IntStream.range(0, 10).mapToObj(Arguments::of);
    }
}

public class CalculatorTest {

    private Calculator calculator = null;

    static IntStream numbersProvider() {
        return IntStream.range(0, 9);
    }

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
    @Disabled("Bad method")
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

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9})
    void testSquaringNumber(int nr) {
        assertEquals(nr * nr, calculator.multiply(nr, nr));
    }

    @ParameterizedTest
    @MethodSource("numbersProvider")
    void testAdditionWith0(int nr) {
        assertEquals(nr, calculator.add(0, nr));
    }

    @ParameterizedTest
    @ArgumentsSource(NumbersProvider.class)
    void testDivisionBy1(int nr) {
        assertEquals(nr, calculator.divide(nr, 1));
    }
}
