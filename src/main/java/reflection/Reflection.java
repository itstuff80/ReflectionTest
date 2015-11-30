/**
 * 
 */
package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import vo.Person;

/**
 * @author ejurado
 *
 */
public class Reflection {

	public static void main(String args[]){
		try {
			Class<?> person = Class.forName("vo.Person");
			Method[] methods=person.getDeclaredMethods();
			
			Object o=person.newInstance();
			for (Method method : methods) {			
				System.out.println(method.getName());
			}
			Method mSetName=person.getMethod("setName", String.class);
			Method mSetEmail=person.getMethod("setEmail", String.class);
			Method mSetId=person.getMethod("setId", int.class);
			
            mSetName.invoke(o, "Edgar");
            mSetEmail.invoke(o, "masterofuniverse@worlddomination.com");
            mSetId.invoke(o, 1);
            
            System.out.println(((Person)o).getName());
            
            String toString="";
            for (Method method : methods) {
            	String property=method.getName();           
            	if (property.startsWith("get")){
            		toString+=property.substring(3) + ": " + method.invoke(o, null) + "; ";
            	}
			}
            
            System.out.println(toString);
            
            
            Method mSquare=person.getMethod("square", long.class);
            
            List<Long> numbers=new ArrayList<Long>();
            numbers.add(1L);
            numbers.add(2L);
            numbers.add(3L);
            numbers.add(4L);
            numbers.add(5L);
            
            List<Long> squares=apply(o,mSquare,numbers);
            for (Long n : squares) {
				System.out.println(n);
			}
            
            
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Long> apply(Object o, Method m, List<Long> parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<Long> results=new ArrayList<Long>();
		for (Long n : parameters) {
			results.add((Long) m.invoke(o, n));
		}
		return results;
	}
}
