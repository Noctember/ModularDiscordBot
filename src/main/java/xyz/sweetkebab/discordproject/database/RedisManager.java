package xyz.sweetkebab.discordproject.database;

import redis.clients.jedis.JedisPool;

public class RedisManager {

	private final String host, password;
	private final int port;

	private static RedisDatabase redisDatabase;

	public RedisManager(String host, String password, int port) {
		this.host = host;
		this.password = password;
		this.port = port;
	}

	public RedisManager(String host, int port) {
		this.host = host;
		this.password = "";
		this.port = port;
	}

	public void connect() {
		redisDatabase = new RedisDatabase(host, password, port);
		redisDatabase.connect();
	}

	public void disconnect() {
		redisDatabase.disconnect();
	}

	public static JedisPool getJedisPool() {
		return redisDatabase.getJedisPool();
	}

	public RedisDatabase getRedisDatabase() {
		return redisDatabase;
	}
}