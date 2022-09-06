package cn.doitedu.rtmk.common.interfaces;

import cn.doitedu.rtmk.common.pojo.UserEvent;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.util.Collector;
import org.roaringbitmap.RoaringBitmap;
import redis.clients.jedis.Jedis;

/**
 * @Author: deep as the sea
 * @Site: <a href="www.51doit.com">多易教育</a>
 * @QQ: 657270652
 * @Date: 2022/8/19
 * @Desc: 规则运算机的统一接口
 **/
public interface RuleCalculator {


    /**
     * 规则运算机的初始化方法
     * @param jedis redis客户端
     * @param ruleDefineParamJsonObject 规则定义参数整体json
     * @param profileUserBitmap 人群画像bitmap
     * @param out flink的结果输出器
     */
    void init(Jedis jedis, JSONObject ruleDefineParamJsonObject, RoaringBitmap profileUserBitmap, Collector<JSONObject> out);


    /**
     * 对输入事件进行规则处理的入口方法
     * @param userEvent 输入的用户行为事件
     */
    void process(UserEvent userEvent);


    /**
     * 规则条件运算逻辑
     * @param userEvent  用户事件
     */
    void calc(UserEvent userEvent);

    /**
     * 规则条件是否满足的判断逻辑
     * @param guid 用户标识
     * @return  是否满足
     */
    boolean isMatch(int guid);
}
