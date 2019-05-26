package com.dongweima.component.spring.data.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 需要实现AuditorAware该接口,获取userId
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseDO implements Serializable {

  private static final long serialVersionUID = -5605028230891280779L;
  @Id
  @GeneratedValue
  private long id;

  /**
   * 创建人
   */
  @CreatedBy
  @Column(name = "create_user", nullable = false, updatable = false)
  protected String createUser;

  /**
   * 修改人
   */
  @LastModifiedBy
  @Column(name = "modify_user", nullable = false)
  protected String modifyUser;

  /**
   * 创建时间
   */
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "gmt_create", nullable = false, updatable = false)
  protected Date gmtCreate;

  /**
   * 修改时间
   */
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  @Column(name = "gmt_modify", nullable = false)
  protected Date gmtModify;

  /**
   * 是否删除 1:删除；0：正常
   */
  @Column(nullable = false, columnDefinition = "smallint(6) default 0")
  protected Integer isDelete = 0;

}
