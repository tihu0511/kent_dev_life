package org.jigang.dao;

import org.apache.ibatis.annotations.Param;
import org.jigang.entity.Account;

public interface IAccountDao {
	Account queryById(@Param("id") Integer id);

	void add(Account account);

	void update(Account account);
}