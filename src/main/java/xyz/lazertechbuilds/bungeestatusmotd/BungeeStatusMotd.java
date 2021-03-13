package xyz.lazertechbuilds.bungeestatusmotd;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import xyz.lazertechbuilds.bungeestatusmotd.listeners.OnPing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

public final class BungeeStatusMotd extends Plugin {

    private static String mainServer;
    private static String motd;
    private static String offlineMotd;
    private static boolean mainOnline;

    @Override
    public void onEnable() {
        loadConfig();

        getProxy().getPluginManager().registerListener(this, new OnPing());

        getProxy().getScheduler().schedule(this, () -> {
            getProxy().getServers().get(getMainServer()).ping(((result, error) -> {
                mainOnline = error.getCause() == null;
            }));
        }, 2, 2, TimeUnit.SECONDS);
    }

    private void loadConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(
                    new File(getDataFolder(), "config.yml"));
            mainServer = configuration.getString("mainServer");
            motd = configuration.getString("motd");
            offlineMotd = configuration.getString("offline-motd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getMainServer() {
        return mainServer;
    }

    public static String getMotd() {
        return motd;
    }

    public static boolean isMainOnline() {
        return mainOnline;
    }

    public static String getOfflineMotd() {
        return offlineMotd;
    }
}
