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
import java.util.HashSet;
import java.util.Set;

@ApiModel(description = "活動(競標)主檔 Activity table 資料")
@Data
@Entity
@Table(name = "activity",
       indexes = {
               @Index(name = "activity_idx_1",  columnList="isActive, canBid", unique = false)
        }
)
public class Activity implements Serializable {

    // 單向一對多, Activity 可找到  ActivityDetail,  ActivityDetail 找不到 Activity
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId")
    @OrderBy("createDate DESC")
    @JsonIgnore
    protected Set<ActivityDetail> eventDetails = new HashSet<>();

    // 單向一對多, Activity 可找到  StaticActivityJoingBid,  StaticActivityJoingBid 找不到 Activity
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId")
    @OrderBy("staticLevel ASC")
    @JsonIgnore
    protected Set<StatisActivity> StaticActivitys = new HashSet<>();


    //---------------------------------------------------
    @Transient
    @ApiModelProperty( value = "競標產品ID")
    protected String bidProductId;

    public String getBidProductId(){
        return bidProductId != null ? bidProductId : product != null ? product.getId() : null;
    }

    // 單向一對一
    //這會自動在 本 table 產生 bid_product_id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidProductId")
    @JsonIgnore
    protected Product product;
    //---------------------------------------------------

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="本活動主檔ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "本活動名称")
    @Column(nullable = false, length = 200)
    protected String name;

    @ApiModelProperty(required = true, value = "本活動是否有效")
    @Column(nullable = false, length = 2)
    protected int isActive;

    @ApiModelProperty(required = true, value = "本活動最大競標代幣額")
    @Column(nullable = false, length = 10)
    protected int maxBidCoin;

    @ApiModelProperty(required = true, value = "本活動最小競標代幣額")
    @Column(nullable = false, length = 10)
    protected int minBidCoin;

    @ApiModelProperty(required = true, value = "當coin達到此數量, 競標活動停止")
    @Column(nullable = false, length = 10)
    protected int stopBidCoinCount;

    @ApiModelProperty(required = true, value = "若沒標成功,返還bid coin百分比")
    @Column(nullable = false, length = 10)
    protected int returnBidPercentage;

    @ApiModelProperty( readOnly = true, value = "本活動總參與人數")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int joinTotalMemberCount;

    @ApiModelProperty( readOnly = true, value = "本活動總競標代幣量")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int joinTotalCoinCount;

    @ApiModelProperty( readOnly = true, value = "返還沒標成功bid coin總代幣量")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int totalReturnBidCount;

    @ApiModelProperty( required = true, value = "是否能競標, 0=不能競標/1=能競標")
    @Column( nullable = false,length = 2, columnDefinition = "integer DEFAULT 1")
    protected int canBid;

    @ApiModelProperty( required = true, value = "統計競標級距")
    @Column( nullable = false,length = 5, columnDefinition = "integer DEFAULT 1")
    protected int statisLevel;

    @ApiModelProperty(value = "本活動下注開始日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Date bidStartDate;

    @ApiModelProperty(value = "本活動下注結束日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Date bidEndDate;

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
