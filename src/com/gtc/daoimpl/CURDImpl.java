package com.gtc.daoimpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.gtc.dao.CURD;
import com.gtc.domain.Contact;
import com.gtc.util.BeansU;

public class CURDImpl implements CURD {
	private static SimpleJdbcTemplate simpleJdbcTemplate;
	private static final String TABLE_NAME="contact";
	private String sql;
	
	
	static{
		
		Properties properties=new Properties();
		
			try {
				properties.load(CURDImpl.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
				DataSource dataSource=BasicDataSourceFactory.createDataSource(properties);
				simpleJdbcTemplate=new SimpleJdbcTemplate(dataSource);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		
	} 
	
	@Override
	public <T> List<T> getAllInfo(Class<T> clazz) {
		// TODO Auto-generated method stub
		
		String paramStr=BeansU.beanParamsToString(Contact.class);
		sql="select * from "+ TABLE_NAME;
		ParameterizedRowMapper rm=ParameterizedBeanPropertyRowMapper.newInstance(clazz);
		//System.out.println(simpleJdbcTemplate.query(sql, rm));
		
		
		return simpleJdbcTemplate.query(sql, rm);
	}

	@Override
	public <T> T register(T t) {
		// TODO Auto-generated method stub
		
		sql="insert into "+TABLE_NAME+"("+BeansU.beanParamsToStringWithoutId(Contact.class)+") values ("+BeansU.beanParamsToSQLValueStringWithoutId(Contact.class)+")";
		//System.out.println("sql="+sql);
		GeneratedKeyHolder generatedKeyHolder=new GeneratedKeyHolder();
		SqlParameterSource paramSource=new BeanPropertySqlParameterSource(t);
		simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql, paramSource, generatedKeyHolder);
		if(generatedKeyHolder.getKey().intValue()<=0){
			t=null;
			return t;
		}
		try {
			BeanUtils.setProperty(t, "id", generatedKeyHolder.getKey().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			t=null;
			throw new RuntimeException("register返回结果写入对象"+ t.getClass().getName()+"失败");
		}
		
		return t;
	}

	@Override
	public <T> boolean queryUser(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryUser(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	@Override
	public <T> T queryUser(String id, Class<T> clasz) {
		// TODO Auto-generated method stub
		int rID=0;
		try {
			rID=Integer.parseInt(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("ID输入错误");
		}
		String paramStr=BeansU.beanParamsToString(Contact.class);
		sql="select "+paramStr+" from "+TABLE_NAME+" where id=?";
		ParameterizedRowMapper rm=ParameterizedBeanPropertyRowMapper.newInstance(clasz);
		
		
		return (T) simpleJdbcTemplate.query(sql,rm,rID).get(0);
	}


	@Override
	public <T> boolean updateUser(T oriUser, T targetT) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean updateUser(String id, T targetT) {
		// TODO Auto-generated method stub
		
		sql="update "+TABLE_NAME+" set name=:name,address=:address,gender=:gender,email=:email,phone=:phone where id=:id";
		SqlParameterSource spa=new BeanPropertySqlParameterSource(targetT);
		System.out.println(simpleJdbcTemplate.update(sql,spa));
		return false;
	}

	@Override
	public boolean deletUser(String id) {
		// TODO Auto-generated method stub
		//System.out.println("id="+id);
		sql="delete from "+TABLE_NAME+" where id=?";
		//System.out.println("删除结果"+simpleJdbcTemplate.update(sql, id));
		if(simpleJdbcTemplate.update(sql, id)>0)
			return true;
		return false;
	}

	@Override
	public <T> boolean deletUser(T t) {
		// TODO Auto-generated method stub
		try {
			Method method=t.getClass().getMethod("getId");
			
			return deletUser((String)method.invoke(t));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(this.getClass().getName()+"中获取getId方法失败");
		}
	}
}
