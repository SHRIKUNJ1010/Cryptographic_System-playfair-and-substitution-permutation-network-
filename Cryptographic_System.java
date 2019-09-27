import java.util.Scanner;
import java.lang.Math;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.io.*;

class Cryptographic_System {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static void main(String args[]) {
        String plaintext = new String();
        String ciphertext = new String();

        int option;
        int a;// switching variables

        Encrypt e = new Encrypt();// Objects of diffrent class
        Decrypt d = new Decrypt();
        Scanner s = new Scanner(System.in);

    String s3 = "Shrikunj1010";//required user name
    System.out.println("Enter UserName:");
    String s2 = s.nextLine();
    while(s2.equals(s3) != true)//loop until valid username is printed
    {
        System.out.println("!!Enter valid UserName!!");
        s2 = s.nextLine();
    }

    File pass = new File("E:\\java\\hashvalue.txt");//opening file which has hash value of password
    try {
        Scanner sn = new Scanner(pass);
        s3 = sn.nextLine();//scanning hash value of password and storing in string
        sn.close();
    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    }
    
    System.out.println("Enter Password:");
    String s1 = s.nextLine();//scanning password
    try{
            s1 = toHexString(getSHA(s1));//converting password to hash value
        }
    catch (NoSuchAlgorithmException l) {  
            System.out.println("Exception thrown for incorrect algorithm: " + l);  
        }
        if(s1.equals(s3) != true)
        {
            System.out.println("!!Entered Password is wrong!!");
        } 
    while(s1.equals(s3) != true)
    {
        System.out.println("Enter Password:");
        s1 = s.nextLine();//scanning password
        try{
            s1 = toHexString(getSHA(s1));//converting password to hash value
        }
        catch (NoSuchAlgorithmException l) {  
            System.out.println("Exception thrown for incorrect algorithm: " + l);  
        } 
        if(s1.equals(s3) != true)
        {
            System.out.println("!!Entered Password is wrong!!");
        }
    }


    System.out.println("Select the algortihm:");
    System.out.println("1 - PlayFair Cipher");
    System.out.println("2 - Substitution Permutation Network");

    option = s.nextInt();
    switch(option){
        case 1:
        System.out.println("Choose Option:");
        System.out.println("1 - Encrypt Message");
        System.out.println("2 - Decrypt Message");

        a = s.nextInt();
            switch(a)
            {
                case 1:
                System.out.println("Enter your message to encrypt:");
                plaintext = s.nextLine();
                plaintext = s.nextLine();

                ciphertext = e.playFair(plaintext);

                System.out.println(ciphertext);
                break;

                case 2:
                System.out.println("Enter your message to decrypt:");
                ciphertext = s.nextLine();
                ciphertext = s.nextLine();

                plaintext = d.playFair(ciphertext);

                System.out.println(plaintext);
                break;
            }
        break;

        case 2:
        System.out.println("Choose Option:");
        System.out.println("1 - Encrypt Message");
        System.out.println("2 - Decrypt Message");

        a = s.nextInt();
            switch(a)
            {
                case 1:
                System.out.println("Enter your message to encrypt:");
                plaintext = s.nextLine();
                plaintext = s.nextLine();

                ciphertext = e.spn(plaintext);

                System.out.println(ciphertext);
                break;

                case 2:
                System.out.println("Enter your message to decrypt:");
                ciphertext = s.nextLine();
                ciphertext = s.nextLine();

                plaintext = d.spn(ciphertext);

                System.out.println(plaintext);
                break;
            }
        break;
    }
    s.close();
    }
}




class Encrypt{
    public String removeWhiteSpace(String plaintext)
    {
        plaintext = plaintext.replaceAll("\\s", "");
        return plaintext;
    }

    public String removeRepeatedCharacter(String input)
    {
        String output = new String();
        int c = 2;
        output = output + input.charAt(0);
        for (int i = 0; i < input.length(); i++) 
        {
            for (int j = 0; j < output.length(); j++) 
            {
                if (input.charAt(i) == output.charAt(j)) 
                {
                    c = 1;
                    break;
                }
                else
                {
                    c = 0;
                }
            }
            if(c == 0)
            {
                output = output + input.charAt(i);
            }
        }
        return output;
    }

    public String binaryToHex(byte bin[][],int n)
    {
	int j;
	String hex = new String();
	for(int z = 0;z < n;z++)
	{
		for(int i = 0;i < 2;i++)
		{
			j = 4 * i;
			if(bin[z][j] == 0 && bin[z][j+1] == 0 && bin[z][j+2] == 0 && bin[z][j+3] == 0)
			{
				hex += '0';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 0 && bin[z][j+2] == 0 && bin[z][j+3] == 1)
			{
				hex += '1';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 0 && bin[z][j+2] == 1 && bin[z][j+3] == 0)
			{
				hex += '2';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 0 && bin[z][j+2] == 1 && bin[z][j+3] == 1 )
			{
				hex += '3';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 1 && bin[z][j+2] == 0 && bin[z][j+3] == 0 )
			{
				hex += '4';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 1 && bin[z][j+2] == 0 && bin[z][j+3] == 1 )
			{
				hex += '5';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 1 && bin[z][j+2] == 1 && bin[z][j+3] == 0 )
			{
				hex += '6';
			}

			else if(bin[z][j] == 0 && bin[z][j+1] == 1 && bin[z][j+2] == 1 && bin[z][j+3] == 1 )
			{
				hex += '7';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 0 && bin[z][j+2] == 0 && bin[z][j+3] == 0 )
			{
				hex += '8';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 0 && bin[z][j+2] == 0 && bin[z][j+3] == 1 )
			{
				hex += '9';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 0 && bin[z][j+2] == 1 && bin[z][j+3] == 0 )
			{
				hex += 'a';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 0 && bin[z][j+2] == 1 && bin[z][j+3] == 1 )
			{
				hex += 'b';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 1 && bin[z][j+2] == 0 && bin[z][j+3] == 0 )
			{
				hex += 'c';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 1 && bin[z][j+2] == 0 && bin[z][j+3] == 1 )
			{
				hex += 'd';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 1 && bin[z][j+2] == 1 && bin[z][j+3] == 0 )
			{
				hex += 'e';
			}

			else if(bin[z][j] == 1 && bin[z][j+1] == 1 && bin[z][j+2] == 1 && bin[z][j+3] == 1 )
			{
				hex += 'f';
			}
		}
	}
	return hex;
}

    public String playFair(String plaintext)
    {
        String ciphertext = new String();
        Scanner s = new Scanner(System.in);
        String key;

        int i,j,t,c = 2;//loop and conditional variable

        int x,y,z;//temp variables

        char en_block[][] = new char[5][5];//key block

        char bn_block[][] = {{'a','b','c','d','e'},{'f','g','h','i','k'},{'l','m','n','o','p'},{'q','r','s','t','u'},{'v','w','x','y','z'}};
        //abcd block

        char cipher[] = new char[2];

        plaintext = removeWhiteSpace(plaintext);

        plaintext = plaintext.toLowerCase();

        plaintext = plaintext.replaceAll("j","i");

        System.out.println("Enter the key: ");
        key = s.nextLine();

        key = removeWhiteSpace(key);

        key = key.toLowerCase();

        key = removeRepeatedCharacter(key);

        //inserting key in keyblock
        for(i = 0;i < key.length();i++)
        {
            t = i % 5;
            j = i / 5;
            en_block[j][t] = key.charAt(i); 
        }

        //preparing key block after inserting key in block
        z = key.length();
        y = z % 5;
        x = z / 5;
        for(i = 0;i < 5;i++)
        {
            for(j = 0;j < 5;j++)
            {
                c = 1;
                for(t = 0;t < z;t++)
                {
                    if(bn_block[i][j] == key.charAt(t))
                    {
                        c = 0;
                    }
                }
                if(c == 1)
                {
                    en_block[x][y] = bn_block[i][j];
                    y++;
                    x = x + (y / 5);
                    y = y % 5;
                }
            }
        }


        int a = 6,b = 6;//rows and cols for first char initializatin 6 is just for reference
        int e = 6,d = 6;//rows and cols for second char
        for(i = 0;i < plaintext.length();i += 2)
        {
            cipher[0] = plaintext.charAt(i);
            if(i+1 >= plaintext.length())
            {
                plaintext += 'x';
            }
            cipher[1] = plaintext.charAt(i+1);
            
            //taking dummy character for repeating characters
            if(cipher[0] == cipher[1] && cipher[0] != x)
            {
                cipher[1] = 'x';
                i--;
            }

            for(x = 0;x < 5;x++)
            {
                for(y = 0;y < 5;y++)
                {
                    if(cipher[0] == en_block[x][y])
                    {
                        a = x;//rows of 1 ch
                        b = y;//cols of 1 ch
                    }
                }
            }

            for(x = 0;x < 5;x++)
            {
                for(y = 0;y < 5;y++)
                {
                    if(cipher[1] == en_block[x][y])
                    {
                        d = x;//rows of 1 ch
                        e = y;//cols of 1 ch
                    }
                }
            }

            //same row
            if(d == a)
            {
                b = (b + 1) % 5;
                e = (e + 1) % 5;

                //taking next char in same row in cyclic manner
                cipher[0] = en_block[a][b];
                cipher[1] = en_block[d][e];
            }

            //same column
            else if(e == b)
            {
                a = (a + 1) % 5;
                d = (d + 1) % 5;

                //taking next char in same column in cyclic manner
                cipher[0] = en_block[a][b];
                cipher[1] = en_block[d][e];
            }

            else
            {
                //taking char of same row and column of second char in keyblock
                cipher[0] = en_block[a][e];
                cipher[1] = en_block[d][b];
            }

            //inserting pair of ciphered char into string 
            ciphertext = ciphertext +cipher[0];
            ciphertext = ciphertext +cipher[1];
        }

        s.close(); 
        return ciphertext;     
    }

    public String spn(String plaintext)
    {
        String ciphertext = new String();
        Scanner s = new Scanner(System.in);

        String key;
        byte keys[] = new byte[32];//key for key scheduling algorithm
        byte roundkey[][] = new byte[5][16];//key for rounds of spn

        int x;// temp variable
        byte t;// temp variable

        int i,j,k,z,y;//looping variables

        byte bitplain[][] = new byte[300][8];//plaintext in binary form
        byte bitcipher[][] = new byte[300][8];//ciphertext in binary form

        byte round[] = new byte[16];//for plaintext

        //key sheduling algorithm
        System.out.println("Enter 4 character key");
        key = s.nextLine();

        //converting key into binary
        z =0;
        for(i = 0;i < key.length();i++)
        {
            x = Integer.valueOf(key.charAt(i));
            for(j = z+7;j >= z;j--)
            {
                if(x % 2 == 1)
                {
                    keys[j] = 1;
                }
                else 
                {
                    keys[j] = 0;
                }
                x = x / 2;
            }
            z += 8;
        }

        //taking round keys into diffrent arrays
        z = 0;
        for(i = 0;i < 5;i++)
        {
            y = 0;
            for(j = z;j < z+16;j++)
            {
                roundkey[i][y] = keys[j];
                y++;
            }
            z += 4;
        }

        plaintext = removeWhiteSpace(plaintext);

        if(plaintext.length() % 2 == 1)
        {
            plaintext = plaintext + 'x';
        }

        //converting plaintext into binary
        for(i = 0;i < plaintext.length();i++)
        {
            x = Integer.valueOf(plaintext.charAt(i));//taking the ascii(unicode) value of ith character of plaintext 
            
            for(j = 7;j >= 0;j--)
            {
                if(x % 2 == 1)
                {
                    bitplain[i][j] = 1;
                }
                else 
                {
                    bitplain[i][j] = 0;
                }
                x = x / 2;
            }
            
        }

        for(i = 0;i < plaintext.length();i += 2)
        {
            for(j = 0;j < 16;j++)//taking 16bits at a time from plaintext
            {
                round[j] = bitplain[i+(j/8)][j%8];
            }

            for(k = 0;k < 4;k++)//rounds of substitution permutation network
            {
                for(z = 0;z < 16;z++)//xor with the ith round key
                {
                    round[z] = (byte) (round[z] ^ roundkey[k][z]);
                }

                //substitution function from one 4 bit value to another 4 bit value
                for(z = 0;z < 16;z += 4)
                {
                    if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                     else if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                }


                //permutation of all 16 bits
                t = round[1];
                round[1] = round[4];
                round[4] = t;

                t = round[2];
                round[2] = round[8];
                round[8] = t;

                t = round[3];
                round[3] = round[12];
                round[12] = t;

                t = round[6];
                round[6] = round[9];
                round[9] = t;

                t = round[7];
                round[7] = round[13];
                round[13] = t;

                t = round[11];
                round[11] = round[14];
                round[14] = t;

            }

            for(z = 0;z < 16;z++)//xor with the 5th round key after completion of 4 rounds
            {
                round[z] = (byte) (round[z] ^ roundkey[4][z]);
            }

            for(j = 0;j < 16;j++)//assigning encrypted bit values to binary form of ciphertext
            {
                bitcipher[i+(j/8)][j%8] = round[j];
            }
        }

        ciphertext = binaryToHex(bitcipher,plaintext.length());

        s.close();
        return ciphertext;
    }
}




class Decrypt
{
    public String removeWhiteSpace(String plaintext)
    {
        plaintext = plaintext.replaceAll("\\s", "");
        return plaintext;
    }

    public String removeRepeatedCharacter(String input)
    {
        String output = new String();
        int c = 2;
        output = output + input.charAt(0);
        for (int i = 0; i < input.length(); i++) 
        {
            for (int j = 0; j < output.length(); j++) 
            {
                if (input.charAt(i) == output.charAt(j)) 
                {
                    c = 1;
                    break;
                }
                else
                {
                    c = 0;
                }
            }
            if(c == 0)
            {
                output = output + input.charAt(i);
            }
        }
        return output;
    }

    public byte[][] hexToBinary(String hex)
{
	char x;
	byte bin[][] = new byte[300][8];
	for(int i = 0; i < hex.length();i+=2)
	{
		x = hex.charAt(i);
			if(x == '0')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 0;
				bin[i/2][2] = 0;
				bin[i/2][3] = 0; 
			}

			else if(x == '1')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 0;
				bin[i/2][2] = 0;
				bin[i/2][3] = 1; 
			}

			else if(x == '2')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 0;
				bin[i/2][2] = 1;
				bin[i/2][3] = 0; 
			}

			else if(x == '3')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 0;
				bin[i/2][2] = 1;
				bin[i/2][3] = 1; 
			}

			else if(x == '4')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 1;
				bin[i/2][2] = 0;
				bin[i/2][3] = 0; 
			}

			else if(x == '5')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 1;
				bin[i/2][2] = 0;
				bin[i/2][3] = 1; 
			}

			else if(x == '6')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 1;
				bin[i/2][2] = 1;
				bin[i/2][3] = 0; 
			}

			else if(x == '7')
			{
				bin[i/2][0] = 0;
				bin[i/2][1] = 1;
				bin[i/2][2] = 1;
				bin[i/2][3] = 1; 
			}

			else if(x == '8')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 0;
				bin[i/2][2] = 0;
				bin[i/2][3] = 0; 
			}

			else if(x == '9')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 0;
				bin[i/2][2] = 0;
				bin[i/2][3] = 1; 
			}
			else if(x == 'a')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 0;
				bin[i/2][2] = 1;
				bin[i/2][3] = 0; 
			}

			else if(x == 'b')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 0;
				bin[i/2][2] = 1;
				bin[i/2][3] = 1; 
			}
			else if(x == 'c')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 1;
				bin[i/2][2] = 0;
				bin[i/2][3] = 0; 
			}
			else if(x == 'd')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 1;
				bin[i/2][2] = 0;
				bin[i/2][3] = 1; 
			}

			else if(x == 'e')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 1;
				bin[i/2][2] = 1;
				bin[i/2][3] = 0; 
			}

			else if(x == 'f')
			{
				bin[i/2][0] = 1;
				bin[i/2][1] = 1;
				bin[i/2][2] = 1;
				bin[i/2][3] = 1; 
			}

		
		x = hex.charAt(i+1);
			if(x == '0')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 0;
				bin[i/2][6] = 0;
				bin[i/2][7] = 0; 
			}

			else if(x == '1')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 0;
				bin[i/2][6] = 0;
				bin[i/2][7] = 1; 
			}

			else if(x == '2')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 0;
				bin[i/2][6] = 1;
				bin[i/2][7] = 0; 
			}

			else if(x == '3')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 0;
				bin[i/2][6] = 1;
				bin[i/2][7] = 1; 
			}

			else if(x == '4')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 1;
				bin[i/2][6] = 0;
				bin[i/2][7] = 0; 
			}

			else if(x == '5')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 1;
				bin[i/2][6] = 0;
				bin[i/2][7] = 1; 
			}

			else if(x == '6')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 1;
				bin[i/2][6] = 1;
				bin[i/2][7] = 0; 
			}

			else if(x == '7')
			{
				bin[i/2][4] = 0;
				bin[i/2][5] = 1;
				bin[i/2][6] = 1;
				bin[i/2][7] = 1; 
			}

			else if(x == '8')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 0;
				bin[i/2][6] = 0;
				bin[i/2][7] = 0; 
			}

			else if(x == '9')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 0;
				bin[i/2][6] = 0;
				bin[i/2][7] = 1; 
			}
			else if(x == 'a')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 0;
				bin[i/2][6] = 1;
				bin[i/2][7] = 0; 
			}

			else if(x == 'b')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 0;
				bin[i/2][6] = 1;
				bin[i/2][7] = 1; 
			}
			else if(x == 'c')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 1;
				bin[i/2][6] = 0;
				bin[i/2][7] = 0; 
			}
			else if(x == 'd')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 1;
				bin[i/2][6] = 0;
				bin[i/2][7] = 1; 
			}

			else if(x == 'e')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 1;
				bin[i/2][6] = 1;
				bin[i/2][7] = 0; 
			}

			else if(x == 'f')
			{
				bin[i/2][4] = 1;
				bin[i/2][5] = 1;
				bin[i/2][6] = 1;
				bin[i/2][7] = 1; 
			}
		
	}
	return bin;
}

    public String playFair(String ciphertext)
    {
        String plaintext = new String();
        Scanner s = new Scanner(System.in);
        String key;

        int i,j,t,c = 2;//loop and conditional variable

        int x,y,z;//temp variables

        char en_block[][] = new char[5][5];//key block

        char bn_block[][] = {{'a','b','c','d','e'},{'f','g','h','i','k'},{'l','m','n','o','p'},{'q','r','s','t','u'},{'v','w','x','y','z'}};
        //abcd block

        char plain[] = new char[2];

        System.out.println("Enter the key: ");
        key = s.nextLine();

        key = removeWhiteSpace(key);

        key = key.toLowerCase();

        key = removeRepeatedCharacter(key);

        //inserting key in keyblock
        for(i = 0;i < key.length();i++)
        {
            t = i % 5;
            j = i / 5;
            en_block[j][t] = key.charAt(i); 
        }

        //preparing key block after inserting key in block
        z = key.length();
        y = z % 5;
        x = z / 5;
        for(i = 0;i < 5;i++)
        {
            for(j = 0;j < 5;j++)
            {
                c = 1;
                for(t = 0;t < z;t++)
                {
                    if(bn_block[i][j] == key.charAt(t))
                    {
                        c = 0;
                    }
                }
                if(c == 1)
                {
                    en_block[x][y] = bn_block[i][j];
                    y++;
                    x = x + (y / 5);
                    y = y % 5;
                }
            }
        }

        
        int a = 6,b = 6;//rows and cols for first char initializatin 6 is just for reference
        int e = 6,d = 6;//rows and cols for second char
        for(i = 0;i < ciphertext.length();i += 2)
        {
            plain[0] = ciphertext.charAt(i);
            plain[1] = ciphertext.charAt(i+1);

            for(x = 0;x < 5;x++)
            {
                for(y = 0;y < 5;y++)
                {
                    if(plain[0] == en_block[x][y])
                    {
                        a = x;//rows of 1 ch
                        b = y;//cols of 1 ch
                    }
                }
            }

            for(x = 0;x < 5;x++)
            {
                for(y = 0;y < 5;y++)
                {
                    if(plain[1] == en_block[x][y])
                    {
                        d = x;//rows of 1 ch
                        e = y;//cols of 1 ch
                    }
                }
            }

            //same row
            if(d == a)
            {
                b = (b - 1) % 5;
                e = (e - 1) % 5;

                if(b < 0)
                {
                    b += 5;
                }

                if(e < 0)
                {
                    e += 5;
                }

                //taking next char in same row in cyclic manner
                plain[0] = en_block[a][b];
                plain[1] = en_block[d][e];
            }

            //same column
            else if(e == b)
            {
                a = (a - 1) % 5;
                d = (d - 1) % 5;

                if(a < 0)
                {
                    a += 5;
                }

                if(d < 0)
                {
                    d += 5;
                }
                //taking next char in same column in cyclic manner
                plain[0] = en_block[a][b];
                plain[1] = en_block[d][e];
            }

            else
            {
                //taking char of same row and column of second char in keyblock
                plain[0] = en_block[a][e];
                plain[1] = en_block[d][b];
            }

            //inserting pair of ciphered char into string 
            plaintext = plaintext + plain[0];
            plaintext = plaintext + plain[1];
        }

        s.close(); 
        return plaintext;
    }

    public String spn(String ciphertext)
    {
        String plaintext = new String();
        Scanner s = new Scanner(System.in);

        String key;
        byte keys[] = new byte[32];//key for key scheduling algorithm
        byte roundkey[][] = new byte[5][16];//key for rounds of spn

        int x;// temp variable
        byte t;// temp variable
        char v;// temp variable

        int i,j,k,z,y;//looping variables

        byte bitplain[][] = new byte[300][8];//plaintext in binary form
        byte bitcipher[][] = new byte[300][8];//ciphertext in binary form

        byte round[] = new byte[16];//for plaintext

        //key sheduling algorithm
        System.out.println("Enter 4 character key");
        key = s.nextLine();

        //converting key into binary
        z =0;
        for(i = 0;i < key.length();i++)
        {
            x = Integer.valueOf(key.charAt(i));
            for(j = z+7;j >= z;j--)
            {
                if(x % 2 == 1)
                {
                    keys[j] = 1;
                }
                else 
                {
                    keys[j] = 0;
                }
                x = x / 2;
            }
            z += 8;
        }

        //taking round keys into diffrent arrays
        z = 0;
        for(i = 0;i < 5;i++)
        {
            y = 0;
            for(j = z;j < z+16;j++)
            {
                roundkey[i][y] = keys[j];
                y++;
            }
            z += 4;
        }

        //converting ciphertext into binary
        bitcipher = hexToBinary(ciphertext);
        
        for(i = 0;i < (ciphertext.length()/2);i += 2)
        {
            for(j = 0;j < 16;j++)//taking 16bits at a time from ciphertext
            {
                round[j] = bitcipher[i+(j/8)][j%8];
            }

	        for(z = 0;z < 16;z++)//xor with the 5th round key 
            {
                round[z] = (byte) (round[z] ^ roundkey[4][z]);
            }

            for(k = 3;k >= 0;k--)//rounds of substitution permutation network
            {
		        //permutation inverse of all 16 bits
                t = round[1];
                round[1] = round[4];
                round[4] = t;

                t = round[2];
                round[2] = round[8];
                round[8] = t;

                t = round[3];
                round[3] = round[12];
                round[12] = t;

                t = round[6];
                round[6] = round[9];
                round[9] = t;

                t = round[7];
                round[7] = round[13];
                round[13] = t;

                t = round[11];
                round[11] = round[14];
                round[14] = t;
    
                //inverse of substitution function
                for(z = 0;z < 16;z += 4)
                {
                    if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                     else if(round[z] == 0 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 0 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 0;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 0 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 0;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 0)
                    {
                        round[z] = 0;
                        round[z+1] = 0;
                        round[z+2] = 0;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 0 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 0;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 0)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 0;
                        round[z+3] = 1;
                    }
                    else if(round[z] == 1 && round[z+1] == 1 && round[z+2] == 1 && round[z+3] == 1)
                    {
                        round[z] = 1;
                        round[z+1] = 1;
                        round[z+2] = 1;
                        round[z+3] = 1;
                    }
                }
		
		        for(z = 0;z < 16;z++)//xor with the ith round key
                {
                    round[z] = (byte) (round[z] ^ roundkey[k][z]);
                }

            }

            
            for(j = 0;j < 16;j++)//assigning decrypted bit values to binary form of plaintext
            {
                bitplain[i+(j/8)][j%8] = round[j];
            }
        }

         //taking ASCII value for encrypted character and assigning it to ciphertext string
         for(i = 0;i < (ciphertext.length()/2);i++)
         {
             x = 0;
             for(j = 0;j <= 7;j++)
             {
                 x = (int) (x + (bitplain[i][j]*Math.pow(2, (7-j))));
             }
             v = (char)x;
             plaintext = plaintext + v;
         }

        s.close();
        return plaintext;
    }
}