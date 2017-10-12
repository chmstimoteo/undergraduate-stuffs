package temp;

public class TypeConverter {

	public static final byte[] intToByteArray(int value) {
		return new byte[]{
		(byte)(value >>> 24), (byte)(value >> 16), (byte)(value >> 8), (byte)value };
	}
	
	
	public static final int byteArrayToInt(byte [] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
	}
	
	
	public static final byte[] intToByteArray(short value) {
		return new byte[]{(byte)(value >> 8), (byte)value };
	}
	
	
	public static final short byteArrayToShort(byte [] b) {
		short x = 0xFF;
		return (short) (( (b[0] & x) << 8) + ( b[1] & x ));
	}
	
	public static final short byteSignedToUnsegned(byte  b) {
		short x = 0xFF;
		return (short) (( (0 & x) << 8) + ( b & x ));
	}
	

	
}
