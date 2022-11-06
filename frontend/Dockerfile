### STAGE 1: Build ###
FROM node:12.7-alpine AS build

ENV VUE_APP_ROOT_API=http://localhost:1234/api/v1

COPY package.json ./

RUN npm install

COPY . .

RUN npm run build

### STAGE 2: Run ###
FROM nginx:alpine

COPY --from=build /dist/ /usr/share/nginx/html

COPY nginx.conf /etc/nginx/nginx.conf