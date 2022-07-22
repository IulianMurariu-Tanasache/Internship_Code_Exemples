import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoAdvanced {

    @Test
    @DisplayName("Testing void methods")
    public void testingVoidMethods() {
        MyList myList = mock(MyList.class);
        doNothing().when(myList).add(anyInt(), isA(String.class));
        myList.add(0, "");
        verify(myList, times(1)).add(0, "");

        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);
        doNothing().when(myList).add(any(Integer.class), valueCapture.capture());
        myList.add(0, "captured");
        assertEquals("captured", valueCapture.getValue());

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);

            assertEquals(3, arg0);
            assertEquals("answer me", arg1);
            return null;
        }).when(myList).add(any(Integer.class), any(String.class));
        myList.add(3, "answer me");
    }

    /*
     * To mock a final method, we need a plugin for Mockito.
     * Plugins are added in src/test/resources/mockito-extensions
     * For mocking final methods: in a file called org.mockito.plugins.MockMaker -> mock-maker-inline
     */

    @Test
    @DisplayName("Mocking final methods")
    public void whenMockFinalMethodMockWorks() {
        MyList myList = new MyList();
        MyList mock = mock(MyList.class);
        when(mock.finalMethod()).thenReturn(1);

        // verifying that the mock is called: mock -> 1 | actual instance -> 0
        assertNotEquals(mock.finalMethod(), myList.finalMethod());

        FinalList finalList = new FinalList();
        FinalList finalListMock = mock(FinalList.class);
        when(finalListMock.size()).thenReturn(2);

        // verifying that the mock is called: mock -> 2 | actual instance -> 1
        assertNotEquals(finalListMock.size(), finalList.size());
    }

    @Test
    @DisplayName("Mocking static methods")
    void mockingStaticMethods() {
        assertEquals("Baeldung", StaticUtils.name());

        /* mockStatic -> returns a static mocked object that is thread-local and needs to be closed by the entity that mocked it
         * that's why is in a try-with-resource block -> it will be closed at the end of the block
         * this is because test run concurrently and may call the same method and bad things may happen
         */
        try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
            utilities.when(StaticUtils::name).thenReturn("Eugen");
            assertEquals("Eugen", StaticUtils.name());
        }

        // closing manually
        MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class);
        utilities.when(StaticUtils::name).thenReturn("Eugen");
        assertEquals("Eugen", StaticUtils.name());
        utilities.close();

        // mocking static method with arguments
        assertEquals(StaticUtils.range(2, 6), List.of(2,3,4,5));

        try (MockedStatic<StaticUtils> otherUtilities = Mockito.mockStatic(StaticUtils.class)) {
            otherUtilities.when(() -> StaticUtils.range(2, 6))
                .thenReturn(Arrays.asList(10, 11, 12));

            assertEquals(StaticUtils.range(2, 6), List.of(10, 11, 12));
        }
    }
}
