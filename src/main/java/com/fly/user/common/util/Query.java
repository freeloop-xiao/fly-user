package com.fly.user.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description: 数据库查询类
 * @author : free loop
 * @since 2019-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Query<T> extends Page<T> {

    /**
     * 传入的页码
     */
    private static final String PAGE = "page";

    /**
     * 单页记录条数
     */
    private static final String LIMIT = "limit";

    /**
     * 升序排列字段List<String>
     */
    private static final String IS_ASC = "isAsc";

    /**
     * 降序排列字段List<String>
     */
    private static final String IS_DESC = "isDesc";

    public Query(Map<Object, Object> params, Set<String> conditionSet) {

        super(Integer.parseInt(params.getOrDefault(PAGE, 1).toString())
                , Integer.parseInt(params.getOrDefault(LIMIT, 10).toString()));
        params.remove(PAGE);
        params.remove(LIMIT);
        params = FormatParam.toUnderline(params);
        Map<Object,Object> condition = new HashMap<>();
        for (String key:conditionSet){
            condition.put(key, params.get(key));
        }
        this.setCondition(condition);
    }


    /**
     * 查询参数（ 不会传入到 xml 层，这里是 Controller 层与 service 层传递参数预留 ）
     */
    private Map<Object, Object> condition;

    @Override
    public Map<Object, Object> condition() {
        return this.condition;
    }

    public void setCondition(Map<Object, Object> condition) {
        this.condition = condition;
    }

    /**
     * 条件转换为对外wrapper
     */
    public QueryWrapper<T> queryWrapper(){
        return PageUtil.mapToQueryWrapper(this.condition);
    }
}
