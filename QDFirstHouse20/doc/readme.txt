青岛一手房系统（2019-05-23创建）
      (从CVS库迁移到GIT库, 原CVS:E:\CVSRoot\QingDao\QDMarket20\04_SRC\01_FirstHouse)

包括2个数据源:
   1) 一手房数据源；
   2）登记、楼盘表数据源。

1、在Tomcat的context.xml文件里，增加数据库连接，如下
	<Resource
               name="jdbc/MiniConnection"
               auth="Container"
               type="javax.sql.DataSource"
               driverClassName="oracle.jdbc.driver.OracleDriver"
               maxIdle="30"
               maxWait="5000"
               validationQuery="SELECT 1 FROM DUAL"
               testOnBorrow="true"
               testOnReturn="true"
               testWhileIdle="true"
               username="firsthand"
               password="firsthand"
               url="jdbc:oracle:thin:@192.168.1.176:1521:qdmarket"
               maxActive="100"/>   
     
     <Resource
               name="jdbc/RealEstateConnection"
               auth="Container"
               type="javax.sql.DataSource"
               driverClassName="oracle.jdbc.driver.OracleDriver"
               maxIdle="30"
               maxWait="5000"
               validationQuery="SELECT 1 FROM DUAL"
               testOnBorrow="true"
               testOnReturn="true"
               testWhileIdle="true"
               username="realestate"
               password="realestate"
               url="jdbc:oracle:thin:@192.168.1.176:1521:qdmarket"
               maxActive="20"/>          
    

    
    数据库（中文字符集）---公司
    IP:192.168.1.176  sid:qdmarket
    secondhouse/secondhouse
               
2、在Java工程中的Java Code Style中，引入NKRAI_Code_Template.xml和 NKRAI_Code_Formatter.xml

3、com.netcom.nkestate.framework包下的文件修改，只能由刀斌来修改。

               