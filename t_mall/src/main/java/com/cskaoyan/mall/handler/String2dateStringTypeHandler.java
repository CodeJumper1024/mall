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
import java.util.Date;

@MappedTypes(String.class)
public class String2dateStringTypeHandler implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parseString2DateString(s));
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        //根据列名获得数据
        String string = resultSet.getString(s);
        //将获得的数据转换成目标类型
        return parseDateString2String(string);
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        //根据列名获得数据
        String string = resultSet.getString(i);
        //将获得的数据转换成目标类型
        return parseDateString2String(string);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        //根据列名获得数据
        String string = callableStatement.getString(i);
        //将获得的数据转换成目标类型
        return parseDateString2String(string);
    }

    private String parseDateString2String(String s) {
        String replace = s.replace(" ", "T");
        StringBuffer stringBuffer = new StringBuffer(replace);
        stringBuffer.append(".000+0000");
        return stringBuffer.toString();
    }
    private String parseString2DateString(String s) {
        String newString = s.replace("T", " ");
        newString = newString.replace(".000+0000", "");
        return newString;
    }
}
