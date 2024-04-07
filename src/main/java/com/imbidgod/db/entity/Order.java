package com.imbidgod.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "訂單主檔 Order table 資料")
@Data
@Entity
@Table(name = "order_m"
)
public class Order implements Serializable {

    // 單向一對多, Order 可找到  orderDetail,  orderDetail 找不到 Order
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    @OrderBy("createDate DESC")
    @JsonIgnore
    protected Set<OrderDetail> orderDetails = new HashSet<>();

    @Transient
    @ApiModelProperty(required = true, value = "訂貨人ID")
    protected long ordererId;

    public long getOrdererId() {
        return getMember() != null ? getMember().getId() : ordererId;
    }

    //這會自動在 本 table 產生 orderer_id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordererId")
    @JsonIgnore
    protected Member member;

    @Id
    @ApiModelProperty(required = true, value = "訂單ID")
    @Column(unique = true, nullable = false, length = 30)
    protected String id;

    @ApiModelProperty( value = "訂單總金額", readOnly = true)
    @Column( columnDefinition = "double(15, 2)")
    protected double totalOrderPrice;

    @ApiModelProperty( value = "訂單實際支付金額", readOnly = true)
    @Column( columnDefinition = "double(15, 2)")
    protected double totalOrderActualPrice;

    @ApiModelProperty(required = true, value = "收件者姓名")
    @Column(nullable = false, length = 100)
    protected String receiverName;

    @ApiModelProperty(required = true, value = "收件者地址")
    @Column(nullable = false, length = 500)
    protected String receiverAddress;

    @ApiModelProperty(required = true, value = "收件者電話")
    @Column(nullable = false, length = 500)
    protected String receiverPhone;

    @ApiModelProperty(required = false, value = "訂單備註")
    @Column(columnDefinition = "TEXT")
    protected String orderDesc;

    @ApiModelProperty(required = true, value = "訂單建立方式", notes = "1=Web/2=APP")
    @Column(nullable = false, length = 5)
    protected int createMethod;

    @ApiModelProperty(required = true, value = "訂單狀態", notes = "10=在購物車裡/20=填寫訂單资料/30=填寫信用卡資料/40付款失敗/50=已出貨/60=已送達")
    @Column(nullable = false, length = 5)
    protected int status;

    @ApiModelProperty(required = false, value = "訂單折扣金額")
    @Column( length = 10)
    protected double discountPrice;

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
