package xyz.lazertechbuilds.bungeestatusmotd.listeners;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import xyz.lazertechbuilds.bungeestatusmotd.BungeeStatusMotd;

public class OnPing implements Listener {

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing serverPing = event.getResponse();
        if (BungeeStatusMotd.isMainOnline()) {
            serverPing.setDescriptionComponent(new TextComponent(BungeeStatusMotd.getMotd()));
        } else {
            serverPing.setDescriptionComponent(new TextComponent(BungeeStatusMotd.getOfflineMotd()));
        }
    }

}
