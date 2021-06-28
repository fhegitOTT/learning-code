/**
 * ================================================================
 * 软件代码：NKFRAMEWORKX
 * 软件名称：南康框架
 * 软件版本：3.0
 * 版权所有：上海南康科技有限公司 2005-2010
 * 研发团队：研发中心框架团队
 * 研发时间：2010年2-6月
 * ================================================================
 */

package com.netcom.nkestate.framework.cache;

/**
 * A simple non-reentrant mutual exclusion lock. The lock is free upon
 * construction. Each acquire gets the lock, and each release frees it.
 * Releasing a lock that is already free has no effect.
 * <p>
 * This implementation makes no attempt to provide any fairness or ordering
 * guarantees. If you need them, consider using one of the Semaphore
 * implementations as a locking mechanism.
 * <p>
 * <b>Sample usage </b> <br>
 * <p>
 * Mutex can be useful in constructions that cannot be expressed using java
 * synchronized blocks because the acquire/release pairs do not occur in the
 * same method or code block. For example, you can use them for hand-over-hand
 * locking across the nodes of a linked list. This allows extremely fine-grained
 * locking, and so increases potential concurrency, at the cost of additional
 * complexity and overhead that would normally make this worthwhile only in
 * cases of extreme contention.
 * 
 * <pre>
 * class Node {
 * 	Object item;
 * 
 * 	Node next;
 * 
 * 	Mutex lock = new Mutex(); // each node keeps its own lock
 * 
 * 	Node(Object x, Node n) {
 * 		item = x;
 * 		next = n;
 * 	}
 * }
 * 
 * class List {
 * 	protected Node head; // pointer to first node of list
 * 
 * 	// Use plain java synchronization to protect head field.
 * 	//  (We could instead use a Mutex here too but there is no
 * 	//  reason to do so.)
 * 	protected synchronized Node getHead() {
 * 		return head;
 * 	}
 * 
 * 	boolean search(Object x) throws InterruptedException {
 * 		Node p = getHead();
 * 		if (p == null)
 * 			return false;
 * 
 * 		//  (This could be made more compact, but for clarity of illustration,
 * 		//  all of the cases that can arise are handled separately.)
 * 
 * 		p.lock.acquire(); // Prime loop by acquiring first lock.
 * 		//    (If the acquire fails due to
 * 		//    interrupt, the method will throw
 * 		//    InterruptedException now,
 * 		//    so there is no need for any
 * 		//    further cleanup.)
 * 		for (;;) {
 * 			if (x.equals(p.item)) {
 * 				p.lock.release(); // release current before return
 * 				return true;
 * 			} else {
 * 				Node nextp = p.next;
 * 				if (nextp == null) {
 * 					p.lock.release(); // release final lock that was held
 * 					return false;
 * 				} else {
 * 					try {
 * 						nextp.lock.acquire(); // get next lock before releasing current
 * 					} catch (InterruptedException ex) {
 * 						p.lock.release(); // also release current if acquire fails
 * 						throw ex;
 * 					}
 * 					p.lock.release(); // release old lock now that new one held
 * 					p = nextp;
 * 				}
 * 			}
 * 		}
 * 	}
 * 
 * 	synchronized void add(Object x) { // simple prepend
 * 		// The use of `synchronized'  here protects only head field.
 * 		// The method does not need to wait out other traversers 
 * 		// who have already made it past head.
 * 
 * 		head = new Node(x, head);
 * 	}
 * 
 * 	// ...  other similar traversal and update methods ...
 * }
 * </pre>
 * 
 * <p>
 */

public class Mutex {

	/** 锁的状态 * */
	protected boolean inuse_ = false;

	/**
	 * 请求锁。
	 * 
	 * @throws InterruptedException 如果发生中断异常。
	 */
	public void acquire() throws InterruptedException {
		if (Thread.interrupted())
			throw new InterruptedException();
		synchronized (this) {
			try {
				while (inuse_)
					wait();
				inuse_ = true;
			} catch (InterruptedException ex) {
				notify();
				throw ex;
			}
		}
	}

	/**
	 * 释放锁。
	 */
	public synchronized void release() {
		inuse_ = false;
		notify();
	}

	/**
	 * 尝试申请锁。
	 * 
	 * @param msecs 等待的毫秒数。
	 * @return 如果成功返回true，否则返回false。
	 * @throws InterruptedException 如果发生中断异常。
	 */
	public boolean attempt(long msecs) throws InterruptedException {
		if (Thread.interrupted())
			throw new InterruptedException();
		synchronized (this) {
			if (!inuse_) {
				inuse_ = true;
				return true;
			} else if (msecs <= 0)
				return false;
			else {
				long waitTime = msecs;
				long start = System.currentTimeMillis();
				try {
					while (true) {
						wait(waitTime);
						if (!inuse_) {
							inuse_ = true;
							return true;
						} else {
							waitTime = msecs
									- (System.currentTimeMillis() - start);
							if (waitTime <= 0)
								return false;
						}
					}
				} catch (InterruptedException ex) {
					notify();
					throw ex;
				}
			}
		}
	}
}

