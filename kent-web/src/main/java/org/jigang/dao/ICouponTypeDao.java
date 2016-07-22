package org.jigang.dao;

import org.apache.ibatis.annotations.Param;
import org.jigang.entity.CouponType;

public interface ICouponTypeDao {
	CouponType queryById(@Param("id") Integer id);

	void add(CouponType couponType);

	void update(CouponType couponType);
}