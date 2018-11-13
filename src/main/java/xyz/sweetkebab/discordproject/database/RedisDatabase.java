package xyz.sweetkebab.discordproject.database;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import xyz.sweetkebab.discordproject.BilBerry;

public class RedisDatabase {

	private final String host, password;
	private final int port;

	private JedisPool jedisPool;


	public RedisDatabase(String host, String password, int port) {
		this.host = host;
		this.password = password;
		this.port = port;
	}

	public RedisDatabase(String host, int port) {
		this.host = host;
		this.password = "";
		this.port = port;
	}

	public void connect() {
		ClassLoader previous = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(Jedis.class.getClassLoader());
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		if(password.isEmpty())
			jedisPool = new JedisPool(jedisPoolConfig, host, port, 3000);
		else
			jedisPool = new JedisPool(jedisPoolConfig, host, port, 3000, password);

		Thread.currentThread().setContextClassLoader(previous);
		try (Jedis jedisConnect = jedisPool.getResource()) {
		} catch (Exception e) {
			e.printStackTrace();
			connect();
		}
	}

	public void disconnect() {
		try {
			jedisPool.close();
		} catch (Exception e) {
			return;
		}
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}
}