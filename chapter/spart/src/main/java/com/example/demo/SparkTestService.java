package com.example.demo;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class SparkTestService implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(SparkTestService.class);
    
    private static final Pattern SPACE = Pattern.compile(" ");

    @Autowired
    private transient JavaSparkContext sc;
    
    

    public Map<String, Object> calculateTopTen() {

	   Map<String, Object> result = new HashMap<String, Object>();
        JavaRDD<String> lines = sc.textFile("src/test/java/test.txt").cache();

	   System.out.println();
	   System.out.println("-------------------------------------------------------");
		System.out.println(lines.count());

//	   JavaRDD<String> words = lines.flatMap(str -> Arrays.asList(SPACE.split(str)));
//
//	   JavaPairRDD<String, Integer> ones = words.mapToPair(str -> new Tuple2<String, Integer>(str, 1));
//
//	   JavaPairRDD<String, Integer> counts = ones.reduceByKey((Integer i1, Integer i2) -> (i1 + i2));
//
//	   JavaPairRDD<Integer, String> temp = counts.mapToPair(tuple -> new Tuple2<Integer, String>(tuple._2, tuple._1));
//
//	   JavaPairRDD<String, Integer> sorted = temp.sortByKey(false).mapToPair(tuple -> new Tuple2<String, Integer>(tuple._2, tuple._1));
//
//	   System.out.println();
//	   System.out.println("-------------------------------------------------------");
//	   System.out.println(sorted.count());
//
//	   //List<Tuple2<String, Integer>> output = sorted.collect();
//
//	   //List<Tuple2<String, Integer>> output = sorted.take(10);
//
//	   List<Tuple2<String, Integer>> output = sorted.top(10);
//		for (Tuple2<String, Integer> tuple : output) {
//	       result.put(tuple._1(), tuple._2());
//	   }
//
//	   return result;
		lines.map(new Function<String, String>() {
			@Override
			public String call(String s) throws Exception {
				System.out.println(s);
				return s;
			}
		});

		System.out.println(lines.count());

		JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {


			@Override
			public Iterable<String> call(String s) throws Exception {
				return Arrays.asList(SPACE.split(s));
			}
		});

		JavaPairRDD<String, Integer> ones = words.mapToPair(new PairFunction<String, String, Integer>() {

			private static final long serialVersionUID = 1L;

			public Tuple2<String, Integer> call(String s) {
				return new Tuple2<String, Integer>(s, 1);
			}
		});

		JavaPairRDD<String, Integer> counts = ones.reduceByKey(new Function2<Integer, Integer, Integer>() {

			private static final long serialVersionUID = 1L;

			public Integer call(Integer i1, Integer i2) {
				return i1 + i2;
			}
		});

		List<Tuple2<String, Integer>> output = counts.collect();
//		List<Tuple2<String, Integer>> output = counts.top(10);
		for (Tuple2<String, Integer> tuple : output) {
			result.put(tuple._1(),tuple._2());

		}

		return result;

	}
    
    /**
     * 练习demo，熟悉其中API
     */
    public void sparkExerciseDemo() {
	   List<Integer> data = Lists.newArrayList(1,2,3,4,5,6);
	   JavaRDD<Integer> rdd01 = sc.parallelize(data);
	   rdd01 = rdd01.map(num ->{
	      return num * num; 
	   });
	   //data map :1,4,9,16,25,36
	   logger.info("data map :{}", Joiner.on(",").skipNulls().join(rdd01.collect()).toString());
	
	   rdd01 = rdd01.filter(x -> x < 6);
	
	   //data filter :1,4
	   logger.info("data filter :{}",Joiner.on(",").skipNulls().join(rdd01.collect()).toString());
	
	   rdd01 = rdd01.flatMap( x ->{
	      Integer[] test = {x,x+1,x+2};
	      return Arrays.asList(test);
	   });
	
	   //flatMap :1,2,3,4,5,6
	   logger.info("flatMap :{}",Joiner.on(",").skipNulls().join(rdd01.collect()).toString());
	
	   JavaRDD<Integer> unionRdd = sc.parallelize(data);
	
	   rdd01 = rdd01.union(unionRdd);
	
	   //union :1,2,3,4,5,6,1,2,3,4,5,6
	   logger.info("union :{}",Joiner.on(",").skipNulls().join(rdd01.collect()).toString());
	
	   List<Integer> result = Lists.newArrayList();
	   result.add(rdd01.reduce((Integer v1,Integer v2) -> {
	       return v1+v2;
	   }));
	
	    //reduce :42
	   logger.info("reduce :{}",Joiner.on(",").skipNulls().join(result).toString());
	   result.forEach(System.out::print);
	
	   JavaPairRDD<Integer,Iterable<Integer>> groupRdd  = rdd01.groupBy(x -> {
	       logger.info("======grouby========：{}",x);
	       if (x > 10) return 0;
	       else  return 1;
	   });
	
	   List<Tuple2<Integer,Iterable<Integer>>> resul = groupRdd.collect();
	
	   //group by  key:1 value:1,2,3,4,5,6,1,2,3,4,5,6
	   resul.forEach(x -> {
	        logger.info("group by  key:{} value:{}",x._1,Joiner.on(",").skipNulls().join(x._2).toString());
	   });
	
    }
    
    /**
     * spark streaming 练习
     */
    public void sparkStreaming() throws InterruptedException {
	   JavaStreamingContext jsc = new JavaStreamingContext(sc, Durations.seconds(10));//批间隔时间
	   JavaReceiverInputDStream<String> lines = jsc.receiverStream(new CustomReceiver(StorageLevel.MEMORY_AND_DISK_2()));
	   JavaDStream<Long> count =  lines.count();
	   count = count.map(x -> {
	       logger.info("这次批一共多少条数据：{}",x);
	      return x; 
	   });
	   count.print();
	   jsc.start();
	   jsc.awaitTermination();
	   jsc.stop();
    }


	public DataFrame sparkSql(){
//		SparkConf conf = new SparkConf();
//		conf.setMaster("local[2]").setAppName("jsonRDD");
//		JavaSparkContext sc = new JavaSparkContext(conf);
		SQLContext sqlContext = new SQLContext(sc);

		JavaRDD<String> nameRDD = sc.parallelize(Arrays.asList(
				"{\"name\":\"zhangsan\",\"age\":\"18\"}",
				"{\"name\":\"lisi\",\"age\":\"19\"}",
				"{\"name\":\"wangwu\",\"age\":\"20\"}"
		));
		JavaRDD<String> scoreRDD = sc.parallelize(Arrays.asList(
				"{\"name\":\"zhangsan\",\"score\":\"100\"}",
				"{\"name\":\"lisi\",\"score\":\"200\"}",
				"{\"name\":\"wangwu\",\"score\":\"300\"}"
		));

		DataFrame namedf = sqlContext.read().json(nameRDD);
		DataFrame scoredf = sqlContext.read().json(scoreRDD);
		namedf.registerTempTable("name");
		scoredf.registerTempTable("score");

		DataFrame result = sqlContext.sql("select name.name,name.age,score.score from name,score where name.name = score.name");
		//Dataset<Row> result = sqlContext.sql("select * from name");
		result.show();
//		result.foreach(x ->System.out.print(x));

		System.out.println(result);
//		sc.stop();
		return result;
	}


}