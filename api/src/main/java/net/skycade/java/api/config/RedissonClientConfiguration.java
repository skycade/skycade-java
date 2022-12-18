package net.skycade.java.api.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

/**
 * The Redisson client configuration.
 * <p>
 * This configuration is used to connect to the Redis server.
 * <br>
 * The Redis server is used to store information about the server
 * and to provide other parts of the network with information about
 * the current server instance.
 *
 * @author Jacob Cohen
 */
@ConfigSerializable
public final class RedissonClientConfiguration {

  /**
   * The address of the Redis server.
   */
  private final String address;

  /**
   * The name of the client.
   */
  private final String clientName;

  /**
   * The id for the redis channel/target id that is used to send packets to all servers.
   */
  private final String allServersTargetId;

  /**
   * If the client is enabled.
   */
  private final boolean enableRedisson;

  /**
   * Constructs a new {@link RedissonClientConfiguration} instance.
   *
   * @param address            the address of the Redis server
   *                           (e.g. {@code localhost:6379})
   * @param clientName         the name of the client
   * @param allServersTargetId the id for the redis channel/target id that is used to send packets to all servers
   * @param enableRedisson     if the client is enabled
   */
  public RedissonClientConfiguration(String address, String clientName, String allServersTargetId,
                                     boolean enableRedisson) {
    this.address = address;
    this.clientName = clientName;
    this.allServersTargetId = allServersTargetId;
    this.enableRedisson = enableRedisson;
  }

  /**
   * The default constructor.
   */
  public RedissonClientConfiguration() {
    this("localhost:6379", "Default Client", "all", false);
  }

  /**
   * Returns the address of the Redis server
   *
   * @return the address of the Redis server
   */
  public String address() {
    return this.address;
  }

  /**
   * Returns the name of the client.
   *
   * @return the name of the client
   */
  public String clientName() {
    return this.clientName;
  }

  /**
   * Returns the id for the redis channel/target id that is used to send packets to all servers.
   *
   * @return the id for the redis channel/target id that is used to send packets to all servers
   */
  public String allServersTargetId() {
    return this.allServersTargetId;
  }

  /**
   * Returns if the client is enabled.
   *
   * @return if the client is enabled
   */
  public boolean redissonEnabled() {
    return this.enableRedisson;
  }
}
