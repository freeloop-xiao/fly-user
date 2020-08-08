package com.fly.user.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.user.manage.pojo.dto.PageRequest;
import com.fly.user.manage.pojo.entity.SysRole;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 分页查询转换工具
 *
 * @author free loop
 * @version 1.0
 * @since 2019-10-12 12:36
 */

public class PageUtil {



    public static <T> Page<T> page(PageRequest request){
        Page<T> page = new Page<>(request.getPage(),request.getLimit());
        if (StringUtils.hasText(request.getOrderAscBy())){
            page.addOrder(OrderItem.desc(FormatParam.toUnderline(request.getOrderAscBy())));
        }else if (StringUtils.hasText(request.getOrderDescBy())){
            page.addOrder(OrderItem.desc(FormatParam.toUnderline(request.getOrderDescBy())));
        }else {
            page.addOrder(OrderItem.desc("create_time"));
        }
        return page;
    }

    /**
     * Map转换为对应查询对象
     *
     * @param map
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> mapToQueryWrapper(Map<Object, Object> map) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (map == null){
            return null;
        }
        for (Object key : map.keySet()) {
            queryWrapper.eq((String) key, map.get(key));
        }
        return queryWrapper;
    }

}
