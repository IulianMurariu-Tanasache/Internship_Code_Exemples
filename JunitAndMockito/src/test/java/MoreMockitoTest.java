import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class MoreMockitoTest {

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
        when(flowerServiceMock.analyze(anyString())).thenReturn("Flower");

        // when(flowerServiceMock.isABigFlower("poppy", anyInt())).thenReturn(true);
        when(flowerServiceMock.isABigFlower(eq("poppy"), anyInt())).thenReturn(true);
        flowerServiceMock.analyze("poppy");
        verify(flowerServiceMock).analyze(or (eq("poppy"), endsWith("y")));
    }

}
