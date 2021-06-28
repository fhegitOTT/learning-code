AbstractBaseDAO实现了以下方法，可在实现DAO中直接使用

   /**
	 * 功能描述：新增一个对象
	 * @param model
	 * @return 对象编号,如果是字符型主键，返回Long.MAX_VALUE
	 * @throws Exception
	 */
	public long add(IModel model) throws Exception;


	/**
	 * 功能描述：更新一个对象
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(IModel model) throws Exception;
	
	/**
	 * 功能描述：删除一个对象
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int delete(IModel model) throws Exception;

	
	/**
	 * 功能描述：查询一个对象
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public IModel find(IModel model) throws Exception;

	/**
	 * 功能描述：根据ID查找一个对象,必须是单一主键
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IModel find(Class clazz, long id) throws Exception;