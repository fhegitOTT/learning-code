/**
 * 应用代码: WAVEFLY
 * 应用名称: WAVEFLY PLATFORM
 * 应用简码: WAVEFLY
 * 应用简称: WAVEFLY PLATFORM
 * 版 本 号: 1.0
 * 版权声明: Copyright (c) 2006 WAVEFLY SOFTWARE
 */
package com.netcom.nkestate.framework.cache;

/**
 * <p>同步对象</p>。
 * <p>感谢并发大师Doug Lea提供的算法，使我在面临高并发的系统时找到应对之策。
 * 
 * @author 崔晓东
 */
public class SyncObject {
	/** 同步锁 */
	protected Mutex m_lock = new Mutex();
	
	/**
	 * 构造同步对象。
	 */
	public SyncObject() {
		super();
	}
	
	/**
	 * 锁定对象。
	 * @return 如果成功，返回true，否则返回false。
	 */
	public boolean lock() {
		try {
			m_lock.acquire();
			return true;
		} catch(InterruptedException e) {
			return false;
		}
	}

	/**
	 * 尝试锁定对象。
	 * @param msecs 尝试的毫秒数。
	 * @return 如果成功返回true，否则返回false。
	 */
	public boolean lock(long msecs) {
		try {
			return m_lock.attempt(msecs);
		} catch(InterruptedException e) {
			return false;
		}
	}
	
	/**
	 * 释放锁。
	 */
	public void unlock() {
		m_lock.release();
	}
	
	public void trace(Object msg) {
		//System.out.println(msg);
	}
} 
