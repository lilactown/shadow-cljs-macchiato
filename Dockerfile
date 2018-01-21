FROM mhart/alpine-node:latest

MAINTAINER Your Name <you@example.com>

# Create app directory
RUN mkdir -p /server
WORKDIR /server

# Install app dependencies
COPY package.json /server
RUN npm install pm2 -g
RUN npm install

# Bundle app source
COPY target/release/server.js /server/server.js
COPY public /server/public

ENV HOST 0.0.0.0

EXPOSE 3000
CMD [ "pm2-docker", "/server/server.js" ]
