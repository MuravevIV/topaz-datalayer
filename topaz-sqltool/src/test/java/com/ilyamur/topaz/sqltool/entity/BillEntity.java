package com.ilyamur.topaz.sqltool.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.ilyamur.topaz.sqltool.Mapper;
import com.ilyamur.topaz.sqltool.mapper.BillMapper;

import java.math.BigDecimal;

public class BillEntity {

    private int idBill;
    private int idAgree;
    private String billNo;
    private BigDecimal billSum;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdAgree() {
        return idAgree;
    }

    public void setIdAgree(int idAgree) {
        this.idAgree = idAgree;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getBillSum() {
        return billSum;
    }

    public void setBillSum(BigDecimal billSum) {
        this.billSum = billSum;
    }

    public static Mapper<BillEntity> getMapper() {
        return new BillMapper(); // todo optimize
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillEntity that = (BillEntity) o;
        return idBill == that.idBill &&
                idAgree == that.idAgree &&
                Objects.equal(billNo, that.billNo) &&
                Objects.equal(billSum, that.billSum);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idBill, idAgree, billNo, billSum);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("idBill", idBill)
                .add("idAgree", idAgree)
                .add("billNo", billNo)
                .add("billSum", billSum)
                .toString();
    }
}
