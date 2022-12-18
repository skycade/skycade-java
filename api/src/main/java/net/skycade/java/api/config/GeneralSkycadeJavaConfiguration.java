package net.skycade.java.api.config;

import java.util.UUID;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

/**
 * The general server configuration that contains all the information about the server.
 * <p>
 * This information is used to connect to the Skycade API and provide other parts
 * of the network information about the current server instance.
 *
 * @author Jacob Cohen
 * <p>
 * TODO: Add more configuration options (redis, gRPC, etc.).
 */
@ConfigSerializable
public class GeneralSkycadeJavaConfiguration {

  /**
   * The server's unique identifier.
   */
  private final UUID serverId;

  /**
   * The server's name.
   */
  private final String serverName;

  /**
   * The redisson client configuration.
   */
  private final RedissonClientConfiguration redissonConfiguration;

  /**
   * The constructor.
   *
   * @param serverId                    The server's unique identifier.
   * @param serverName                  The server's name.
   * @param redissonClientConfiguration The redisson client configuration.
   */
  public GeneralSkycadeJavaConfiguration(UUID serverId, String serverName,
                                         RedissonClientConfiguration redissonClientConfiguration) {
    this.serverId = serverId;
    this.serverName = serverName;
    this.redissonConfiguration = redissonClientConfiguration;
  }

  /**
   * The default constructor.
   */
  public GeneralSkycadeJavaConfiguration() {
    this(UUID.randomUUID(), "Default Server", new RedissonClientConfiguration());
  }

  /**
   * Gets the server's unique identifier.
   *
   * @return The server's unique identifier.
   */
  public UUID serverId() {
    return serverId;
  }

  /**
   * Gets the server's name.
   *
   * @return The server's name.
   */
  public String serverName() {
    return serverName;
  }

  /**
   * Gets the redisson client configuration.
   *
   * @return The redisson client configuration.
   */
  public RedissonClientConfiguration redissonConfiguration() {
    return redissonConfiguration;
  }
}
