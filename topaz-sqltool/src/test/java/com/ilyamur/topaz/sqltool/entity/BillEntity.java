package com.ilyamur.topaz.sqltool.entity;

import com.ilyamur.topaz.sqltool.Mapper;
import com.ilyamur.topaz.sqltool.mapper.BillMapper;

import java.math.BigDecimal;

public class BillEntity {

    private int idBill;
    private int idAgree;
    private String billNo;
    private BigDecimal billSum;

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public void setIdAgree(int idAgree) {
        this.idAgree = idAgree;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public void setBillSum(BigDecimal billSum) {
        this.billSum = billSum;
    }

    public static Mapper<BillEntity> getMapper() {
        return new BillMapper(); // todo optimize
    }
}
