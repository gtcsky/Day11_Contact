package com.gtc.dao;

import java.util.List;



public interface CURD {
	
		public abstract  <T> List<T> getAllInfo(Class<T> clazz);
		public abstract <T> T register(T t);
	
		public abstract <T> boolean queryUser(T t);
		public abstract boolean queryUser(String name);
		public abstract <T> T queryUser(String id,Class<T> clasz);
	
		public abstract <T> boolean updateUser(T oriUser,T targetT);
		public abstract <T> boolean updateUser(String id,T targetT);
	
		public abstract boolean deletUser(String id);

		
		public abstract <T> boolean deletUser(T  t);

}
