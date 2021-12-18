# 
#
# STAGE 1: Build sources
#
#
FROM node:12.16.3 as node

WORKDIR /usr/src/app
COPY ./ ./


# RUN apk add g++ make python3
RUN npm i -g npm@6.13.7
RUN npm ci --loglevel=error
RUN npm run build


#
#
# STAGE 2: Launch server
#
#
FROM nginx:1.14.0
COPY --from=node /usr/src/app/dist /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]