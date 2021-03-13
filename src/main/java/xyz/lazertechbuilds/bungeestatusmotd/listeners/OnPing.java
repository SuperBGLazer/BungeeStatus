/*
 * Bungee Status Motd changes the server motd based on weather or not the downstream server is online or not.
 *     Copyright (C) 2021  Breyon Gunn
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
