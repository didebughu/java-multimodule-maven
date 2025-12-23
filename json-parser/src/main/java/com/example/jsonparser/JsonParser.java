package com.example.jsonparser;

import com.example.jsonparser.model.CalculationData;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * JSON解析器类
 * 提供解析JSON文件并转换为CalculationData对象的功能
 */
public class JsonParser {
    
    private final ObjectMapper objectMapper;
    
    /**
     * 默认构造函数
     */
    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }
    
    /**
     * 从JSON文件解析计算数据
     * @param filePath JSON文件路径
     * @return 解析后的CalculationData对象
     * @throws IOException 如果文件读取或解析失败
     */
    public CalculationData parseCalculationData(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("文件不存在: " + filePath);
        }
        
        return objectMapper.readValue(file, CalculationData.class);
    }
    
    /**
     * 从JSON字符串解析计算数据
     * @param jsonString JSON格式的字符串
     * @return 解析后的CalculationData对象
     * @throws IOException 如果JSON字符串解析失败
     */
    public CalculationData parseCalculationDataFromString(String jsonString) throws IOException {
        return objectMapper.readValue(jsonString, CalculationData.class);
    }
    
    /**
     * 将CalculationData对象转换为JSON字符串
     * @param data CalculationData对象
     * @return JSON格式的字符串
     * @throws IOException 如果序列化失败
     */
    public String toJsonString(CalculationData data) throws IOException {
        return objectMapper.writeValueAsString(data);
    }
}