FROM alpine:latest
MAINTAINER "Frank Carta <fcarta@byteknowledge.com>"

# Install libraries
RUN echo "Installing Libraries" \
    && apk add --update git bash openssh python3 curl findutils jq wget
RUN echo "Installing Ansible Libs" \
    && apk add --update sudo python py-pip openssl ca-certificates \
    && apk add --update --virtual build-dependencies python-dev libffi-dev openssl-dev build-base
RUN echo "Installing AWS Libs" \
    && apk add --update groff less

ENV KUBECTL_VERSION=v1.12.7
WORKDIR /

# Install AWS CLI
RUN echo "Installing AWS CLI" \
    && pip install --upgrade awscli

# Install Kubectl
RUN echo "Installing Kubectl" \
    && wget -q https://storage.googleapis.com/kubernetes-release/release/${KUBECTL_VERSION}/bin/linux/amd64/kubectl \
    && chmod +x ./kubectl \
    && mv kubectl /usr/local/bin/kubectl \
    && which kubectl \
    && kubectl version --short --client

# Install Istio
RUN echo "Installing Istioctl" \
    && wget -q https://s3-us-west-2.amazonaws.com/nsxsm/istio/linux/istioctl \
    && chmod +x ./istioctl \
    && mv istioctl /usr/local/bin/istioctl \
    && which istioctl \
    && istioctl version

# Install Helm
RUN echo "Installing Helm" \
    && curl -o get_helm.sh https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get \
    && chmod +x ./get_helm.sh \
    && ./get_helm.sh \
    && rm ./get_helm.sh

# Leave Container Running for SSH Access - SHOULD REMOVE
ENTRYPOINT ["tail", "-f", "/dev/null"]
