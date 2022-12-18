package net.skycade.java.api;

import static net.skycade.java.api.redis.RedisConstants.setAllServersTargetId;

import java.nio.file.Path;
import net.skycade.java.api.config.ConfigurationLoader;
import net.skycade.java.api.config.GeneralSkycadeJavaConfiguration;
import net.skycade.java.api.model.provider.SkycadeJavaProvider;
import net.skycade.java.api.redis.client.EmptyRedissonClientProvider;
import net.skycade.java.api.redis.client.RedissonClientLoader;
import net.skycade.java.api.redis.client.RedissonClientProvider;
import net.skycade.java.api.redis.service.EmptyRedisServiceProvider;
import net.skycade.java.api.redis.service.RedisServiceProvider;
import net.skycade.java.api.redis.service.RedissonServiceProvider;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkycadeJavaBootstrapper {

  /**
   * The logger.
   */
  public static final Logger LOGGER = LoggerFactory.getLogger(SkycadeJavaBootstrapper.class);

  /**
   * The skycade-java api object.
   */
  private final SkycadeJavaApi skycadeJavaApi;

  /**
   * Constructs a new {@link SkycadeJavaBootstrapper} instance.
   */
  public SkycadeJavaBootstrapper() {
    // The general server configuration that contains all the information about the server.
    // This information is used to connect to the Skycade API and provide other parts
    // of the network information about the current server instance.
    GeneralSkycadeJavaConfiguration configuration =
        new ConfigurationLoader<>(Path.of("skycade-java.conf"),
            GeneralSkycadeJavaConfiguration.class, this.getClass()).config();

    SkycadeJavaProvider<RedissonClient> redissonClientProvider;
    RedisServiceProvider redisServiceProvider;

    if (configuration.redissonConfiguration().redissonEnabled()) {
      redissonClientProvider = new RedissonClientProvider(
          new RedissonClientLoader(configuration.redissonConfiguration()));

      // The Redis service provider.
      redisServiceProvider = new RedissonServiceProvider(redissonClientProvider,
          configuration.redissonConfiguration().allServersTargetId(),
          configuration.serverId().toString());
    } else {
      redissonClientProvider = new EmptyRedissonClientProvider();
      redisServiceProvider = new EmptyRedisServiceProvider();
    }

    // The skycade-java api object.
    this.skycadeJavaApi = new SkycadeJavaApi(redissonClientProvider, redisServiceProvider);

    // set the channel id used to send packets to all servers
    // yes, this is a hack, but it's a hack that works
    setAllServersTargetId(configuration.redissonConfiguration().allServersTargetId());
  }

  /**
   * Gets the skycade-java api object.
   *
   * @return the skycade-java api object
   */
  public SkycadeJavaApi skycadeJavaApi() {
    return this.skycadeJavaApi;
  }
}
