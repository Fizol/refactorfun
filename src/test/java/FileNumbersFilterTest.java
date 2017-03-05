import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileNumbersFilter.class})
public class FileNumbersFilterTest {

    private FileNumbersFilter fileNumbersFilter;
    private FileInputStream is;
    private FileOutputStream os;
    private OutputStreamWriter streamWriter;
    private InputStreamReader streamReader;
    private BufferedReader bufferedReader;
    private BufferedWriter writer;

    @Before
    public void setUp() {
        fileNumbersFilter = new FileNumbersFilter();
        is = mock(FileInputStream.class);
        os = mock(FileOutputStream.class);
        streamWriter = mock(OutputStreamWriter.class);
        streamReader = mock(InputStreamReader.class);
        bufferedReader = mock(BufferedReader.class);
        writer = mock(BufferedWriter.class);
    }

    @Test
    public void howActuallyItIsTested() throws Exception {

        String inputPath = "inputPath";
        String outputPath = "outputPath";
        whenNew(FileInputStream.class).withArguments(inputPath).thenReturn(is);
        whenNew(InputStreamReader.class).withArguments(is).thenReturn(streamReader);
        whenNew(BufferedReader.class).withAnyArguments().thenReturn(bufferedReader);
        whenNew(FileOutputStream.class).withArguments(outputPath).thenReturn(os);
        whenNew(OutputStreamWriter.class).withArguments(os).thenReturn(streamWriter);
        whenNew(BufferedWriter.class).withArguments(streamWriter).thenReturn(writer);

        when(bufferedReader.readLine()).thenReturn("1")
                                       .thenReturn("2")
                                       .thenReturn(null);

        fileNumbersFilter.filterToFile(inputPath, outputPath, 0);

        verify(writer).write(new Double("1").toString());
        verify(writer).write(new Double("2").toString());
        verify(writer).close();
        verify(bufferedReader).close();
    }
    /*
    Za pomocą tego nie możliwe jest pisanie w TDD - nie da tak zacząć pisać testu, nie można za pomocą TDD dojść
    do takiego czegoś
    Bardzo dużo czasu mi pochłonęło aby zweryfikować czy test działa, bo się wywalał
    Bardzo dużeo czasu mi zajeło przygotowanie testu i danych testowych - to jest męczące wręcz
     */
}