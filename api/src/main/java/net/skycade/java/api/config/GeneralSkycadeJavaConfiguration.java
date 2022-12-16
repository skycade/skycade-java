package net.skycade.java.api.config;

import java.util.UUID;

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
   * The constructor.
   *
   * @param serverId   The server's unique identifier.
   * @param serverName The server's name.
   */
  public GeneralSkycadeJavaConfiguration(UUID serverId, String serverName) {
    this.serverId = serverId;
    this.serverName = serverName;
  }

  /**
   * Gets the server's unique identifier.
   *
   * @return The server's unique identifier.
   */
  public UUID getServerId() {
    return serverId;
  }

  /**
   * Gets the server's name.
   *
   * @return The server's name.
   */
  public String getServerName() {
    return serverName;
  }
}
