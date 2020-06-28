How to run (for testing and development)
=========================================
1. run make
--------------

2. create variables.env and write your configuration
-----------------------------------------------------
An example configuration would be:

```
    - BITCOIN_PORT_8332_TCP_PORT=9245
    - ADMIN_ADDRESS=x
    - ADMIN_UUID=x
    - BITCOIN_ENV_USERNAME=x
    - BITCOIN_ENV_PASSWORD=x
    - BITCOIN_NODE_HOST=192.168.0.11
    - BUYIN_AMOUNT=1.00
    - LIVES_PERBUYIN=1
    - SPAWN_PROTECT_RADIUS=25
    - LOOT_RADIUS_MIN=500
    - LOOT_RADIUS_MAX=500
    - LOOT_ANNOUNCE_RADIUS=100
    - ADDRESS_URL=https://explorer.lbry.com/address/
    - TX_URL=https://explorer.lbry.com/tx/
    - COINGECKO_CRYPTO=lbry-credits
    - DENOMINATION_NAME=LBCs
    - SERVER_NAME=LBRYQuest
    - CRYPTO_DECIMALS=6
    - DISPLAY_DECIMALS=2
    - VOTE_URL
    - MIN_FEE=1.2
    - MAX_FEE=15
    - CRYPTO_TICKER=LBC
    - VOTE_API_KEY
    - DISCORD_URL
    - DISCORD_HOOK_CHANNEL_ID
    - DISCORD_HOOK_URL
    - ADMIN_ADDRESS
    - ADMIN2_ADDRESS
    - USD_DECIMALS=0.000
    - CONFS_TARGET=1
```

3. run docker-compose up
--------------------------

port to docker
----------------------
sudo iptables -t nat -L -n

sudo iptables -t nat -A POSTROUTING --source 172.17.0.3 --destination 172.17.0.3 -p tcp --dport 25565 -j MASQUERADE

----------------------

How to run without docker
=========================================
1. Download [Spigot 1.16.1](https://getbukkit.org/get/fDoqnvFqdhjFcURmY7Oqtzu0giKFAiyY)
2. Start server with ```java -jar Spigot-1.16.1.jar```
3. Copy LBRYQuest.jar to plugin folder
4. Set EULA.txt to true
5. Edit and run setenv.sh script
6. Make sure you have LBRYcrd and Redsi server running.
7. Edit server.properties to point to your local IP
8. Start server with ```java -jar Spigot-1.16.1.jar```
