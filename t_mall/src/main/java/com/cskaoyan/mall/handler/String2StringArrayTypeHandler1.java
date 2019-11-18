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
import java.util.Arrays;

@MappedTypes(String[].class)
public class String2StringArrayTypeHandler1 implements TypeHandler<String[]>  {
    //输入映射
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, String[] strings, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(index,parseStringArray2String(strings));
    }

    //输出映射
    @Override
    public String[] getResult(ResultSet resultSet, String columnName) throws SQLException {
        String string = resultSet.getString(columnName);
        return parseString2StringArray(string);
    }

    @Override
    public String[] getResult(ResultSet resultSet, int index) throws SQLException {
        String string = resultSet.getString(index);
        return parseString2StringArray(string);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return parseString2StringArray(string);
    }

    //把String数组 转换为 String类型
    private String parseStringArray2String(String[] strings){
        String s = Arrays.toString(strings);
        return s;
    }

    //String类型 转换为 String数组
    private String[] parseString2StringArray(String string){
        String s = string.replaceAll("\"", "").replaceAll("\\[","").replaceAll("]","");
        String[] strings = s.split(",");
        return strings;
    }

}
