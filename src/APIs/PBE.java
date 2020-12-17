package APIs;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;

// This is for BASE64 encoding and decoding
import sun.misc.*;
// import com.isnetworks.base64.*;

public class PBE
{
	private static int ITERATIONS = 1000;



	public static String encrypt(char[] password, String plaintext)
		throws Exception
	{
		// Begin by creating a random salt of 64 bits (8 bytes)
		byte[] salt = new byte[8];
		Random random = new Random();
		random.nextBytes(salt);

		// Create the PBEKeySpec with the given password
		PBEKeySpec keySpec = new PBEKeySpec(password);

		// Get a SecretKeyFactory for PBEWithMD5AndDES
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC");

		// Create our key
		SecretKey key = keyFactory.generateSecret(keySpec);

		// Now create a parameter spec for our salt and iterations
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);

		// Create a cipher and initialize it for encrypting
		Cipher cipher = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

		byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

		BASE64Encoder encoder = new BASE64Encoder();

		String saltString = encoder.encode(salt);
		String ciphertextString = encoder.encode(ciphertext);

		return saltString+ciphertextString;
	}

	public static String decrypt(char[] password, String text)
		throws Exception
	{
		// Begin by splitting the text into salt and text Strings
		// salt is first 12 chars, BASE64 encoded from 8 bytes.
		String salt = text.substring(0,12);
		String ciphertext = text.substring(12,text.length());

		// BASE64Decode the bytes for the salt and the ciphertext
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] saltArray = decoder.decodeBuffer(salt);
		byte[] ciphertextArray = decoder.decodeBuffer(ciphertext);

		// Create the PBEKeySpec with the given password
		PBEKeySpec keySpec = new PBEKeySpec(password);

		// Get a SecretKeyFactory for PBEWithMD5AndDES
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC");

		// Create our key
		SecretKey key = keyFactory.generateSecret(keySpec);

		// Now create a parameter spec for our salt and iterations
		PBEParameterSpec paramSpec = new PBEParameterSpec(saltArray, ITERATIONS);

		// Create a cipher and initialize it for encrypting
		Cipher cipher = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		// Perform the actual decryption
		byte[] plaintextArray = cipher.doFinal(ciphertextArray);

		return new String(plaintextArray);
	}
}