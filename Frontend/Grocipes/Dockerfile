FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install --legacy-peer-deps
COPY . .
RUN npm run build --prod
FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build /app/dist/grocipes /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]