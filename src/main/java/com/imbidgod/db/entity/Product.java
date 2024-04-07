package com.imbidgod.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "產品 Product table 資料")
@Data
@Entity
@Table(name = "product"
)
public class Product implements Serializable {

    @Id
    @ApiModelProperty(required = true, value = "產品ID")
    @Column(unique = true, nullable = false, length = 30)
    protected String id;

    @ApiModelProperty(required = true, value = "產品名称")
    @Column(nullable = false, length = 100)
    protected String name;

    @ApiModelProperty(required = false, value = "系列產品ID")
    @Column( length = 30)
    protected String serialProductId;

    @ApiModelProperty(required = true, value = "產品小分類ID", notes = "link to ProductClass")
    @Column(nullable = false)
    protected Long productClassId;

    @ApiModelProperty(required = true, value = "供應商ID", notes = "link to Vendor")
    @Column(nullable = false)
    protected Long vendorId;

    @ApiModelProperty(required = false, value = "外包裝呎吋单位")
    @Column(length = 50)
    protected String packageingSizeUnit;

    @ApiModelProperty(required = false, value = "外包裝呎吋")
    @Column(length = 10)
    protected int packageingSizeLength;

    @ApiModelProperty(required = false, value = "外包裝呎吋")
    @Column(length = 10)
    protected int packageingSizeWidth;

    @ApiModelProperty(required = false, value = "外包裝呎吋")
    @Column(length = 10)
    protected int packageingSizeHight;


    @ApiModelProperty(required = true, value = "販售单位")
    @Column(nullable = false, length = 50)
    protected String onSaleUnit;

    @ApiModelProperty(required = true, value = "庫存")
    @Column(nullable = false, length = 5)
    protected int inStoreCount;

    @ApiModelProperty(required = true, value = "安全庫存")
    @Column(nullable = false, length = 5)
    protected int safetyCount;

    @ApiModelProperty(required = true, value = "价格")
    @Column(nullable = false, columnDefinition = "double(15, 2)")
    protected double price;

    @ApiModelProperty(required = true, value = "成本")
    @Column(nullable = false, columnDefinition = "double(15, 2)")
    protected double cost;

    @ApiModelProperty(required = false, value = "產品備註")
    @Column(columnDefinition = "TEXT")
    protected String productDesc;

    @ApiModelProperty(required = true, value = "上下架", notes = "負值->下架/其他->上架")
    @Column(nullable = false, length = 5)
    protected int isActive;

    @ApiModelProperty(required = false, value = "貨幣")
    @Column(length = 5)
    protected String currency;

    @ApiModelProperty(required = false, value = "屬性")
    @Column(length = 200)
    protected String propertyName1;

    @ApiModelProperty(required = false, value = "屬性值")
    @Column(length = 200)
    protected String propertyValue1;

    @ApiModelProperty(required = false, value = "屬性")
    @Column(length = 200)
    protected String propertyName2;

    @ApiModelProperty(required = false, value = "屬性值")
    @Column(length = 200)
    protected String propertyValue2;

    @ApiModelProperty(required = false, value = "屬性")
    @Column(length = 200)
    protected String propertyName3;

    @ApiModelProperty(required = false, value = "屬性值")
    @Column(length = 200)
    protected String propertyValue3;

    @ApiModelProperty(required = false, value = "屬性")
    @Column(length = 200)
    protected String propertyName4;

    @ApiModelProperty(required = false, value = "屬性值")
    @Column(length = 200)
    protected String propertyValue4;

    @ApiModelProperty(required = false, value = "屬性")
    @Column(length = 200)
    protected String propertyName5;

    @ApiModelProperty(required = false, value = "屬性值")
    @Column(length = 200)
    protected String propertyValue5;

    @ApiModelProperty(required = true, value = "誰建立")
    @Column(nullable = false, length = 50)
    protected String createBy;

    @ApiModelProperty(value = "建立記錄日期", required = true, readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Date createDate;

    @ApiModelProperty(required = false, value = "誰更新")
    @Column(length = 50)
    protected String updateBy;

    @ApiModelProperty(value = "更新記錄日期", readOnly = true, required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, insertable = false, columnDefinition = "TIMESTAMP  DEFAULT NULL")
    protected Date updateDate;

}