package com.imbidgod.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "供應商 Vendor table 資料")
@Data
@Entity
@Table(name = "vendor")
public class Vendor implements Serializable {

    // 單向一對多, Vendor 可找到 這 Product,  Product 找不到 Vendor
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="vendorId")
    @OrderBy("createDate DESC")
    @Where(clause = "on_sale=1")
    @JsonIgnore
    protected Set<Product> products = new HashSet<>();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="供應商ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "是否有效")
    @Column(nullable = false, length = 2)
    protected int isActive;

    @ApiModelProperty(required = true, value = "供應商供應商名稱")
    @Column(nullable = false, length = 20)
    protected String name;

    @ApiModelProperty(required = false, value = "電話1")
    @Column(length = 20)
    protected String phone1;

    @ApiModelProperty(required = false, value = "電話2")
    @Column(length = 20)
    protected String phone2;

    @ApiModelProperty(required = false, value = "地址1")
    @Column(length = 200)
    protected String address1;

    @ApiModelProperty(required = false, value = "地址2")
    @Column(length = 200)
    protected String address2;

    @ApiModelProperty(required = false, value = "供應商備註")
    @Column(columnDefinition = "TEXT")
    protected String remark;

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
