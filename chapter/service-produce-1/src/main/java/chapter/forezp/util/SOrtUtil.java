package chapter.forezp.util;

import java.util.*;

/**
 * Created by JF on 2019/9/19.
 */
public class SOrtUtil {
    public static void sortDesc(List list,final String... orderByCol){
        Collections.sort(list, new Comparator<Map>() {
            @Override
            public int compare(Map o2,Map o1){
                return recursion(o1,o2,0);
            }

            private int recursion(Map o1,Map o2,int i){
                if (o1.containsKey(orderByCol[i])&& o2.containsKey(orderByCol[i])){
                    Object value1 = o1.get(orderByCol[i]);
                    Object value2 = o2.get(orderByCol[i]);
                    if (value1==null&&value2==null){
                        if ((i+1)<orderByCol.length){
                            int recursion=recursion(o1,o2,i+1);
                            return recursion;
                        }else{
                            return 0;
                        }
                    }else if(value1==null&&value2!=null){
                        return 1;
                    }else if(value1!=null&&value2==null){
                        return -1;
                    }else{
                        if (value1.equals(value2)){
                            if ((i+1)<orderByCol.length){
                                return recursion(o1,o2,i+1);
                            }else {
                                return 0;
                            }
                        }else{
                            if (value1 instanceof String && value2 instanceof String){
                                return value1.toString().compareTo(value2.toString());
                            }else{
                                return new java.math.BigDecimal(value1.toString()).compareTo(new java.math.BigDecimal(value2.toString()));
                            }
                        }
                    }
                }else {
                    return 0;
                }
            }

        });
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        Map map1 = new HashMap();
        map1.put("id",123);
        map1.put("name",321);
        map1.put("age",100);
        Map map2 = new HashMap();
        map2.put("id",123);
        map2.put("name",320);
        map2.put("age",105);
        Map map3 = new HashMap();
        map3.put("id",124);
        map3.put("name",345);
        map3.put("age",111);
        Map map4 = new HashMap();
        map4.put("id",124);
        map4.put("name",345);
        map4.put("age",104);
        Map map5 = new HashMap();
        map5.put("id",125);
        map5.put("name",391);
        map5.put("age",108);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        System.out.println(list.toString());
        SOrtUtil.sortDesc(list,new String[]{"id","name","age"});
        System.out.println(list.toString());
    }
}
