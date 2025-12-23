package com.example.calculator;

import com.example.jsonparser.model.CalculationData;

/**
 * 计算器类
 * 提供各种数学计算功能
 */
public class Calculator {
    
    /**
     * 根据CalculationData执行计算
     * @param data 包含数字和操作类型的数据
     * @return 计算结果
     * @throws IllegalArgumentException 如果操作类型不支持或数据无效
     */
    public double calculate(CalculationData data) {
        if (data == null) {
            throw new IllegalArgumentException("计算数据不能为null");
        }
        
        double[] numbers = data.getNumbers();
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("数字数组不能为空");
        }
        
        String operation = data.getOperation();
        if (operation == null || operation.trim().isEmpty()) {
            throw new IllegalArgumentException("操作类型不能为空");
        }
        
        return switch (operation.toLowerCase()) {
            case "sum" -> sum(numbers);
            case "average" -> average(numbers);
            case "multiply" -> multiply(numbers);
            case "max" -> max(numbers);
            case "min" -> min(numbers);
            default -> throw new IllegalArgumentException("不支持的操作类型: " + operation);
        };
    }
    
    /**
     * 计算数字总和
     * @param numbers 数字数组
     * @return 总和
     */
    public double sum(double[] numbers) {
        double result = 0.0;
        for (double num : numbers) {
            result += num;
        }
        return result;
    }
    
    /**
     * 计算数字平均值
     * @param numbers 数字数组
     * @return 平均值
     */
    public double average(double[] numbers) {
        if (numbers.length == 0) {
            return 0.0;
        }
        return sum(numbers) / numbers.length;
    }
    
    /**
     * 计算数字乘积
     * @param numbers 数字数组
     * @return 乘积
     */
    public double multiply(double[] numbers) {
        if (numbers.length == 0) {
            return 0.0;
        }
        
        double result = 1.0;
        for (double num : numbers) {
            result *= num;
        }
        return result;
    }
    
    /**
     * 找出最大值
     * @param numbers 数字数组
     * @return 最大值
     */
    public double max(double[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("数字数组不能为空");
        }
        
        double max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
    
    /**
     * 找出最小值
     * @param numbers 数字数组
     * @return 最小值
     */
    public double min(double[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("数字数组不能为空");
        }
        
        double min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
    
    /**
     * 计算标准差
     * @param numbers 数字数组
     * @return 标准差
     */
    public double standardDeviation(double[] numbers) {
        if (numbers.length == 0) {
            return 0.0;
        }
        
        double mean = average(numbers);
        double sumSquaredDifferences = 0.0;
        
        for (double num : numbers) {
            double difference = num - mean;
            sumSquaredDifferences += difference * difference;
        }
        
        double variance = sumSquaredDifferences / numbers.length;
        return Math.sqrt(variance);
    }
}