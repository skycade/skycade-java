package net.skycade.java.api.redis.client;

import net.skycade.java.api.config.RedissonClientConfiguration;
import net.skycade.java.api.model.provider.SkycadeJavaProviderObjectLoader;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonClientLoader extends SkycadeJavaProviderObjectLoader<RedissonClient> {

  /**
   * The Redisson client configuration.
   */
  private final RedissonClientConfiguration configuration;

  /**
   * Constructor.
   *
   * @param configuration the Redisson client configuration
   */
  public RedissonClientLoader(RedissonClientConfiguration configuration) {
    this.configuration = configuration;
  }

  /**
   * Loads the Redisson client.
   *
   * @return the Redisson client
   */
  @Override
  public RedissonClient load() {
    Config config = new Config();
    config.useSingleServer().setAddress(this.configuration.address())
        .setClientName(this.configuration.clientName());

    return Redisson.create(config);
  }
}
