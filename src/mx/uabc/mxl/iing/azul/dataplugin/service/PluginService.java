package mx.uabc.mxl.iing.azul.dataplugin.service;
/*
    Copyright (C) 2017  Jesús Donaldo Osornio Hernández
    Copyright (C) 2017  Luis Alejandro Herrera León
    Copyright (C) 2017  Gabriel Alejandro López Morteo

    This file is part of DataPlugin.

    DataPlugin is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    DataPlugin is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DataPlugin.  If not, see <http://www.gnu.org/licenses/>.
 */

import mx.uabc.mxl.iing.azul.dataplugin.registry.PluginManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Restful resource to make the methods of the {@link PluginManager} available via REST methods.
 *
 * @author jdosornio
 * @version %I%
 */
@Path("/p")
public class PluginService {

    /**
     * Method for executing a plugin given its name and with the given arguments, if any
     *
     * @param pluginName the name of the plugin to be executed
     * @param args the argument(s) to be sent to the plugin to be executed
     *
     * @return A message indicating the information of the requested execution
     */
    @Path("exec")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String executePlugin(@FormParam("name") String pluginName, @FormParam("args") String args) {
        PluginManager.executePlugin(pluginName, args.split(","));

        return "Plugin [" + pluginName + "] sent with args [" + args + "] for async execution...";
    }

    /**
     * Gets the names of all the application registered plugins
     *
     * @return a list of strings representing the names of all the application registered plugins
     */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listPlugins() {
        return PluginManager.listPlugins().toString();
    }

    /**
     * Gets the metadata of a specific plugin given its name
     *
     * @param pluginName the name of the plugin
     *
     * @return A string with the metadata of the requested plugin
     */
    @Path("desc/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String showPluginInfo(@PathParam("name") String pluginName) {
        return PluginManager.showInfo(pluginName);
    }
}