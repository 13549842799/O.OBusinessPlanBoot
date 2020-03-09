package com.cyz.ob.basic.entity;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("serial")
public abstract class IdEntity <T> implements Serializable {
	
	private T id; //主键
	@SuppressWarnings("rawtypes")
	private static final Class[] params = new Class[] {String.class};
	
	public IdEntity () {}
	
	public IdEntity (T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	public T mainTainId (Class<T> cls) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!(id instanceof Number)) {
			return id;
		}
		String str = String.valueOf(id);
		Constructor<T> cons = cls.getConstructor(params);
		Object o = cons.newInstance(new Object[] {str});
        this.id = (T)o;
		return this.id;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + "";
	}

}
