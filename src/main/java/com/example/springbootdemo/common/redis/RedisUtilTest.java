package com.example.springbootdemo.common.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisUtilTest {

//    @Autowired
//    private StringRedisTemplate redisTemplate;
    private JedisPool jedisPool;

    private Jedis jedis;
    public void set(String str){

    }

    public void  initJedisPoolConfig(JedisPoolConfig config){
        config.setMaxIdle(5);//最大Jedis实例个数  idle-空闲的Jedis 实例个数
        config.setMaxIdle(0);//最小Jedis实例个数
        config.setTestOnBorrow(true);//获取一个Jedis实例时是否检查链接可用性
        config.setTestOnReturn(true);//return一个Jedis实例时是否检查链接可用性
        config.setTestWhileIdle(true);//idle状态监测是否用异步线程evict进行检查  evict-回收jedis实例
        config.setNumTestsPerEvictionRun(10);//一次最多evict的pool里的jedis实例个数
        config.setTimeBetweenEvictionRunsMillis(60000);//test idle 的间隔时间
        config.setMaxWaitMillis(3000);//获取jedis实例的最大等待时间
        config.setBlockWhenExhausted(false);//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时,默认true
    }



    private JedisCluster jedisCluster;//jedis集群


    public void jedisClustertest(){
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(new HostAndPort("127.0.0.1",8000));
        hostAndPortSet.add(new HostAndPort("127.0.0.1",8000));
        hostAndPortSet.add(new HostAndPort("127.0.0.1",8000));
        jedisCluster = new JedisCluster(hostAndPortSet,new JedisPoolConfig());
    }


    //操作String
    private void openStr(){
        //添加一条数据
        jedisCluster.set("name","哈哈");
        //获取key的value
        String name = jedisCluster.get("name");
        //删除key
        jedisCluster.del("name");
    }

    //操作Hash
    private void openHash(){
        //添加一条数据
        jedisCluster.hset("keyName","fieldName","value");
        //获取数据
        String value = jedisCluster.hget("keyName","fieldName");
        Map<String,String> map = new HashMap<>();
        map.put("name","哈哈");
        map.put("age","23");
        //添加多条数据
        jedisCluster.hmset("hahaInfo",map);
        //获取多条
        List<String> nameList = jedisCluster.hmget("hahaInfo","name","age");//获取key的多个属性值
        //获取全部
        Map<String,String> vlueMap = jedisCluster.hgetAll("hahaInfo");
        //删除
        jedisCluster.hdel("hahaInfo");
    }

    //操作list
    private void openList(){
        //添加
        jedisCluster.lpush("user","哈哈","张山");
        //获取
        String resultStr = jedisCluster.rpop("user");
        //分页查询
        List<String> stringList = jedisCluster.lrange("user",0,3);
        //获取总条数
        Long total = jedisCluster.llen("user");
        //删除
        jedisCluster.lrem("user",1,"哈哈");
    }

    //操作Set
    private void openSet(){
        //添加
        jedisCluster.sadd("user","哈哈","hah","哈哈");
        //获取
        Set<String> stringSet = jedisCluster.smembers("user");
        //删除
        jedisCluster.srem("user","hah");
    }


    /*
    <!-- 连接池配置 -->

<bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">

<!-- 最大连接数 -->

<property name="maxTotal" value="30" />

<!-- 最大空闲连接数 -->

<property name="maxIdle" value="10" />

<!-- 每次释放连接的最大数目 -->

<property name="numTestsPerEvictionRun" value="1024" />

<!-- 释放连接的扫描间隔（毫秒） -->

<property name="timeBetweenEvictionRunsMillis" value="30000" />

<!-- 连接最小空闲时间 -->

<property name="minEvictableIdleTimeMillis" value="1800000" />

<!-- 连接空闲多久后释放, 当空闲时间>该值 且 空闲连接>最大空闲连接数 时直接释放 -->

<property name="softMinEvictableIdleTimeMillis" value="10000" />

<!-- 获取连接时的最大等待毫秒数,小于零:阻塞不确定的时间,默认-1 -->

<property name="maxWaitMillis" value="1500" />

<!-- 在获取连接的时候检查有效性, 默认false -->

<property name="testOnBorrow" value="true" />

<!-- 在空闲时检查有效性, 默认false -->

<property name="testWhileIdle" value="true" />

<!-- 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时,默认true -->

<property name="blockWhenExhausted" value="false" />

</bean>

<!-- redis集群 -->

<bean id="jedisCluster" class="redis.clients.jedis.JedisCluster">

<constructor-arg index="0">

<set>

<bean class="redis.clients.jedis.HostAndPort">

<constructor-arg index="0" value="192.168.101.3"></constructor-arg>

<constructor-arg index="1" value="7001"></constructor-arg>

</bean>

<bean class="redis.clients.jedis.HostAndPort">

<constructor-arg index="0" value="192.168.101.3"></constructor-arg>

<constructor-arg index="1" value="7002"></constructor-arg>

</bean>

<bean class="redis.clients.jedis.HostAndPort">

<constructor-arg index="0" value="192.168.101.3"></constructor-arg>

<constructor-arg index="1" value="7003"></constructor-arg>

</bean>

<bean class="redis.clients.jedis.HostAndPort">

<constructor-arg index="0" value="192.168.101.3"></constructor-arg>

<constructor-arg index="1" value="7004"></constructor-arg>

</bean>

<bean class="redis.clients.jedis.HostAndPort">

<constructor-arg index="0" value="192.168.101.3"></constructor-arg>

<constructor-arg index="1" value="7005"></constructor-arg>

</bean>

<bean class="redis.clients.jedis.HostAndPort">

<constructor-arg index="0" value="192.168.101.3"></constructor-arg>

<constructor-arg index="1" value="7006"></constructor-arg>

</bean>

</set>

</constructor-arg>

<constructor-arg index="1" ref="jedisPoolConfig"></constructor-arg>

</bean>*/

}

