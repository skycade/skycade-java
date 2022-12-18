package net.skycade.java.api.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import net.skycade.java.api.utils.ResourceUtils;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

/**
 * Runtime configuration loader.
 *
 * @author Jacob Cohen
 */
public class ConfigurationLoader<T> {

  /**
   * The path to the configuration file.
   */
  private final Path path;

  /**
   * The root node of the configuration.
   */
  private CommentedConfigurationNode root;

  /**
   * The class of the configuration object.
   */
  private final Class<T> configClass;

  /**
   * The loader class for the configuration.
   */
  private final Class<?> loaderClass;

  /**
   * Constructs a new {@link ConfigurationLoader} instance.
   *
   * @param path        the path to the configuration file
   * @param clazz       the class of the configuration object
   * @param loaderClass the class of the loader
   */
  public ConfigurationLoader(Path path, Class<T> clazz, Class<?> loaderClass) {
    this.path = path;
    this.configClass = clazz;
    this.loaderClass = loaderClass;
  }


  /**
   * Returns the comment configuration node.
   *
   * @return the comment configuration node
   */
  private CommentedConfigurationNode load() {
    // if the file doesn't exist, create it
    if (!path.toFile().exists()) {
      try {
        ResourceUtils.extractResource(path.getFileName().toString(), loaderClass);
      } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
      }
    }

    HoconConfigurationLoader loader = HoconConfigurationLoader.builder().path(path).build();

    CommentedConfigurationNode root;
    try {
      root = loader.load();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration file", e);
    }

    this.root = root;

    return root;
  }

  /**
   * Returns the configuration object, represented by the class passed in the constructor.
   *
   * @return the configuration object
   */
  public T config() {
    if (this.root == null) {
      this.root = load();
    }

    try {
      return this.root.get(configClass);
    } catch (SerializationException e) {
      throw new RuntimeException(e);
    }
  }
}
