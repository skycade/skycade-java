package net.skycade.java.api;

import static net.skycade.java.api.redis.RedisConstants.setAllServersTargetId;

import net.skycade.java.api.config.GeneralSkycadeJavaConfiguration;
import net.skycade.java.api.redis.RedisServiceProvider;
import net.skycade.java.api.redis.RedissonClientLoader;
import net.skycade.java.api.redis.RedissonClientProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkycadeJavaBootstrapper {

  /**
   * The logger.
   */
  public static final Logger LOGGER = LoggerFactory.getLogger(SkycadeJavaBootstrapper.class);

  /**
   * The Redisson client provider.
   */
  private final RedissonClientProvider redissonClientProvider;

  /**
   * The Redis service provider.
   */
  private final RedisServiceProvider redisServiceProvider;

  /**
   * Constructs a new {@link SkycadeJavaBootstrapper} instance.
   *
   * @param configuration the general server configuration instance. This is used to load the Redisson client
   *                      <p> and contains information about the server that's being started. It is also used to
   *                      connect to the other parts of the network and Skycade's database, as well as
   *                      other APIs.</p>
   */
  public SkycadeJavaBootstrapper(GeneralSkycadeJavaConfiguration configuration) {
    // The general server configuration that contains all the information about the server.
    // This information is used to connect to the Skycade API and provide other parts
    // of the network information about the current server instance.
    this.redissonClientProvider = new RedissonClientProvider(
        new RedissonClientLoader(configuration.getRedissonClientConfiguration()));

    // set the channel id used to send packets to all servers
    // yes, this is a hack, but it's a hack that works
    setAllServersTargetId(configuration.getRedissonClientConfiguration().allServersTargetId());

    // The Redis service provider.
    this.redisServiceProvider = new RedisServiceProvider(this.redissonClientProvider,
        configuration.getRedissonClientConfiguration().allServersTargetId(),
        configuration.getRedissonClientConfiguration().thisServerTargetId());
  }

  /**
   * Gets the Redisson client provider.
   *
   * @return the Redisson client provider
   */
  public RedissonClientProvider redissonClientProvider() {
    return this.redissonClientProvider;
  }

  /**
   * Gets the Redis service provider.
   *
   * @return the Redis service provider
   */
  public RedisServiceProvider redisServiceProvider() {
    return this.redisServiceProvider;
  }
}
