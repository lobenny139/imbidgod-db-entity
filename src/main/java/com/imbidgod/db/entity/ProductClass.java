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

@ApiModel(description = "產品小分類 ProductClass table 資料")
@Data
@Entity
@Table(name = "product_class"
)
public class ProductClass implements Serializable {

    // 單向一對多, ProductClass 可找到 這 Product,  Product 找不到 ProductClass
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="productClassId")
    @OrderBy("createDate DESC")
    @Where(clause = "is_active=1")
    @JsonIgnore
    protected Set<Product> products = new HashSet<>();

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="產品分類ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "產品大分類ID", notes = "link to MainClass")
    @Column(nullable = false)
    protected Long mainClassId;

    @ApiModelProperty(required = true, value = "產品小分類名称")
    @Column(nullable = false, length = 200, unique = true)
    protected String name;

    @ApiModelProperty(required = true, value = "是否有效, 負值->否/其他->是")
    @Column(nullable = false, length = 2)
    protected int isActive;

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
