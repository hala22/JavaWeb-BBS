package com.hala.dao;

import java.util.List;

import com.hala.entity.Message;

public interface IMessageDao {
	
	/**
	 * 插入Message到数据库
	 * @param msg(发送者id,接收者id,标题，内容，发送时间)
	 * @return 1成功 其他失败
	 */
	public int insert(Message msg);

	/**
	 * 根据id获取该用户消息的总数量
	 * @param id
	 * @return
	 */
	public int queryAllSize(long id);

	/**
	 * 获取消息集合
	 * @param start
	 * @param end
	 * @param id
	 * @return
	 */
	public List<Message> serachAllMessages(int start, int end, long id);

	/**
	 * 修改消息state
	 * @param id
	 * @return 1成功其他失败
	 */
	public int updateState(long id);

	/**
	 * 根据id获取message对象
	 * @param id
	 * @return
	 */
	public Message queryById(long id);

}
