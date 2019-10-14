package com.boot.lea.mybot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Resource
	private RedisTemplate<String, Object> redisTemplate; //这里使用的是redisTemplate

	/**
	 * 递增测试
	 */
	@Test
	public void redisIncrTest() {
		for (int i=0; i < 100; i++) {
			System.out.println(redisTemplate.opsForValue().increment("incr", 1));
		}
	}

	/**
	 * 递增存储大的value值，验证卢健的大value 900k+问题
	 */
	@Test
	public void redisBigValueTest() throws Exception {
		ClassPathResource classPathResource = new ClassPathResource("redisBigValueTest.txt");
		InputStream inputStream =classPathResource.getInputStream();
		byte[] bt = this.readStream(inputStream);
		String str = new String(bt);
		//System.out.println("信息：" + str);
		System.out.println("信息大小：" + str.length());

		long startsettime = System.currentTimeMillis();
		redisTemplate.opsForValue().set("bigValue", str);
		long endsettime = System.currentTimeMillis();
		System.out.println("set信息耗时：" + (endsettime-startsettime));

		// 休息3秒
		Thread.sleep(3000);

		long startgettime = System.currentTimeMillis();
		String result = (String) redisTemplate.opsForValue().get("bigValue");
		System.out.println("卢健::::"+result);
		long endgettime = System.currentTimeMillis();
		System.out.println("get信息耗时：" + (endgettime-startgettime));
	}

	/**
	 * @功能 读取流
	 * @param inStream
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

}
