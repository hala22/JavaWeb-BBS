package com.hala.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hala.dao.IMessageDao;
import com.hala.dao.IUserDao;
import com.hala.entity.Message;
import com.hala.entity.User;
import com.hala.utils.JDBCUtils;

public class MessageDaoImpl implements IMessageDao {
	
	IUserDao ud=new UserDaoImpl();

	@Override
	public int insert(Message msg) {
		QueryRunner run=JDBCUtils.getQueryRunner();
		String sql="INSERT INTO "
				+ "mes(sendid,receiveid,title,msgcontent,msg_date,state) "
				+ "VALUES(?,?,?,?,?,1)";
		
		int row=-1;
		
		try {
			row=run.update(sql,msg.getSendId(),msg.getReceiveId(),msg.getTitle()
					,msg.getMsgContent(),msg.getMsg_date());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	
	//这里不使用QueryRunner对象，是因为QueryRunner查询出来返回一个对象
	//或者对象集合，这里只需要一个int类型的值，所以要用原始方法
	@Override
	public int queryAllSize(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "SELECT COUNT(*) FROM mes WHERE receiveid = ?";// 少写了
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);// 少写了
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public List<Message> serachAllMessages(int start, int number, long id) {
		QueryRunner run = JDBCUtils.getQueryRunner();
		//下面是oracle数据库的用法，因为它没有limit函数，只能用伪列的方式实现
//		String sql = "select ID,SENDID,TITLE,STATE,RECEIVEID,MSG_CREATE_DATE from("
//				+ "select rownum as r, ID,SENDID,TITLE,STATE,RECEIVEID,MSG_CREATE_DATE from MESSAGES "
//				+ "where RECEIVEID = ?)t " + "where t.r > ? and t.r <=?";
		
		//MySql可以使用limit函数达到分页的目的,query1别名必须起，否则报错
		//Limit第一个问号代表开始位置前一个如0，则是从1开始
		//Limit第二个问号代表有几个
		String sql="SELECT * FROM(SELECT id,sendid,receiveid,title,msg_date,state FROM"
				+ " mes WHERE receiveid=?) query1 LIMIT ?,? ";
		List<Message> list = null;
		try {
			list = run.query(sql, new BeanListHandler<>(Message.class), id, start, number);
			for (int i = 0; i < list.size(); i++) {
				setSendUserById(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public int updateState(long id) {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "UPDATE mes SET state = 0 WHERE id = ?";
		int row = -1;
		try {
			row = run.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}


	@Override
	public Message queryById(long id) {
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT * FROM mes WHERE id = ?";
		Message msg = null;
		try {
			msg = run.query(sql, new BeanHandler<>(Message.class), id);
			setSendUserById(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	//封装的填充代码,根据id取出不带密码的user
	private void setSendUserById (Message msg) throws SQLException {
		if (msg.getSendId() == 0) {
			return;
		}
		QueryRunner run = JDBCUtils.getQueryRunner();
		String sql = "SELECT id,username,email FROM user WHERE id = ?";
		User sendUser = run.query(sql, new BeanHandler<>(User.class), msg.getSendId());
		msg.setSendUser(sendUser);
	}

}
