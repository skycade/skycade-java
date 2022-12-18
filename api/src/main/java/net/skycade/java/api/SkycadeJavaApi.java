package net.skycade.java.api;

import net.skycade.java.api.model.provider.SkycadeJavaProvider;
import net.skycade.java.api.redis.service.RedisServiceProvider;
import org.redisson.api.RedissonClient;

public class SkycadeJavaApi {


  /**
   * The Redisson client provider.
   */
  private final SkycadeJavaProvider<RedissonClient> redissonClientProvider;

  /**
   * The Redis service provider.
   */
  private final RedisServiceProvider redissonServiceProvider;

  /**
   * Constructs a new {@link SkycadeJavaApi} instance.
   *
   * @param redissonClientProvider  the Redisson client provider.
   * @param redissonServiceProvider the Redis service provider.
   */
  public SkycadeJavaApi(SkycadeJavaProvider<RedissonClient> redissonClientProvider,
                        RedisServiceProvider redissonServiceProvider) {
    this.redissonClientProvider = redissonClientProvider;
    this.redissonServiceProvider = redissonServiceProvider;
  }


  /**
   * Gets the Redisson client provider.
   *
   * @return the Redisson client provider
   */
  public SkycadeJavaProvider<RedissonClient> redissonClientProvider() {
    return this.redissonClientProvider;
  }

  /**
   * Gets the Redis service provider.
   *
   * @return the Redis service provider
   */
  public RedisServiceProvider redisServiceProvider() {
    return this.redissonServiceProvider;
  }
}
