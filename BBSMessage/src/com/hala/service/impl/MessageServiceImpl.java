package com.hala.service.impl;

import java.util.List;

import com.hala.dao.IMessageDao;
import com.hala.dao.IUserDao;
import com.hala.dao.impl.MessageDaoImpl;
import com.hala.dao.impl.UserDaoImpl;
import com.hala.entity.Message;
import com.hala.entity.Pager;
import com.hala.entity.User;
import com.hala.service.IMessageService;

public class MessageServiceImpl implements IMessageService {
	
	IUserDao ud = new UserDaoImpl();
	IMessageDao md = new MessageDaoImpl();

	@Override
	public int sendMsg(Message msg, String toUserName) {
		// 1.根据接收者名字获得接收者id
		User toUser=ud.queryByName(toUserName);
		if(toUser!=null) {
			long toId=toUser.getId();
			msg.setReceiveId(toId);//将接收者id设置到Message对象中
		}else {
			return -1;
		}
		// 2.将数据插入数据库
		IMessageDao md=new MessageDaoImpl();
		return md.insert(msg);
	}

	@Override
	public List<Message> getAllMessageById(Pager p, long id) {
		// 4.设置总记录数		
		if (p.getRecordCount() == 0) {
			// 设置总长
			int count = md.queryAllSize(id);// 获取当前用户的所有message数量
			p.setRecordCount(count);
		}
		// 5.计算起始和结束位置
		//如果用Oracle方式检索可以用start,end
		//如果用MySql的Limit检索则要用start,number
		int start = (p.getCurrentPage() - 1) * Pager.PAGE_RECORD;
		int end = start + Pager.PAGE_RECORD;
		int number=Pager.PAGE_RECORD;
		// 6.根据起始位置和用户id查询数据库,返回对应的一个集合
		List<Message> result = md.serachAllMessages(start, number, id);
		return result;
	}

	@Override
	public Message readMsg(long id) {
		// TODO Auto-generated method stub
		//1.根据id修改state状态（由1改为0）
		md.updateState(id);
		//2.根据id获取Message对象
		Message msg=md.queryById(id);
		return msg;
	}

}
