package com.example.demo;

import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

	@Autowired
    private SparkTestService sparkTestService;
	
	@RequestMapping("/demo/top10")
	public Map<String, Object> calculateTopTen() {
		return sparkTestService.calculateTopTen();
	}
	
	@RequestMapping("/demo/exercise")
	public void exercise() {
		sparkTestService.sparkExerciseDemo();
	}

	@RequestMapping("/demo/stream")
	public void streamingDemo() throws InterruptedException {
		sparkTestService.sparkStreaming();
	}

	@RequestMapping("/demo/sparkSql")
	public DataFrame sparkSql() throws InterruptedException {
		return sparkTestService.sparkSql();
	}


}