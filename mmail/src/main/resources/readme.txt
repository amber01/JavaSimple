1. 项目结构
common //常量、全局异常都放到这个包里面
controller
dao   //dao层在最下面和db交互，然后上边是service，service的最上层是controller层
pojo  //pojo是简单的数据库的一个对象，在上一层是通过vo进行封装然后返给controller再反给前端进行展示。当然也可以在pojo的上层使用bo，bo再上层使用vo。
service
util  //工具类都放到这里
vo  //view object或者value object或者再复杂的业务还可以加一次bo。vo最下面有一个pojo。

2. mybatis三剑客
  *mybatis-generator
  1）mybatis-generator根据我们的数据库自动生成pojo和dao还有对应的xml文件。pojo里面放的是和db里面一一对应的字段，dao层是一个接口
     给service调用，xml是这个dao层接口的实现。