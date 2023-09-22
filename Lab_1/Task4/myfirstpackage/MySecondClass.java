package myfirstpackage;
public class MySecondClass{
	private int firstValue;
	private int secondValue;
	public int getFirstValue() {
		return firstValue;
	}
	public int getSecondValue(){
		return secondValue;
	}
	public void setFirstValue(int newFirstValue){
		firstValue = newFirstValue;
	}
	public void setSecondValue(int newSecondValue){
		secondValue = newSecondValue;
	}
	public MySecondClass(int firstValue, int secondValue){
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}
	public int toSubstract(){
		return firstValue - secondValue;
	}
}