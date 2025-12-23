package com.example.jsonparser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 计算数据模型类
 * 用于存储从JSON文件中解析的计算数据
 */
public class CalculationData {
    
    @JsonProperty("numbers")
    private double[] numbers;
    
    @JsonProperty("operation")
    private String operation;
    
    @JsonProperty("description")
    private String description;
    
    // 默认构造函数
    public CalculationData() {}
    
    // 带参数构造函数
    public CalculationData(double[] numbers, String operation, String description) {
        this.numbers = numbers;
        this.operation = operation;
        this.description = description;
    }
    
    // Getter和Setter方法
    public double[] getNumbers() {
        return numbers;
    }
    
    public void setNumbers(double[] numbers) {
        this.numbers = numbers;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "CalculationData{" +
                "numbers=" + java.util.Arrays.toString(numbers) +
                ", operation='" + operation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}