package net.skycade.java.api.redis.client;

import net.skycade.java.api.model.provider.SkycadeJavaProvider;
import org.redisson.api.RedissonClient;

public class RedissonClientProvider implements SkycadeJavaProvider<RedissonClient> {

  /**
   * The Redisson client.
   */
  private final RedissonClient redissonClient;

  /**
   * Constructor.
   *
   * @param redissonClientLoader the Redisson client loader
   */
  public RedissonClientProvider(RedissonClientLoader redissonClientLoader) {
    this.redissonClient = redissonClientLoader.load();
  }

  @Override
  public RedissonClient get() {
    return this.redissonClient;
  }
}
