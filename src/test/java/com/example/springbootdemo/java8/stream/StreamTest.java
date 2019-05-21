package com.example.springbootdemo.java8.stream;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {


    //中间操作
    /**
     * concat() distinct() filter() flatMap() limit() map() peek()
     * skip() sorted() parallel() sequential() unordered()
     */
    //结束操作
    /**
     * allMatch() anyMatch() collect() count() findAny() findFirst()
     * forEach() forEachOrdered() max() min() noneMatch() reduce() toArray()
     */

    public static void main1(String[] args){
        Stream<String> stream = Stream.of("A","d","s","d","f");
        stream.forEach(s -> System.out.println(s));
    }




    //predicate ：评估参数里面的表达式

    public Boolean strEqualsHaha(String str){
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("haha");
            }
        };
        return predicate.test(str);//判断这个参数是否符合test()方法体里面的判断
    }

    public static boolean predicateMethod(String value,Predicate<String> predicate){
        return predicate.test(value);
    }

    public static void main2(String[] args){
        Map<Integer,Integer> map = new HashMap<>();//put  返回对应key 老的value
        System.out.println(map.put(1,1));//null
        System.out.println(map.put(1,2));//1
        System.out.println(map.get(1));//2
        System.out.println(map.putIfAbsent(1,3));//2   putIfAbsent   key对应的value已存在不会替换  而 put 会替换value
        System.out.println(map.put(1,null));
        System.out.println(map.get(1));
        System.out.println(predicateMethod("haha",value -> value.equals("124")));
    }




    private static int putMap(String str){
        Map<String,Boolean> map = new ConcurrentHashMap<>();
        System.out.println(map.putIfAbsent(str,Boolean.TRUE));
        return map.size();
    }
    public static void main(String[] a){
        System.out.println(putMap("dsf"));
        System.out.println(putMap("dsf"));
        System.out.println(putMap("dsf1"));
        System.out.println(putMap("dsf1"));
        Stream<String> stream = Stream.of("A","d","s","d","f");
        List<String> list = stream.filter(distinctBykey(e-> e)).collect(Collectors.toList());
        System.out.println(JSON.toJSON(list));
    }

    static <T> Predicate<T> distinctBykey(Function<? super T,?> keyExtractor){
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        System.out.println(JSON.toJSONString(seen));
        Predicate<T> predicat = t -> seen.putIfAbsent(keyExtractor.apply(t),Boolean.TRUE) == null;
        return predicat;
    }



}
