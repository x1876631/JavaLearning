package thinkingInJava.chapter12;

public abstract class Inning {
	public Inning() throws BException{
	}
	
//	public void event() throws AException{
//		
//	}
	
	public abstract void atBat() throws A1Exception,A2Exception;
	
	public void walk(){}
}
