package com.ppcredit.bamboo.backend.web.rest.admin.util.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Title: BaseRedisSupport.java Description: Redis 操作支持类
 * 
 * @author yang_hx
 * @created 2015-10-16 下午5:01:04
 */
@Repository("BaseLogRedisSupport")
public class BaseLogRedisSupport {

	@Resource
	private StringRedisTemplate logRedisTemplate;

	// private RedisTemplate

	public StringRedisTemplate getLogRedisTemplate() {
		return logRedisTemplate;
	}

	public void setLogRedisTemplate(StringRedisTemplate logRedisTemplate) {
		this.logRedisTemplate = logRedisTemplate;
	}

	/**
	 * 
	 * @discription 保存
	 * @author yang_hx
	 * @created 2015-10-16 下午5:16:51
	 * @param key
	 * @param value
	 */
	public void set(final String key, final String value) {
		logRedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(logRedisTemplate.getStringSerializer().serialize(key),
						logRedisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}

	/**
	 * 
	 * @discription 根据key得到对应的值
	 * @author yang_hx
	 * @created 2015-10-16 下午5:17:05
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		return logRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] _k = logRedisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(_k)) {
					byte[] _v = connection.get(_k);
					return logRedisTemplate.getStringSerializer().deserialize(_v);
				}
				return null;
			}
		});
	}

	/**
	 * 批量删除key
	 * 
	 * 例如：log_*
	 */
	public void deleteStartWith(String start) {
		Set<String> s = logRedisTemplate.keys(start);

		Iterator it = s.iterator();

		while (it.hasNext()) {
			String key = (String) it.next();
			logRedisTemplate.delete(key);
			System.out.println("删除key:" + key);
		}
	}

	/**
	 * 
	 * @discription 根据key删除
	 * @author yang_hx
	 * @created 2015-10-16 下午5:17:27
	 * @param key
	 * @return
	 */
	public Long delete(final String key) {
		return logRedisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.del(logRedisTemplate.getStringSerializer().serialize(key));
			}
		});
	}

	/**
	 * 
	 * @discription 压栈 ,栈是一种先进后出的数据类型
	 * @author yang_hx
	 * @created 2015-10-16 下午5:17:44
	 * @param key
	 * @param value
	 * @return
	 */
	public Long push(String key, String value) {
		return logRedisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * 
	 * @discription 出栈 ,栈是一种先进后出的数据类型
	 * @author yang_hx
	 * @created 2015-10-16 下午5:17:44
	 * @param key
	 * @param value
	 * @return
	 */
	public String pop(String key) {
		return logRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 
	 * @discription 入队 ,队列是一种先进先出的数据类型
	 * @author yang_hx
	 * @created 2015-10-16 下午5:17:44
	 * @param key
	 * @param value
	 * @return
	 */
	public Long in(String key, String value) {
		return logRedisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * 
	 * @discription 出队 ,队列是一种先进先出的数据类型
	 * @author yang_hx
	 * @created 2015-10-16 下午5:17:44
	 * @param key
	 * @param value
	 * @return
	 */
	public String out(String key) {
		return logRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 
	 * @discription 栈/队列长
	 * @author yang_hx
	 * @created 2015-10-16 下午5:18:28
	 * @param key
	 * @return
	 */
	public Long length(String key) {
		return logRedisTemplate.opsForList().size(key);
	}

	/**
	 * 
	 * @discription 范围检索
	 * @author yang_hx
	 * @created 2015-10-16 下午5:18:37
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> range(String key, int start, int end) {
		return logRedisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 
	 * @discription 移除
	 * @author yang_hx
	 * @created 2015-10-16 下午5:18:43
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, String value) {
		logRedisTemplate.opsForList().remove(key, i, value);
	}

	/**
	 * 
	 * @discription 检索
	 * @author yang_hx
	 * @created 2015-10-16 下午5:18:51
	 * @param key
	 * @param index
	 * @return
	 */
	public String index(String key, long index) {
		return logRedisTemplate.opsForList().index(key, index);
	}

	/**
	 * 
	 * @discription 置值
	 * @author yang_hx
	 * @created 2015-10-16 下午5:19:00
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, String value) {
		logRedisTemplate.opsForList().set(key, index, value);
	}

	/**
	 * 
	 * @discription 裁剪
	 * @author yang_hx
	 * @created 2015-10-16 下午5:19:07
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		logRedisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * 消息队列，接受者 从消息队列中获取消息，如果没有消息，则等待100秒
	 * 
	 * @param queueName
	 * @return
	 */
	public String queueReceiver(final String queueName) {
		return logRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				// 等待100秒
				List<byte[]> l = connection.bLPop(100, logRedisTemplate.getStringSerializer().serialize(queueName));
				if (l != null) {
					return logRedisTemplate.getStringSerializer().deserialize(l.get(1));
				}
				return null;
			}
		});
	}

	/**
	 * 消息队列
	 * 从A队列取，同时存入B队列
	 * 
	 * @param queueName
	 * @return
	 */
	public String queueReceiverAndPush(final String srcqueue,final String dstqueue) {
		return logRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				// 等待100秒
				byte[] r = connection.bRPopLPush(100, logRedisTemplate.getStringSerializer().serialize(srcqueue), logRedisTemplate.getStringSerializer().serialize(dstqueue));
				if (r != null) {
					return logRedisTemplate.getStringSerializer().deserialize(r);
				}
				return null;
			}
		});
	}
	
	
	/**
	 * 发布者
	 * 
	 * @param channel
	 *            主题
	 * @param message
	 *            内容
	 * @return
	 */
	public Long topicPublish(final String channel, final String message) {
		return logRedisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {

				return connection.publish(logRedisTemplate.getStringSerializer().serialize(channel),
						logRedisTemplate.getStringSerializer().serialize(message));
			}
		});
	}

	/**
	 * 订阅者，基于长连接模型
	 * 
	 * @param channels
	 *            主题
	 */
	public void topicSubscribe(final MessageListener listener, final String channel) {
		logRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.subscribe(listener, logRedisTemplate.getStringSerializer().serialize(channel));
				return null;
			}
		});
	}

	/**
	 * 
	 * @discription 保存,可以设置过期时间
	 * @author huangming
	 * @created 2015-10-16 下午5:16:51
	 * @param key
	 * @param value
	 * @param timeout
	 *            过期时间，单位：秒
	 */
	public void set(final String key, final String value, final long timeout) {
		logRedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(logRedisTemplate.getStringSerializer().serialize(key),
						logRedisTemplate.getStringSerializer().serialize(value));
				logRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
				return null;
			}
		});
	}

	/**
	 * 设置对象(采用Redis的String存储)
	 * 
	 * @author huangming
	 * @date 2016-01-14
	 * @param key
	 *            key
	 * @param T
	 *            对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> void setObject(String key, T T) {
		ValueOperations<String, T> valueOperations = (ValueOperations<String, T>) logRedisTemplate.opsForValue();
		valueOperations.set(key, T);
	}

	/**
	 * 设置对象(采用Redis的String存储，含过期时间)
	 * 
	 * @author huangming
	 * @date 2016-01-14
	 * @param key
	 *            key
	 * @param T
	 *            对象
	 * @param timeout
	 *            过期时间，单位：秒
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> void setObject(String key, T T, long timeout) {
		ValueOperations<String, T> valueOperations = (ValueOperations<String, T>) logRedisTemplate.opsForValue();
		valueOperations.set(key, T, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 根据key获取对象(对象采用Redis的String存储)
	 * 
	 * @author huangming
	 * @date 2016-01-14
	 * @param key
	 *            key
	 * @return T 对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends Serializable> T getObject(String key) {
		ValueOperations<String, T> valueOperations = (ValueOperations<String, T>) logRedisTemplate.opsForValue();
		return valueOperations.get(key);
	}

	/**
	 * 
	 * @discription hest保存形式
	 * @author zhouxin
	 * @created 2015-10-16 下午5:16:51
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(final String key, final String field, final String value) {
		logRedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hSet(logRedisTemplate.getStringSerializer().serialize(key),
						logRedisTemplate.getStringSerializer().serialize(field),
						logRedisTemplate.getStringSerializer().serialize(value));
				return null;
			}
		});
	}

	/**
	 * hegtAll Map<String,String> 类型用
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> hgetAll(final String key) {
		Map<String, String> result = logRedisTemplate.execute(new RedisCallback<Map<String, String>>() {
			@Override
			public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
				Map<byte[], byte[]> retMap = connection.hGetAll(logRedisTemplate.getStringSerializer().serialize(key));
				Map<String, String> retResultMap = new HashMap<>();
				for (Entry<byte[], byte[]> entry : retMap.entrySet()) {
					try {
						String key = new String(entry.getKey(), "UTF-8");
						String value = new String(entry.getValue(), "UTF-8");
						retResultMap.put(key, value);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				return retResultMap;
			}
		});
		return result;
	}

	/**
	 * hegt Map<String,String> 类型用
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String hget(final String key, final String field) {
		String result = logRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				String result = "";
				byte[] value = connection.hGet(logRedisTemplate.getStringSerializer().serialize(key),
						logRedisTemplate.getStringSerializer().serialize(field));
				try {
					result = new String(value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return result;
			}
		});
		return result;
	}

	/**
	 * 
	 * @discription hdel 删除哈希字段
	 * @author yang_hx
	 * @created 2016-4-19下午5:16:51
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hdel(final String key, final String field) {
		logRedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(logRedisTemplate.getStringSerializer().serialize(key),
						logRedisTemplate.getStringSerializer().serialize(field));
				return null;
			}
		});
	}
	/**
	 * 
	 * @discription 返回hset的长度
	 * @author yang_hx
	 * @created 2016-4-19下午5:16:51
	 * @param key
	 */
	public Long hlength(String key) {
		return logRedisTemplate.opsForHash().size(key);
	}
	
}
