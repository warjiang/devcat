# 安装thrift-compiler(0.11.0)
mac上可以直接通过`brew install thrift`来安装thrift
也可以从源码安装thrift

# nodejs-nodejs
rpc的客户端和服务端均使用nodejs. 
```
// 将thrift IDL文件翻译成nodejs可用的JS文件
cd /path/to/backend/rpc
thrift -r -o nodejs --gen js:node ./tutorial/tutorial.thrift
// 启动rpc的服务端
cd /path/to/backend/rpc/
node server.js

// 使用rpc的客户端
cd /path/to/backend/rpc/
node client.js
```

# java-java
rpc的客户端和服务端均使用java
```
// 将thrift IDL文件翻译成nodejs可用的JS文件
cd /path/to/rpc
thrift -r --gen js:nodejs ./tutorial/tutorial.thrift
// 启动rpc的服务端
// 使用rpc的客户端
```