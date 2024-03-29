package it.mcblock.mcblockit.api;

/**
 * A player on the server
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
 * 
 */
public interface MCBIPlayer {
    /**
     * Get the player's IP
     * 
     * @return the IP address of the player eg. 192.0.0.1
     */
    public String getIP();

    /**
     * Get the player's username
     * 
     * @return the username of the player
     */
    public String getName();

    /**
     * Kick the player off the server
     * 
     * @param reason
     *            Message to display to the player
     */
    public void kick(String reason);

    /**
     * Message the user if they are an admin
     * Done this way to allow for sync'd permission checking
     * 
     * @param message
     *            The messages to send
     */
    public void messageIfAdmin(String message);
}
