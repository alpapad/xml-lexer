package com.aktarma;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

import groovy.lang.GroovyClassLoader;

public class TestGroovy {

	public static void main(String[] args) throws CompilationFailedException, IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		test("Test");
		test("Test2");
		test("Test3");
	}

	private static void test(String f) throws CompilationFailedException, IOException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		GroovyClassLoader gcl = new GroovyClassLoader();
		Class<?> clazz = gcl.parseClass(new File("C:/WORK/projects/aktarma.xml-lexer/src/main/java/com/aktarma/" +f +".groovy"));
		Object aScript = clazz.newInstance();
		MyInterface myObject = (MyInterface) aScript;
		System.err.println(f + ":" + myObject.interfaceMethod("sdfhgdsfhsdfh"));
	}
}
