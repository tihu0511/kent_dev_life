/*
	* Generated by MybatisGenerator on 2016-07-15 10:50:34
*/
package org.jigang.entity;

import java.util.Date;

/** 
 * entity for table test.coupon_type
 */
public class CouponType {
	private Integer id;

	private Integer merchantId;

	private String name;

	private String icon;

	private Integer totalNum;

	private Integer restNum;

	private Integer isImmediate;

	private String resetInterval;

	private Date lastResetTime;

	private Integer version;

	private Integer state;

	private Date createTime;

	private Date updateTime;

	private Integer type;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getRestNum() {
		return this.restNum;
	}

	public void setRestNum(Integer restNum) {
		this.restNum = restNum;
	}

	public Integer getIsImmediate() {
		return this.isImmediate;
	}

	public void setIsImmediate(Integer isImmediate) {
		this.isImmediate = isImmediate;
	}

	public String getResetInterval() {
		return this.resetInterval;
	}

	public void setResetInterval(String resetInterval) {
		this.resetInterval = resetInterval;
	}

	public Date getLastResetTime() {
		return this.lastResetTime;
	}

	public void setLastResetTime(Date lastResetTime) {
		this.lastResetTime = lastResetTime;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public String toString() {
		return "id = " + this.id + ", merchantId = " + this.merchantId
			+ ", name = " + this.name + ", icon = " + this.icon
			+ ", totalNum = " + this.totalNum + ", restNum = " + this.restNum
			+ ", isImmediate = " + this.isImmediate + ", resetInterval = " + this.resetInterval
			+ ", lastResetTime = " + this.lastResetTime + ", version = " + this.version
			+ ", state = " + this.state + ", createTime = " + this.createTime
			+ ", updateTime = " + this.updateTime + ", type = " + this.type;
	}
}