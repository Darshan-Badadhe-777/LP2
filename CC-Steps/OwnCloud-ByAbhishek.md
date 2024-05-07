

### OwnCloud using Docker Commands

```bash
sudo su -
```
```bash
apt-get install docker -y
 ```
 ```bash
 apt-get install docker.io -y
 ```
 ```bash
 service docker start
 ```
 ```bash
 dcoker --version
 ```
################################################

------now on google search for "install docker compose", click "Overview of Installing Docker Compose",scroll down and you will see "install the Compose standalone" and click on it, now run that two installation commands , first command will be given as => 
```bash
curl -SL https://github.com/docker/compose/releases/download/v2.27.0/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose 
 ```
 second command will be given as => 
```bash
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
 ```
################################################



```bash
chmod -R 777 /usr/local/bin/docker-compose /usr/bin/docker-compose
 ```

------now on google search for "installing owncloud using docker" , click on "Installing with Docker- OwnCloud Documentation", scroll down you will see "Docker Compose", ther are 3 points , do/perform that 3 points ------

```bash
ifconfig
 ```

------if "ifconfig" not found ,then install it ------

------you will see 192.something , copy that ip address and paste it in .env file inplace of "localhost"------

```bash
 cd /home/abhishek/owncloud-docker-server
  ```

-------in above command inplace of "abhishek" whatever your system's username is there, that must be used------


```bash
 docker-compose up -d
  ```

------now go in browser and go to "localhost:8080" and sign in by username: admin and password:admin , in phone you can put ip address 192.something:8080, make sure that other device(for e.g. phone) is connected with same WiFi or LAN connection --------

