package com.hala.service;

import java.util.List;

import com.hala.entity.Message;
import com.hala.entity.Pager;

public interface IMessageService {
	
	/**
	 * 发送消息的业务逻辑
	 * @param msg(发送者id，标题，正文，发送时间)
	 * @param toUserName（接收者的名字）
	 * @return 1成功 其他失败
	 */
	public int sendMsg(Message msg,String toUserName);
	
	/**
	 * 根据用户id和分页要求返回一个特定数量的属于该用户id的数据集合
	 * @param p 
	 * @param id
	 * @return 成功返回List集合 失败返回null
	 */
	public List<Message> getAllMessageById(Pager p,long id);

	/**
	 * 读取消息
	 * @param mid
	 * @return 读取后的消息对象
	 */
	public Message readMsg(long mid);

}
