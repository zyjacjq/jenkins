package chapter.forezp.config;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemcacheConfiguration {

    @Value("${memcache.servers}")
    private String[] servers;
    @Value("${memcache.failover}")
    private boolean failover;
    @Value("${memcache.initConn}")
    private int initConn;
    @Value("${memcache.minConn}")
    private int minConn;
    @Value("${memcache.maxConn}")
    private int maxConn;
    @Value("${memcache.maintSleep}")
    private int maintSleep;
    @Value("${memcache.nagle}")
    private boolean nagel;
    @Value("${memcache.socketTO}")
    private int socketTO;
    @Value("${memcache.aliveCheck}")
    private boolean aliveCheck;
//    @Value("${memcache.weights}")
//    private Integer[] weights;

    @Bean
    public SockIOPool sockIOPool () {
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);
//        pool.setWeights(weights);
        pool.setFailover(failover);
        pool.setInitConn(initConn);
        pool.setMinConn(minConn);
        pool.setMaxConn(maxConn);
        pool.setMaintSleep(maintSleep);
        pool.setNagle(nagel);
        pool.setSocketTO(socketTO);
        pool.setAliveCheck(aliveCheck);
//        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
//        pool.setHashingAlg(0);
        pool.initialize();
        return pool;
    }

    @Bean
    public MemCachedClient memCachedClient(){
        return new MemCachedClient();
    }

}