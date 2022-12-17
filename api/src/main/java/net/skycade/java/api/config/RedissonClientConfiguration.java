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
   * The id for the redis channel/target id that is used to send packets to THIS server only.
   */
  private final String thisServerTargetId;

  /**
   * Constructs a new {@link RedissonClientConfiguration} instance.
   *
   * @param address            the address of the Redis server
   *                           (e.g. {@code localhost:6379})
   * @param clientName         the name of the client
   * @param allServersTargetId the id for the redis channel/target id that is used to send packets to all servers
   * @param thisServerTargetId the id for the redis channel/target id that is used to send packets to THIS server only
   */
  public RedissonClientConfiguration(String address, String clientName, String allServersTargetId,
                                     String thisServerTargetId) {
    this.address = address;
    this.clientName = clientName;
    this.allServersTargetId = allServersTargetId;
    this.thisServerTargetId = thisServerTargetId;
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
   * Returns the id for the redis channel/target id that is used to send packets to THIS server only.
   *
   * @return the id for the redis channel/target id that is used to send packets to THIS server only
   */
  public String thisServerTargetId() {
    return this.thisServerTargetId;
  }
}
