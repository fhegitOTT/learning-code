                                 VO映射说明：

1.映射表、Sequence、ID方式，对类做注解
	@DBModel(tablename = "user_t", sequence = "seq_user_t", id = DBModel.AutoIncreaseID)
	public class SysUserVO extends AbstractBaseVO implements ISysUserVO {
	   ....	
	}

2.VO对象中的字段属性映射	
          对get方法做注解，如下
    @DBMeta(column="userid",name="用户编号",type="Long",primarykey="true")
	public long getUserID() {
		return userID;
	}	
	
	@DBMeta(column="username",name="用户姓名",type="String")
	public String getUserName() {
		return userName;
	}
	
	注解说明
	public @interface DBMeta {	
		public String column();     //数据库字段名
		public String name() default "";       //字段中文名
		public String type()        default "String"; //类型String, Date, DateTime, Long, Int, Double, Float, Blob, Clob 
		public String length()      default "0";      //长度
		public String not_null()    default "false";  //是否允许为空， true/false
		public String primarykey()  default "false";  //是否是主键, true/false
		public String can_update()  default "true";   //是否能更新该字段, true/false
		
		public String dict_table() default "";     //关联字典表，如common.ct_district，字典表要求有三个字段 code, name, status
    }
    
          映射外键对象
    @MappingVO(clazz = BlockVO.class, fkColumn = "blockid", autoload = "true")
	public BlockVO getBlock() {
		return blockVO;
	}
	//注解说明
	public @interface MappingVO {
		public Class clazz(); //映射对象类
	
		public String fkColumn(); //外键字段，对于DBObj就是主键
		
		public String autoload() default "true"; //是否自动加载
	}
	
	注：在findByAttibutes()方法中，不能用外键对象的属性作为过滤条件
	
	

3. 数据类型映射
        字段类型         Java类型
    Number    int / long / double
    varchar   String
    varchar2  String
    Date      java.util.Date
    DateTime  java.util.Date
    Clob      byte[]
    Blob      byte[]
   