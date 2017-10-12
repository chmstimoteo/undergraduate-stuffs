package src;

import java.math.BigInteger;

public class Sample {

	private byte[] sampleBytes;
	
	public Sample(int numberBytes){
		this.sampleBytes = new byte[numberBytes];
	}
	
	public Sample(byte[] sample){
		this.sampleBytes = sample;
	}

	public byte[] getSampleBytes() {
		return sampleBytes;
	}
	
	public void addByte(byte b, int position){
		this.sampleBytes[position] = b;
	}

	public void setSampleBytes(byte[] sampleBits) {
		this.sampleBytes = sampleBits;
	}

	public int getSize() {
		return this.sampleBytes.length;
	}
	
	public Sample plus(Sample fator){
		Sample result = new Sample(fator.getSize());
		byte[] bits = result.getSampleBytes();
		byte[] fatorBits = fator.getSampleBytes();
		
		for(int i=0; i<result.getSize(); i++){
			bits[i] = (byte) (this.sampleBytes[i]+fatorBits[i]);
		}
	
		return result;
		
	}
	
	public Sample minus(Sample fator){
		Sample result = new Sample(fator.getSize());
		byte[] bits = result.getSampleBytes();
		byte[] fatorBits = fator.getSampleBytes();
		
		for(int i=0; i<result.getSize(); i++){
			bits[i] = (byte) (this.sampleBytes[i]-fatorBits[i]);
		}
	
		return result;
	}
	
	public Sample product(Sample fator){
		Sample result = new Sample(fator.getSize());
		byte[] bits = result.getSampleBytes();
		byte[] fatorBits = fator.getSampleBytes();
		
		for(int i=0; i<result.getSize(); i++){
			bits[i] = (byte) (this.sampleBytes[i]*fatorBits[i]);
		}
	
		return result;
	}
	
	public Sample division(Sample fator){
		Sample result = new Sample(fator.getSize());
		byte[] bits = result.getSampleBytes();
		byte[] fatorBits = fator.getSampleBytes();
		
		for(int i=0; i<result.getSize(); i++){
			bits[i] = (byte) (this.sampleBytes[i]/fatorBits[i]);
		}
	
		return result;
	}
	
	
	public String toString(){
		String str = "";
		for(int i=0; i<this.sampleBytes.length; i++){
			str += Integer.toBinaryString(this.sampleBytes[i]);
		}
		return str;
	}
	
	public String toIntString(){
		String str = "";
		for(int i=0; i<this.sampleBytes.length; i++){
			str += Integer.toString(this.sampleBytes[i]);
		}
		return str;
	}
	
	public BigInteger toBinary(){
		return new BigInteger(this.toString());
	}

	public int toInteger() {
		return Integer.parseInt(this.toIntString());
		
	}
	
	
	/*public static void main(String[] args){
		byte[] b = {10,11};
		Sample s1 = new Sample(b);
		
		byte[] b1 = {32,9};
		Sample s2 = new Sample(b1);
		
		//s1.setSampleBits(b);
		
		byte[] c = (s1.product(s2)).getSampleBytes();
		
		byte d = 5;
		byte e = 3;
		
		System.out.println(Integer.toBinaryString(d*d));
	}*/
}
