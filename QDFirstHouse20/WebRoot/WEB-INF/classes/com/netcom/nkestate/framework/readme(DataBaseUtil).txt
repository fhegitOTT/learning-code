                             DataBaseUtil.java方法说明

1. 新增一个对象，返回值是新增对象的ID，如果是复合主键返回值永远为 Long.MAX_VALUE,日期型主键，返回最大值-1,Long.MAX_VALUE-1,字符型主键，返回最大值;
    public static long add(AbstractBaseVO vo, Connection conn) throws Exception;
	
2. 更新一个对象
    2.1 (更新全部属性，ID和can_update=false除外)
	    public static long update(AbstractBaseVO vo, Connection conn) throws Exception ;
	    
	2.2 更新一个对象(更新对象中的部分属性)
        public static long update(Class clazz, long id, List<MetaField> fields, Connection conn) throws Exception ;
        
    2.3 更新一个对象(更新对象中的部分属性)
	    public static long update(Class clazz, String id, List<MetaField> fields, Connection conn) throws Exception ；
	
	2.4 更新VO对象中非NULL的字段（仅适用于单主键字段）------2015.08.04增加---- 
	   public static int updateWithoutNullField(AbstractBaseVO vo,Connection conn) throws Exception    
	    
	     

3.根据条件批量更新同类对象
	public static long update(Class clazz, List<MetaField> whereFileds, List<MetaField> modifyFields, Connection conn) throws Exception ;

4.根据ID加载一个对象 
    4.1
	    public static AbstractBaseVO find(Class clazz, long id, Connection conn) throws Exception;
    4.2
	    public static AbstractBaseVO find(Class clazz, String id, Connection conn) throws Exception;
	4.3 根据复合主键加载一个对象 
	    public static AbstractBaseVO find(Class clazz, Map<String,String> keys, Connection conn) throws Exception ;

5.根据VO对象中的主键值查询对象	
	public static AbstractBaseVO find(AbstractBaseVO vo, Connection conn) throws Exception ;

6.根据参数查询对象，调用时page、orders可以为空
	6.1 查询全部
	public static List<AbstractBaseVO> findByAttibutes(Class clazz,List<MetaFilter> params,Connection conn) throws Exception ;
	6.2 带有分页
	public static List<AbstractBaseVO> findByAttibutes(Class clazz, List<MetaFilter> params, Page page, Connection conn) throws Exception ;
	6.3 带有条件、排序、分页
	public static List<AbstractBaseVO> findByAttibutes(Class clazz,List<MetaFilter> params,List<MetaOrder> orders,Page page,Connection conn) throws Exception;
	
    6.4 带有排序字段,nkSql 类似hql语句,用于组织多种复杂条件,格式为 vo属性1>... or (vo属性2<.. and vo属性2>..)
    public static List<AbstractBaseVO> findByAttibutes(Class clazz, List<MetaFilter> params,String nkSql, Page page, List<MetaOrder> orders, Connection conn)throws Exception ;

    	
7.执行SQL语句（SQL语句中带有查询条件占位符），返回List
    7.1 不带分页
	public static List<AbstractBaseVO> select(String sql, List<Object> params,Class clazz, Connection conn) throws Exception;
	
    7.2 分页
	public static List<AbstractBaseVO> select(String sql,List<Object> params,Class clazz,Page page,Connection conn) throws Exception;
	
	7.3 分页 + 排序 （注:SQL中不能带 order by 关键字）
	public static List<AbstractBaseVO> select(String sql,List<Object> params,List<MetaOrder> orders,Class clazz,Page page,Connection conn) throws Exception {
	
	
8.加载一个VO对象（加载VO时，会自动把rs中的值赋值到VO中的属性，如果匹配不上，就会放在attribute中）
    8.1 加载VO，根据数据库字符集，对String做了转码
	static public AbstractBaseVO loadVO(Class clazz, ResultSet rs) throws Exception ;
	
	8.2加载一个对象，并自动加载字典表
	static public AbstractBaseVO loadVOWidthDictValue(Class clazz, ResultSet rs, Connection conn) throws Exception

9.删除一个vo对象
    9.1根据VO对象删除一个对象
	public static long delete(AbstractBaseVO vo, Connection conn) throws Exception;	
	
	9.2根据ID删除一个对象
	public static int delete(Class clazz, long id, Connection conn) throws Exception ;
	
	9.3根据ID删除一个对象 
	public static int delete(Class clazz, String id, Connection conn) throws Exception;
	
	9.4根据复合主键删除对象
	public static int delete(Class clazz, Map<String , String> keys, Connection conn) throws Exception
	
	9.5根据过滤条件删除对象
	public static int delete(Class clazz, List<MetaFilter> params, Connection conn) throws Exception
	
10. 执行SQL语句
    public static int execute(String sql, List<Object> params, Connection conn) throws Exception;
    
11. 执行SQL语句，返回结果记录集合
    11.1 执行SQL语句，返回ResultSet
    public static ResultSet executeQuery(String sql, List<Object> params, Connection conn,PreparedStatement pstmt) throws Exception;
    
    11.2 执行SQL语句，返回List<Map<String , Object>> ，根据数据库字符集，对String做了转码
    public static List<Map<String , Object>> executeQuery(String sql,List<Object> params,Connection conn) throws Exception ;

12. 单表统计
   12.1 public static DataGrid statByAttibutes(Class clazz,List<MetaStatistic> stats,List<String> groupfields,List<MetaFilter> params,Connection conn) throws Exception ;
  