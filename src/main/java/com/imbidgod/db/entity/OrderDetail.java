package com.imbidgod.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "訂單明細表檔 OrderDetail table 資料")
@Data
@Entity
@Table(name = "order_d"
)
public class OrderDetail implements Serializable {

    // 單向一對一, OrderDetail 可找到  Product,  Product 找不到 OrderDetail
    //這會自動在 本 table 產生 product_id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @JsonIgnore
    protected Product product;

    @Transient
    @ApiModelProperty(value = "訂單明細產品ID")
    protected String productId;

    public String getProductId () {
            return getProduct() != null ? getProduct().getId() : productId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="訂單明細表ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "訂單ID" , notes = "link to Order")
    @Column(nullable = false, length = 30)
    protected String orderId;

    @ApiModelProperty(required = true, value = "訂單明細產品數量")
    @Column(nullable = false, length = 5)
    protected int productCount;

    @ApiModelProperty(required = false, value = "訂單明細產品折扣金額")
    @Column( length = 10)
    protected int productDiscountPrice;

    @ApiModelProperty(required = true, value = "訂單明細產品价格")
    @Column(nullable = false, columnDefinition = "double(15, 2)")
    protected double productPrice;

    @ApiModelProperty(required = false, value = "訂單明細產品折扣備註")
    @Column(columnDefinition = "TEXT")
    protected String productDiscountDesc;

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
