package org.jigang.filter.impl;

import org.jigang.filter.IFilterDeal;
import org.springframework.stereotype.Component;

/**
 * Created by wujigang on 16/7/12.
 */
@Component("filterDeal")
public class FilterDealImpl implements IFilterDeal {
    public void deal() {
        System.out.println("Deal in dubbo filter.");
    }
}
