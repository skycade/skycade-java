package net.skycade.java.api.redis.client;

import net.skycade.java.api.model.provider.SkycadeJavaProvider;
import org.redisson.api.RedissonClient;

public class EmptyRedissonClientProvider implements SkycadeJavaProvider<RedissonClient> {

  @Override
  public RedissonClient get() {
    throw new UnsupportedOperationException("Redis is not enabled.");
  }
}
