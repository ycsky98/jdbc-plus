# jdbc-plus
jdbc-plus是一款原生插件

## 拥有Mybatis-Plus的逻辑构建功能,能快速构建出单表查询逻辑

    Logic logic = new Logic();//构建出逻辑对象
    logic.eq(key, val);//eq操作
    logic.lt(key, val);//lt操作......
    ........
###以上是基本逻辑构造,其余返回值还是参照原生jdbc的做法,后续将陆续封装构建表模型;

## 如何完整的使用该插件
    HikariConfig hikariConfig = new HikariConfig();
    SqlWhereBuild build = new SqlWhereBuild();
    BusExecutor<Integer> busExecutor = new BusExecutor<>(hikariConfig, build);

    busExecutor.autoCommit(true);
    busExecutor.execute("SELECT COUNT(1) FROM admin", new Logic().eq("username", "admin").end());

##感谢各位大佬支持!

## 支持原生sql查询

当前版本支持内容
