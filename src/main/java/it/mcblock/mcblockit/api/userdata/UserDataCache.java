package it.mcblock.mcblockit.api.userdata;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Cache for user data
 * 
 * @author Matt Baxter
 * 
 *         Copyright 2012 Matt Baxter
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License");
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 */
public class UserDataCache {
    private final File cacheFolder;
    private final Gson gson;

    public UserDataCache(File dataFolder) {
        this.gson = new Gson();
        this.cacheFolder = new File(dataFolder, "cache");
        if (!this.cacheFolder.exists()) {
            this.cacheFolder.mkdirs();
        } else if (!this.cacheFolder.isDirectory()) {
            System.out.println("Critical error: " + this.cacheFolder + " is not a directory");
        }
    }

    public void addUserData(UserData data) {
        final String name = data.getUsername().toLowerCase();
        final File file = new File(this.cacheFolder, name + ".json");
        if (file.exists()) {
            file.delete();
        }
        try {
            final BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(this.gson.toJson(data));
            output.close();
        } catch (final IOException e) {
            System.out.println("Failed to write " + file);
            e.printStackTrace();
        }
    }

    public void delUserCache(String name) {
        final File file = new File(this.cacheFolder, name.toLowerCase() + ".json");
        if (file.exists()) {
            file.delete();
        }
    }

    public UserData getUserData(String name) {
        final String search = name.toLowerCase();
        UserData data = null;
        final File file = new File(this.cacheFolder, search + ".json");
        if (file.exists()) {
            final StringBuilder builder = new StringBuilder();
            try {
                final BufferedReader input = new BufferedReader(new FileReader(file));
                String line;
                while ((line = input.readLine()) != null) {
                    builder.append(line);
                }
                input.close();
            } catch (final IOException e) {
                System.out.println("Failed to read " + file);
                e.printStackTrace();
            }
            try {
                data = this.gson.fromJson(builder.toString(), UserData.class);
            } catch (final JsonSyntaxException e) {
                //Boggle
            }
        }
        return data;
    }

    public long lastMod(String name) {
        final File file = new File(this.cacheFolder, name.toLowerCase() + ".json");
        if (file.exists()) {
            return file.lastModified();
        }
        return 0;
    }
}
