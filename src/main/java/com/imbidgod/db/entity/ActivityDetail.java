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

@ApiModel(description = "活動明細表檔 EventDetail table 資料")
@Data
@Entity
@Table(name = "activity_detail",
       indexes = {
               @Index(name = "activity_detail_idx_1",  columnList="activityId", unique = false),
               @Index(name = "activity_detail_idx_2",  columnList="activityId, joinBidCoin, hasGetBid", unique = false)
       }
)
public class ActivityDetail implements Serializable {

    //單向一對一, 這會自動在 本 table 產生 join_member_id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joinMemberId")
    @JsonIgnore
    protected Member member;

    public long getJoinMemberId() {
            return getMember() != null ? getMember().getId() : joinMemberId;
    }

    @Transient
    protected long joinMemberId;
    // ---------------------------------------------------------

    @ApiModelProperty(required = true, value = "活動(競標)ID", notes = "link to Activity")
    @Column(nullable = false)
    protected Long activityId;
    // ---------------------------------------------------------

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="活動主檔ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "競標代幣")
    @Column(nullable = false, length = 10)
    protected int joinBidCoin;

    @ApiModelProperty( value = "如果沒競標成功,返還多少代幣", readOnly = true)
    @Column( length = 10)
    protected int returnBidCoin;

    @ApiModelProperty( value = "有得標嗎,0=沒得標/1=最低金額得標/100=最高金額得標", readOnly = true)
    @Column( length = 10)
    protected int hasGetBid;

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
    @Column(nullable = true, insertable = false, updatable = false, columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    protected Date updateDate;
}
