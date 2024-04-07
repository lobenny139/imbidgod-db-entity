package com.imbidgod.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "活動(競標)統計檔 StaticActivityJoingBid table 資料")
@Data
@Entity
@Table(name = "statis_activity",
        indexes = {
                @Index(name = "statis_activity_idx_1",  columnList="activityId", unique = false)
        }
)
public class StatisActivity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="活動(競標)統計檔ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "活動(競標)ID", notes = "link to Activity")
    @Column(nullable = false)
    protected Long activityId;

    @ApiModelProperty( readOnly = true, value = "統計級別")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int statisLevel;

    @ApiModelProperty( readOnly = true, value = "統計級別起")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int statisStart;

    @ApiModelProperty( readOnly = true, value = "統計級別迄")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int statisEnd;

    @ApiModelProperty( readOnly = true, value = "參與人數")
    @Column( length = 10, columnDefinition = "integer DEFAULT 0")
    protected int joiningCount;

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
