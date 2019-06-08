package com.ilyamur.topaz.sqltool.mapper;

import com.ilyamur.topaz.sqltool.Mapper;
import com.ilyamur.topaz.sqltool.entity.BillEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillMapper extends Mapper<BillEntity> {

    @Override
    public BillEntity applyMapper(ResultSet resultSet) throws SQLException {
        BillEntity billEntity = new BillEntity();
        billEntity.setIdAgree(resultSet.getInt("id_agree"));
        billEntity.setIdBill(resultSet.getInt("id_bill"));
        billEntity.setBillNo(resultSet.getString("bill_no"));
        billEntity.setBillSum(resultSet.getBigDecimal("bill_sum"));
        return billEntity;
    }
}
