package tests.requirement_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import interpreter.CodeTable;
import tests.helpers.ByteCodes;

public class CodeTableTest {
    
    @BeforeEach
    public void setUp() {
        CodeTable.init();
    }

    @Test
    public void testUnknownCodeReturnsNull() {
        assertEquals(null, CodeTable.get("abalamahalamatandra"));
    }

    @ParameterizedTest
    @MethodSource("provideCodeTableMappings")
    public void testCodeTable(ByteCodes helperCode) {
        assertTrue(
            CodeTable.get(helperCode.getGeneratedCode()).contains(helperCode.getExpectedTableValue())
        );
    }

    private static Stream<ByteCodes> provideCodeTableMappings() {
        return Stream.of(ByteCodes.values());
    }
}
