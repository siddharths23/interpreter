package tests.requirement_3;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import interpreter.ByteCodeLoader;
import interpreter.CodeTable;
import interpreter.Program;
import interpreter.bytecode.*;

public class ByteCodeLoaderTest {
    @BeforeEach
    public void setUp() {
        CodeTable.init();
    }
    
    @ParameterizedTest
    @MethodSource("provideByteCodeFiles")
    public void testLoadingCodes(String fileContent, Class<?> expectedClass) throws Exception {
        String path = createFile(fileContent);

        Program program = (new ByteCodeLoader(path)).loadCodes();
        assertEquals(String.format("Testing generated class for [%s]", fileContent), program.getCode(0).getClass(), expectedClass);
    }

    private String createFile(String fileContent) throws Exception{
        String[] parts = fileContent.split(" ");
        Path temp = Files.createTempFile(parts[0], ".x.cod");
        String absolutePath = temp.toString();

        BufferedWriter writer = new BufferedWriter(new FileWriter(absolutePath));
        writer.write(fileContent);
        writer.close();

        return absolutePath;
    }

    private static Stream<Arguments> provideByteCodeFiles() {
        return Stream.of(
            Arguments.of("HALT", HaltCode.class),
            Arguments.of("POP 5", PopCode.class),
            Arguments.of("FALSEBRANCH f<<3>>", FalsebranchCode.class),
            Arguments.of("GOTO f<<3>>", GotoCode.class),
            Arguments.of("STORE 2 i", StoreCode.class),
            Arguments.of("LOAD 3 j", LoadCode.class),
            Arguments.of("LIT 5", LitCode.class),
            Arguments.of("LIT 0 z", LitCode.class),
            Arguments.of("ARGS 2", ArgsCode.class),
            Arguments.of("CALL Read", CallCode.class),
            Arguments.of("CALL f<<9>>", CallCode.class),
            Arguments.of("RETURN xyz<<4>>", ReturnCode.class),
            Arguments.of("RETURN", ReturnCode.class),
            Arguments.of("BOP +", BopCode.class),
            Arguments.of("WRITE", WriteCode.class),
            Arguments.of("LABEL Read", LabelCode.class),
            Arguments.of("LABEL dfg<<42>>", LabelCode.class)
        );
    }
}
