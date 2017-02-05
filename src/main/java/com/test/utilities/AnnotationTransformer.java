package com.test.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class AnnotationTransformer implements IAnnotationTransformer{
	
	static final ClassPool cp = ClassPool.getDefault();

	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		// TODO Auto-generated method stub
		arg0.setPriority(getLineNumber(arg3));
	}
	
	private int getLineNumber(Method m)
	{
		try{
		CtClass ct = cp.get(m.getDeclaringClass().getCanonicalName());
		System.out.println(ct);
		CtMethod cm = ct.getDeclaredMethod(m.getName());
		System.out.println(cm);
		return cm.getMethodInfo().getLineNumber(0);
		}
		catch(NotFoundException e)
		{
			System.out.println("The expected class is not found");
			throw new RuntimeException("the expected class is not found");
		}
	}

}
