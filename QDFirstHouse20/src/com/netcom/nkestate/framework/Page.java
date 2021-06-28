/**
 *<p>Page.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：分页对象</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-4-24</p>
 *
 */
package com.netcom.nkestate.framework;

import java.io.Serializable;

/**
 * 修改记录：YYYY-MM-DD AUTHOR MESSAGE
 */
public class Page implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	protected long recordCount; // 总记录数
	protected int pageCount; // 总页数
	protected int pageSize = 10; // 默认每页10条记录
	protected int currentPage = 1; // 默认为第一页
	protected boolean hasNext = false; // 是否有下一页
	protected boolean hasPre = false; // 是否有上一页
	
	public Page() {
	}

	// 构造函数
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	// 得到总记录数
	public long getRecordCount() {
		return recordCount < 0 ? 0 : recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	// 得到总页数
	public int getPageCount() {
		pageCount = (int) (recordCount / getPageSize()); // 计算分页信息
		if(recordCount % getPageSize() > 0)
			pageCount = pageCount + 1;
		
		return pageCount < 0 ? 0 : pageCount;
	}
	
	// 得到每页记录数
	public int getPageSize() {
		return pageSize <= 0 ? 20 : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 得到当前页码
	public int getCurrentPage() {
		return currentPage <= 0 ? 1 : currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// 是否有下一页
	public boolean hasNext() {
		if(currentPage < getPageCount())
			hasNext = true;
		return hasNext;
	}	
    
	// 是否有上一页
	public boolean hasPre() {
		if(currentPage > 1)
			hasPre = true;
		return hasPre;
	}
	
}
