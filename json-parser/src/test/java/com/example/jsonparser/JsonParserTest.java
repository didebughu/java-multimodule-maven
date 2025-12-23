package com.example.jsonparser;

import com.example.jsonparser.model.CalculationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JsonParser类的单元测试
 */
class JsonParserTest {
    
    private JsonParser jsonParser;
    
    @BeforeEach
    void setUp() {
        jsonParser = new JsonParser();
    }
    
    @Test
    void testParseCalculationDataFromString() throws IOException {
        // 准备测试数据
        String jsonString = """
        {
            "numbers": [10.5, 20.3, 30.7],
            "operation": "sum",
            "description": "计算三个数的总和"
        }
        """;
        
        // 执行解析
        CalculationData result = jsonParser.parseCalculationDataFromString(jsonString);
        
        // 验证结果
        assertNotNull(result);
        assertArrayEquals(new double[]{10.5, 20.3, 30.7}, result.getNumbers(), 0.001);
        assertEquals("sum", result.getOperation());
        assertEquals("计算三个数的总和", result.getDescription());
    }
    
    @Test
    void testParseCalculationDataFromFile(@TempDir Path tempDir) throws IOException {
        // 创建临时JSON文件
        File tempFile = tempDir.resolve("test_data.json").toFile();
        String jsonContent = """
        {
            "numbers": [5.0, 3.0],
            "operation": "multiply",
            "description": "计算两个数的乘积"
        }
        """;
        
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(jsonContent);
        }
        
        // 执行解析
        CalculationData result = jsonParser.parseCalculationData(tempFile.getAbsolutePath());
        
        // 验证结果
        assertNotNull(result);
        assertArrayEquals(new double[]{5.0, 3.0}, result.getNumbers(), 0.001);
        assertEquals("multiply", result.getOperation());
        assertEquals("计算两个数的乘积", result.getDescription());
    }
    
    @Test
    void testParseCalculationDataFileNotFound() {
        // 测试文件不存在的情况
        String nonExistentPath = "/nonexistent/path/data.json";
        
        IOException exception = assertThrows(IOException.class, () -> {
            jsonParser.parseCalculationData(nonExistentPath);
        });
        
        assertTrue(exception.getMessage().contains("文件不存在"));
    }
    
    @Test
    void testToJsonString() throws IOException {
        // 准备测试数据
        CalculationData data = new CalculationData(
            new double[]{1.0, 2.0, 3.0},
            "average",
            "计算平均值"
        );
        
        // 执行序列化
        String jsonString = jsonParser.toJsonString(data);
        
        // 验证结果
        assertNotNull(jsonString);
        assertTrue(jsonString.contains("\"numbers\":[1.0,2.0,3.0]"));
        assertTrue(jsonString.contains("\"operation\":\"average\""));
        assertTrue(jsonString.contains("\"description\":\"计算平均值\""));
    }
    
    @Test
    void testRoundTripSerialization() throws IOException {
        // 测试序列化和反序列化的往返过程
        CalculationData originalData = new CalculationData(
            new double[]{15.5, 25.5},
            "subtract",
            "计算两个数的差值"
        );
        
        // 序列化
        String jsonString = jsonParser.toJsonString(originalData);
        
        // 反序列化
        CalculationData deserializedData = jsonParser.parseCalculationDataFromString(jsonString);
        
        // 验证数据一致性
        assertArrayEquals(originalData.getNumbers(), deserializedData.getNumbers(), 0.001);
        assertEquals(originalData.getOperation(), deserializedData.getOperation());
        assertEquals(originalData.getDescription(), deserializedData.getDescription());
    }
}