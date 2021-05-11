# redis_pub_sub
使用redis实现消息的发布和订阅功能
1.redis.conf配置文件修改
notify-keyspace-events "Ex"，能够监听过期的key事件
tcp-keepalive 60，设置客户端心跳监听时间，官方推荐60秒
git本地拉取远端，git pull origin master --allow-unrelated-histories
