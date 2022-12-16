package net.skycade.java.api;

import net.skycade.java.api.config.GeneralSkycadeJavaConfiguration;

public class SkycadeJavaBootstrapper {

  /**
   * The general server configuration that contains all the information about the server.
   * <p>
   * This information is used to connect to the Skycade API and provide other parts
   * of the network information about the current server instance.
   */
  private final GeneralSkycadeJavaConfiguration configuration;

  /**
   * Constructs a new {@link SkycadeJavaBootstrapper} instance.
   *
   * @param configuration the general server configuration
   */
  public SkycadeJavaBootstrapper(GeneralSkycadeJavaConfiguration configuration) {
    this.configuration = configuration;
  }
}
