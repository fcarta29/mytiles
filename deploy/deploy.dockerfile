FROM fcarta29/mytiles-build:latest
MAINTAINER "Frank Carta <fcarta@byteknowledge.com>"

# Copy Over the deployment folder
WORKDIR /home
COPY . .
