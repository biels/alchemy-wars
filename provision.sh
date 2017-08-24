#!/bin/bash
export DEBIAN_FRONTEND=noninteractive

sudo apt-get update
# sudo apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual -y

sudo apt-get install \
    linux-image-extra-$(uname -r) \
    linux-image-extra-virtual -y
    
#docker    
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common -y
    
sudo apt-get install curl -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable" -y
sudo apt-get update
sudo apt-get install docker-ce -y
docker version
#docker noroot
sudo groupadd docker

#docker autostart
sudo systemctl enable docker

#java
sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get update

echo debconf shared/accepted-oracle-license-v1-1 select true | \
sudo debconf-set-selections
echo debconf shared/accepted-oracle-license-v1-1 seen true | \
sudo debconf-set-selections

sudo apt-get install oracle-java8-installer --force-yes -y

#node
curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
sudo apt-get install -y nodejs build-essential

#yarn
curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
echo "deb https://dl.yarnpkg.com/debian/ stable main" | sudo tee /etc/apt/sources.list.d/yarn.list

sudo apt-get update && sudo apt-get install yarn -y

#apollo
npm install -g apollo-codegen