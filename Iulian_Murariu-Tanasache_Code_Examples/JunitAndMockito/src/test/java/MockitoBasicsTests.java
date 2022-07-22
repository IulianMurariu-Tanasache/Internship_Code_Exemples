import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoBasicsTests {

    @Mock
    private JobService jobService;

    @Test
    @DisplayName("Using Mockito verify")
    void usingMockitoVerify() {
        List<String> mockedList = mock(MyList.class);
        mockedList.size();
        verify(mockedList).size();
        verify(mockedList, times(1)).size();
        verify(mockedList, never()).clear();

        // mockedList.size();
        mockedList.add("a parameter");
        verify(mockedList).add(anyString());
        mockedList.clear();

        InOrder inOrder = Mockito.inOrder(mockedList);
        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add("a parameter");
        inOrder.verify(mockedList).clear();

        mockedList.add("test");
        verify(mockedList, atLeast(2)).add(anyString());
    }

    @Test
    @DisplayName("Using argument matchers")
    void usingArgumentMatchers() {
        FlowerService flowerServiceMock = mock(FlowerService.class);
        doReturn("Flower").when(flowerServiceMock).analyze("poppy");
        assertEquals("Flower", flowerServiceMock.analyze("poppy"));
        when(flowerServiceMock.analyze(anyString())).thenReturn("Flower");
        assertEquals("Flower", flowerServiceMock.analyze("Not a flower"));

        // when(flowerServiceMock.isABigFlower("poppy", anyInt())).thenReturn(true); -> wrong
        when(flowerServiceMock.isABigFlower(eq("poppy"), anyInt())).thenReturn(true);
        assertTrue(flowerServiceMock.isABigFlower("poppy", 1));
        //  flowerServiceMock.analyze("poppy");
        verify(flowerServiceMock).analyze(or (eq("poppy"), endsWith("y")));
    }

    @Test
    @DisplayName("Mocking interfaces with default methods")
    public void givenDefaultMethod_whenCallRealMethod_thenNoExceptionIsRaised() {
        Person person = new Person("John", "Sales Representative");

        when(jobService.findCurrentJobPosition(person))
            .thenReturn(Optional.of("Sales Representative"));

        doCallRealMethod().when(jobService)
            .assignJobPosition(
                Mockito.any(Person.class),
                Mockito.any(String.class)
            );

        assertFalse(jobService.assignJobPosition(person, "Sales Representative"));
    }

    @Test
    public void whenPersonWithJob_thenIsNotEntitled() {
        Person peter = new Person("Peter");
        Person linda = new Person("Linda");

        String jobPosition = "Teacher";

        when(jobService.findCurrentJobPosition(any(Person.class))).thenReturn(Optional.empty());

        when(jobService.findCurrentJobPosition(
            ArgumentMatchers.argThat(p -> p.getName().equals("Peter"))))
            .thenReturn(Optional.of(jobPosition));

        doCallRealMethod().when(jobService).personIsEntitledToUnemploymentSupport(
            any(Person.class)
        );

        assertTrue(jobService.personIsEntitledToUnemploymentSupport(linda));
        assertFalse(jobService.personIsEntitledToUnemploymentSupport(peter));
    }

    @Test
    @DisplayName("Some Mockito when examples")
    void someMockitoWhenExamples() {
        MyList listMock = Mockito.mock(MyList.class);
        when(listMock.add(anyString()))
            .thenReturn(false)
            .thenThrow(IllegalStateException.class);

        listMock.add("String");
        assertThrows(IllegalStateException.class, () -> listMock.add("String")); // will throw the exception

        MyList spy = Mockito.spy(listMock);

        doThrow(NullPointerException.class).when(spy).size();
        assertThrows(NullPointerException.class, () -> spy.size()); // will throw the exception

        when(listMock.size()).thenCallRealMethod();
        assertEquals(1, listMock.size());

        doAnswer(invocation -> "Always the same").when(listMock).get(anyInt());
        String element = listMock.get(1);
        assertEquals("Always the same", element);
    }

}
