package com.example.calculator;

import com.example.jsonparser.model.CalculationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Calculator类的单元测试
 */
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    void testCalculateSum() {
        // 准备测试数据
        CalculationData data = new CalculationData(
            new double[]{10.0, 20.0, 30.0},
            "sum",
            "计算总和"
        );
        
        // 执行计算
        double result = calculator.calculate(data);
        
        // 验证结果
        assertEquals(60.0, result, 0.001);
    }
    
    @Test
    void testCalculateAverage() {
        CalculationData data = new CalculationData(
            new double[]{5.0, 10.0, 15.0},
            "average",
            "计算平均值"
        );
        
        double result = calculator.calculate(data);
        assertEquals(10.0, result, 0.001);
    }
    
    @Test
    void testCalculateMultiply() {
        CalculationData data = new CalculationData(
            new double[]{2.0, 3.0, 4.0},
            "multiply",
            "计算乘积"
        );
        
        double result = calculator.calculate(data);
        assertEquals(24.0, result, 0.001);
    }
    
    @Test
    void testCalculateMax() {
        CalculationData data = new CalculationData(
            new double[]{8.0, 15.0, 3.0, 20.0},
            "max",
            "找出最大值"
        );
        
        double result = calculator.calculate(data);
        assertEquals(20.0, result, 0.001);
    }
    
    @Test
    void testCalculateMin() {
        CalculationData data = new CalculationData(
            new double[]{8.0, 15.0, 3.0, 20.0},
            "min",
            "找出最小值"
        );
        
        double result = calculator.calculate(data);
        assertEquals(3.0, result, 0.001);
    }
    
    @Test
    void testCalculateWithUnsupportedOperation() {
        CalculationData data = new CalculationData(
            new double[]{1.0, 2.0},
            "divide",
            "不支持的操作"
        );
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(data);
        });
        
        assertTrue(exception.getMessage().contains("不支持的操作类型"));
    }
    
    @Test
    void testCalculateWithNullData() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(null);
        });
        
        assertEquals("计算数据不能为null", exception.getMessage());
    }
    
    @Test
    void testCalculateWithEmptyNumbers() {
        CalculationData data = new CalculationData(
            new double[]{},
            "sum",
            "空数组测试"
        );
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(data);
        });
        
        assertEquals("数字数组不能为空", exception.getMessage());
    }
    
    @Test
    void testSum() {
        double[] numbers = {1.5, 2.5, 3.5};
        double result = calculator.sum(numbers);
        assertEquals(7.5, result, 0.001);
    }
    
    @Test
    void testAverage() {
        double[] numbers = {10.0, 20.0, 30.0};
        double result = calculator.average(numbers);
        assertEquals(20.0, result, 0.001);
    }
    
    @Test
    void testAverageWithEmptyArray() {
        double[] numbers = {};
        double result = calculator.average(numbers);
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    void testMultiply() {
        double[] numbers = {2.0, 3.0, 4.0};
        double result = calculator.multiply(numbers);
        assertEquals(24.0, result, 0.001);
    }
    
    @Test
    void testMultiplyWithEmptyArray() {
        double[] numbers = {};
        double result = calculator.multiply(numbers);
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    void testMax() {
        double[] numbers = {5.0, 8.0, 3.0, 10.0};
        double result = calculator.max(numbers);
        assertEquals(10.0, result, 0.001);
    }
    
    @Test
    void testMaxWithEmptyArray() {
        double[] numbers = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.max(numbers);
        });
        
        assertEquals("数字数组不能为空", exception.getMessage());
    }
    
    @Test
    void testMin() {
        double[] numbers = {5.0, 8.0, 3.0, 10.0};
        double result = calculator.min(numbers);
        assertEquals(3.0, result, 0.001);
    }
    
    @Test
    void testMinWithEmptyArray() {
        double[] numbers = {};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.min(numbers);
        });
        
        assertEquals("数字数组不能为空", exception.getMessage());
    }
    
    @Test
    void testStandardDeviation() {
        double[] numbers = {2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0};
        double result = calculator.standardDeviation(numbers);
        assertEquals(2.0, result, 0.1); // 标准差约为2.0
    }
    
    @Test
    void testStandardDeviationWithEmptyArray() {
        double[] numbers = {};
        double result = calculator.standardDeviation(numbers);
        assertEquals(0.0, result, 0.001);
    }
    
    @Test
    void testCaseInsensitiveOperation() {
        CalculationData data = new CalculationData(
            new double[]{1.0, 2.0, 3.0},
            "SUM", // 大写测试
            "大小写不敏感测试"
        );
        
        double result = calculator.calculate(data);
        assertEquals(6.0, result, 0.001);
    }
}