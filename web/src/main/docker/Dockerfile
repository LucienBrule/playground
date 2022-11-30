FROM registry.access.redhat.com/ubi8/nginx-120

WORKDIR /opt/app-root/src

COPY ./build/distributions/ /opt/app-root/src/

EXPOSE 8080
ENV PORT 8080
CMD ["nginx", "-g", "daemon off;"]

