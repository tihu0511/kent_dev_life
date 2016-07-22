package org.jigang.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * Created by wujigang on 16/7/12.
 */
public class TestFilter implements Filter {

    private IFilterDeal filterDeal;

    public void setFilterDeal(IFilterDeal filterDeal) {
        this.filterDeal = filterDeal;
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        filterDeal.deal();

        Result result = invoker.invoke(invocation);

        return result;
    }
}
