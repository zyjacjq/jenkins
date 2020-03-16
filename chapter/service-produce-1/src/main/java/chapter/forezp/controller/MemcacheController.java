package chapter.forezp.controller;

import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MemcacheController {

    @Autowired
    private MemCachedClient memCachedClient;
    @Autowired(required = false)
    public void setMemCachedClient(MemCachedClient memCachedClient) {
        memCachedClient.setSanitizeKeys(false);
        this.memCachedClient = memCachedClient;
    }

    /**
     * memcache缓存
     */
    @RequestMapping("/memcache")
    @ResponseBody
    public String memcache() throws InterruptedException{
        // 放入缓存
        boolean flag = memCachedClient.set("mem", "name");
        // 取出缓存
        Object value = memCachedClient.get("mem");
        System.out.println(value);
        // 3s后过期
        memCachedClient.set("num", "666", new Date(3000));
        /*memCachedClient.set("num", "666", new Date(System.currentTimeMillis()+3000));与上面的区别是当设置了这个时间点
        之后，它会以服务端的时间为准，也就是说如果本地客户端的时间跟服务端的时间有差值，这个值就会出现问题。
        例：如果本地时间是20:00:00,服务端时间为20:00:10，那么实际上会是40秒之后这个缓存key失效*/
        Object key = memCachedClient.get("num");
        System.out.println(key);
        //多线程睡眠3s
        Thread.sleep(3000);
        key  = memCachedClient.get("num");
        System.out.println(value);
        System.out.println(key );
        List<String> allKeys = getAllKeys(memCachedClient);
        String sub = "";
        for (String keys : allKeys){
            String valuess = (String)memCachedClient.get(keys);
            sub += valuess;
        }
        return allKeys.toString();
    }

    /**
     * 获取服务器上面所有的key
     * @return
     * @author liu787427876@126.com
     * @date 2013-12-4
     */
    public  List<String> getAllKeys(MemCachedClient memCachedClient) {
//        logger.info("开始获取没有挂掉服务器中所有的key.......");
        List<String> list = new ArrayList<String>();
        Map<String, Map<String, String>> items = memCachedClient.statsItems();
        for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext();) {
            String itemKey = itemIt.next();
            Map<String, String> maps = items.get(itemKey);
            for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext();) {
                String mapsKey = mapsIt.next();
                String mapsValue = maps.get(mapsKey);
                if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str
                    String[] arr = mapsKey.split(":");
                    int slabNumber = Integer.valueOf(arr[1].trim());
                    int limit = Integer.valueOf(mapsValue.trim());
                    Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);
                    for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext();) {
                        String dumpKey = dumpIt.next();
                        Map<String, String> allMap = dumpMaps.get(dumpKey);
                        for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext();) {
                            String allKey = allIt.next();
                            list.add(allKey.trim());
                        }
                    }
                }
            }
        }
//        logger.info("获取没有挂掉服务器中所有的key完成.......");
        return list;
    }

    @RequestMapping("/fbs")
    @ResponseBody
    public String fbs(){
//        boolean test = memCachedClient.set("test", "test");
//        boolean testone = memCachedClient.set("testone", "testone");
//        boolean testtwo = memCachedClient.set("testtwo", "testtwo");
//        memCachedClient.set("date","3",new Date(1000*60*3));
        long startTime = System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            memCachedClient.set(""+i,i+"abc");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("uifffffffdshhhhhhhhhhhhhhhhhh"+(endTime-startTime)/1000);
        return (endTime-startTime)/1000+"";
    }

    @RequestMapping("/test/{test}")
    @ResponseBody
    public Boolean test(@PathVariable String test){
        boolean set = memCachedClient.set(test, test);
        return set;
    }

    @RequestMapping("/get/{test}")
    @ResponseBody
    public String getTest(@PathVariable String test){
        return memCachedClient.get(test,null,true).toString();
    }
}