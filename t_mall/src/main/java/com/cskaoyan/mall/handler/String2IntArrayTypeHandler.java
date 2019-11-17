package com.cskaoyan.mall.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(int[].class)
public class String2IntArrayTypeHandler implements TypeHandler<int[]> {

    //输入映射
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, int[] ints, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(index,parseIntArray2String(ints));
    }

    //输出映射
    @Override
    public int[] getResult(ResultSet resultSet, String columnName) throws SQLException {
        //根据列名获得数据
        String string = resultSet.getString(columnName);
        //将获得的数据转换成目标类型
        return parseString2IntArray(string);
    }

    @Override
    public int[] getResult(ResultSet resultSet, int index) throws SQLException {
        String string = resultSet.getString(index);
        return parseString2IntArray(string);
    }

    @Override
    public int[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return parseString2IntArray(string);
    }


    //把int数组转换为String类型
    private String parseIntArray2String(int[] ints){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(ints);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //String类型 转换为 int数组
    private int[] parseString2IntArray(String string){
        ObjectMapper objectMapper = new ObjectMapper();
        int[] ints = new int[0];
        try {
            ints = objectMapper.readValue(string, int[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ints;
    }

}
